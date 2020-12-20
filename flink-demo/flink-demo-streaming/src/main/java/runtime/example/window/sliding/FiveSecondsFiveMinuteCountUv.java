package runtime.example.window.sliding;

import lombok.*;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import runtime.utils.SplitSeparator;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/20 15:10
 * @Description 每隔5秒统计最近5分钟热门页面
 */
public class FiveSecondsFiveMinuteCountUv {


    /**
     * 每 隔 5 秒 统 计 最 近 5 分 钟 热 门 页 面
     * 五秒 - 五分钟
     * 热门商品 单个商品 求 TopN
     * <p>
     * key 商品 累加count 值
     * 排序求 TopN
     */


    final static String path = "C:\\software-engineer\\flink-demo\\flink-demo-streaming\\src\\main\\resources\\example\\data2.log";

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        try {

            env
                    .readTextFile(path)
                    .map(new FiveSecondsFiveMinuteCountUvCustomMapFun())
                    .assignTimestampsAndWatermarks(new FiveSecondsFiveMinuteCountUvBoundOutOfTime())
                    .keyBy(ApacheLogEvent::getUrl)
                    .window(SlidingEventTimeWindows.of(Time.minutes(5), Time.seconds(5)))
                    .aggregate(new FiveSecondsFiveMinuteCountUvCustomAggregateFun(), new FiveSecondsFiveMinuteCountUvCustomTimeWindow())
                    .keyBy(UrlViewCount::getWindowEnd)
                    .process(new FiveSecondsFiveMinuteCountUvCustomProcessFunction(3))
                    .print();

            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    //输入数据类
    public static class ApacheLogEvent {
        private String ip; //IP地址
        private String userId; //用户ID
        private Long eventTime; //用户点击广告时间
        private String method; //请求方式
        private String url; //请求的URL

    }

    // 窗口聚合结果样例类
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    private static class UrlViewCount {
        private String url; //请求的URL
        private Long windowEnd; //窗口
        private Long count; //点击的次数

    }


    public static class FiveSecondsFiveMinuteCountUvCustomMapFun implements MapFunction<String, ApacheLogEvent> {
        //83.149.9.123 - - 17/05/2020:10:05:03 +0000 GET /presentations/logstash-kafkamonitor-2020/images/kibana-search.png
        @Override
        public ApacheLogEvent map(String value) throws Exception {
            val dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
            String[] thisLineData = value.split(SplitSeparator.SPLIT_6);
            return new ApacheLogEvent(thisLineData[0], thisLineData[1], dateFormat.parse(thisLineData[3]).getTime(), thisLineData[5], thisLineData[6]);
        }
    }


    public static class FiveSecondsFiveMinuteCountUvBoundOutOfTime implements AssignerWithPeriodicWatermarks<ApacheLogEvent> {

        Long currentMaxEventTime = 0L;//设置当前窗口里面最大的时间
        int maxOufOfOrderTime = 10000;//最大乱序时间 10s

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            return new Watermark(currentMaxEventTime - maxOufOfOrderTime);
        }

        @Override
        public long extractTimestamp(ApacheLogEvent element, long previousElementTimestamp) {
            Long eventTime = element.getEventTime();
            currentMaxEventTime = Math.max(eventTime, currentMaxEventTime);
            return eventTime;
        }

    }


    /**
     * 自定义窗口处理函数
     */
    public static class FiveSecondsFiveMinuteCountUvCustomTimeWindow implements WindowFunction<Long, UrlViewCount, String, TimeWindow> {
        @Override
        public void apply(String str, TimeWindow window, Iterable<Long> input, Collector<UrlViewCount> out) throws Exception {
            Long next = input.iterator().next();
            out.collect(new UrlViewCount(str, window.getEnd(), next));
        }
    }

    /**
     * 实现的是对URL进行聚合
     * sum
     * 辅助变量，累加变量
     */
    public static class FiveSecondsFiveMinuteCountUvCustomAggregateFun implements AggregateFunction<ApacheLogEvent, Long, Long> {
        @Override
        public Long createAccumulator() {
            return 0L;
        }

        @Override
        public Long add(ApacheLogEvent value, Long accumulator) {
            return accumulator += 1L;
        }

        @Override
        public Long getResult(Long accumulator) {
            return accumulator;
        }

        @Override
        public Long merge(Long a, Long b) {
            return a + b;
        }
    }

    public static class FiveSecondsFiveMinuteCountUvCustomProcessFunction extends ProcessFunction<UrlViewCount, Object> {
        private int size;
        private ListState<UrlViewCount> listState;

        public FiveSecondsFiveMinuteCountUvCustomProcessFunction(int size) {
            this.size = size;
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            ListStateDescriptor<UrlViewCount> descriptor = new ListStateDescriptor<>("listState", UrlViewCount.class);
            this.listState = getRuntimeContext().getListState(descriptor);
        }

        @Override
        public void processElement(UrlViewCount value, Context ctx, Collector<Object> out) throws Exception {
            listState.add(value);
            ctx.timerService().registerEventTimeTimer(value.windowEnd + 1);
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<Object> out) throws Exception {
            super.onTimer(timestamp, ctx, out);

            ArrayList<UrlViewCount> countArrayList = new ArrayList<>();

            for (UrlViewCount urlViewCount : this.listState.get()) {
                countArrayList.add(urlViewCount);
            }
            this.listState.clear();

            countArrayList.sort(new Comparator<UrlViewCount>() {
                @Override
                public int compare(UrlViewCount o1, UrlViewCount o2) {
                    return (int) -(o1.getCount() - o2.getCount());
                }
            });



            StringBuilder result = new StringBuilder();
            result.append("时间：").append(new Timestamp(timestamp - 1)).append("\n");

            countArrayList.forEach(value -> {
                result.append("URL:").append(value.url)
                        .append(" 访问量：").append(value.getCount()).append("\n");
            });
            result.append("===================");

            out.collect(result.toString());

        }
    }
}
