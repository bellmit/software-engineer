package runtime.example;

import com.sun.istack.internal.Nullable;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import runtime.utils.SplitSeparator;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/20 15:58
 * @Description 实时黑名单统计
 */
public class MaliceBlackListOutPut {
    /**
     * 黑名单 当天访问超过100次后 加入黑名单 某一个?
     * 当天
     * 超过100次 (1 - 2 - 3 - 4 - 5) ... (中间状态?)
     * 加入黑名单 侧输出
     */
    final static String path = "C:\\software-engineer\\flink-demo\\flink-demo-streaming\\src\\main\\resources\\example\\data3.csv";

    private static OutputTag<String> outputBlackList = new OutputTag<>("blacklist", Types.STRING);


    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        try {

            SingleOutputStreamOperator<Object> operator = env.readTextFile(path)
                    .map(new MaliceBlackListOutPutMapFun())
                    .assignTimestampsAndWatermarks(new MaliceBlackListOutPutBoundOutOfTime())
                    .keyBy(AdClickEvent::getAdId)
                    .process(new MaliceBlackListOutPutProcessFun(100L));


            operator
                    .getSideOutput(outputBlackList)
                    .print();


            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 输入的广告点击事件样例类
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class AdClickEvent {
        //用户ID
        private Long userId;
        //广告ID
        private Long adId;
        //省份
        private String province;
        //城市
        private String city;
        //用户点击广告的时间
        private Long timeStamp;
    }

    public static class MaliceBlackListOutPutMapFun implements MapFunction<String, AdClickEvent> {
        //83.149.9.123 - - 17/05/2020:10:05:03 +0000 GET /presentations/logstash-kafkamonitor-2020/images/kibana-search.png
        @Override
        public AdClickEvent map(String value) throws Exception {
            //val dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
            String[] thisLineData = value.split(SplitSeparator.SPLIT_4);
            return new AdClickEvent(Long.parseLong(thisLineData[0]), Long.parseLong(thisLineData[1]), thisLineData[2], thisLineData[3], Long.parseLong(thisLineData[4]));
        }
    }

    public static class MaliceBlackListOutPutBoundOutOfTime implements AssignerWithPeriodicWatermarks<AdClickEvent> {

        Long currentMaxEventTime = 0L;//设置当前窗口里面最大的时间
        int maxOufOfOrderTime = 10000;//最大乱序时间 10s

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            return new Watermark(currentMaxEventTime - maxOufOfOrderTime);
        }

        @Override
        public long extractTimestamp(AdClickEvent element, long previousElementTimestamp) {
            Long eventTime = element.getTimeStamp();
            currentMaxEventTime = Math.max(eventTime, currentMaxEventTime);
            return eventTime;
        }

    }

    public static class MaliceBlackListOutPutProcessFun extends KeyedProcessFunction<Long, AdClickEvent, Object> {
        private Long maxCount;

        public MaliceBlackListOutPutProcessFun(Long maxCount) {
            this.maxCount = maxCount;
        }

        private ValueState<Long> countState;
        private ValueState<Boolean> flag;

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            ValueStateDescriptor<Long> descriptor = new ValueStateDescriptor<>("countState", Types.LONG);
            this.countState = getRuntimeContext().getState(descriptor);

            ValueStateDescriptor<Boolean> descriptorFlag = new ValueStateDescriptor<>("descriptorFlag", Types.BOOLEAN);
            this.flag = getRuntimeContext().getState(descriptorFlag);

        }

        @Override
        public void processElement(AdClickEvent value, Context ctx, Collector<Object> out) throws Exception {

            Long everyCount = this.countState.value() == null ? 0L : this.countState.value();
            Boolean everyFlag = this.flag.value() == null ? false : this.flag.value();
            //System.out.println(everyFlag);

            //  第一次到达 设置定时清除
            if (everyCount == 0L) {
                ctx.timerService().registerEventTimeTimer(tomorrowZeroTimestampMs(System.currentTimeMillis(), 8));
            }

            if (everyCount > maxCount) {
                if (!everyFlag) {
                    ctx.output(outputBlackList, "用户" + value.userId + " 对广告：" + value.adId + " 点击超过 " + maxCount + " 次");
                }
                this.flag.update(true);
                return;
            }
            this.countState.update(everyCount + 1L);
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<Object> out) throws Exception {
            super.onTimer(timestamp, ctx, out);
            this.countState.clear();
        }
    }

    public static long tomorrowZeroTimestampMs(long now, int timeZone) {
        return now - (now + timeZone * 3600000) % 86400000 + 86400000;
    }

}
