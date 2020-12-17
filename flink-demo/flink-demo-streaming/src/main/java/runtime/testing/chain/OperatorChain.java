package runtime.testing.chain;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import runtime.utils.datasource.CustomSocketStream;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/14 17:36
 * @Description
 */
public class OperatorChain {

    public static void main(String[] args) {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.disableOperatorChaining();

        CustomSocketStream.getSocketTestData(env, "node01", 6377)
                .map(new MapFunction<String, Object>() {
                    @Override
                    public Object map(String value) throws Exception {
                        System.out.println(" Map Function1 " + value);
                        return value;
                    }
                })//.startNewChain()
                .filter(value -> value.equals("2"))

                .keyBy(new KeySelector<Object, String>() {
                    @Override
                    public String getKey(Object value) throws Exception {
                        return value.toString().split(",")[0];
                    }
                })
                .process(new ProcessFunction<Object, Object>() {
                    @Override
                    public void processElement(Object value, Context ctx, Collector<Object> out) throws Exception {
                        System.out.println("Process " + value);
                    }
                }).startNewChain();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
