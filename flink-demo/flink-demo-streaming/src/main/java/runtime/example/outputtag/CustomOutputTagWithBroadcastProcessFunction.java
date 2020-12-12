package runtime.example.outputtag;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import runtime.utils.datasource.CustomSocketStream;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 9:35
 * @Description
 */
public class CustomOutputTagWithBroadcastProcessFunction {
    static MapStateDescriptor<String, String> mapStateDescriptor = new MapStateDescriptor<String, String>("", Types.STRING, Types.STRING);
    static OutputTag<String> tag = new OutputTag<>("nonMatchKeyWord", Types.STRING);

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataStreamSource<String> stream = CustomSocketStream.getSocketTestData(env, "node01", 6377);
        BroadcastStream<String> broadStream = CustomSocketStream.getSocketTestData(env, "node01", 6378).broadcast(mapStateDescriptor);


        stream
                .connect(broadStream)
                .process(new BroadcastProcessFunction<String, String, Object>() {
                    @Override
                    public void processElement(String value, ReadOnlyContext ctx, Collector<Object> out) throws Exception {
                        ctx.output(tag, value);
                    }

                    @Override
                    public void processBroadcastElement(String value, Context ctx, Collector<Object> out) throws Exception {
                        ctx.getBroadcastState(mapStateDescriptor).put("key", value);
                    }
                })
                .getSideOutput(tag)
                .map((MapFunction<String, Object>) value -> {
                    System.out.println("没有匹配上的 KeyWord 有" + value);
                    return null;
                });


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


