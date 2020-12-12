package runtime.example.outputtag;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import runtime.utils.datasource.CustomSocketStream;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 9:28
 * @Description
 */
public class CustomOutputTagWithProcessFunction {
    public static void main(String[] args) {

        OutputTag<String> tag = new OutputTag<>("nonMatchKeyWord", Types.STRING);


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        CustomSocketStream.getSocketTestData(env, "node01", 6377)
                .process(
                        new ProcessFunction<String, String>() {
                            @Override
                            public void processElement(String value, Context ctx, Collector<String> out) throws Exception {
                                ctx.output(tag, value);
                            }
                        }
                ).getSideOutput(tag)
                .print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
