package runtime.window;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import runtime.pojo.Version1Pojo;
import runtime.utils.CustomKafkaConsumer;
import runtime.window.code.CustomCountTrigger;
import runtime.window.code.CustomTumblingProcessingTimeWindows;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/27 9:14
 * @Description
 */
public class CustomTrigger {
    public static void main(String[] args) throws Exception {

        final OutputTag<Version1Pojo> outputTag = new OutputTag<Version1Pojo>("side-output") {
        };


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Version1Pojo> stream = CustomKafkaConsumer.getResultMethod(env, args, "T1", "Version1Pojo");

/*        stream.keyBy(Version1Pojo::getUserId)
                //.timeWindow(Time.seconds(5))
                .window(CustomTumblingProcessingTimeWindows.of(Time.seconds(5)))
                //.window(GlobalWindows.create())
                .trigger(CustomCountTrigger.of(2))
                .process(new ProcessWindowFunction<Version1Pojo, Object, Integer, TimeWindow>() {
                    //.process(new ProcessWindowFunction<Version1Pojo, Object, Integer, GlobalWindow>() {
                    @Override
                    public void process(Integer integer, Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {

//                        for (Iterator<Version1Pojo> it2 = elements.iterator(); it2.hasNext(); ) {
//                            System.out.println("\t" + it2.next());
//                        }
                    }
                });*/


//        stream.windowAll(CustomTumblingProcessingTimeWindows.of(Time.seconds(5)))
//                .trigger(CustomCountTrigger.of(3))
//                .allowedLateness(Time.seconds(1))
//                //.sideOutputLateData(outputTag)
//                .process(new ProcessAllWindowFunction<Version1Pojo, Object, TimeWindow>() {
//                    @Override
//                    public void process(Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {
//                        System.out.println(elements);
//                    }
//                })/*.getSideOutput(outputTag)
//                .map((MapFunction<Version1Pojo, Object>) value -> {
//                    System.out.println("侧输出 延迟数据: " + "\t" + value);
//                    return null;
//                })*/;

        stream
                .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .process(new ProcessAllWindowFunction<Version1Pojo, Object, TimeWindow>() {
                    @Override
                    public void process(Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {

                    }
                });


        System.out.println(env.getExecutionPlan());
        env.execute();
    }
}
