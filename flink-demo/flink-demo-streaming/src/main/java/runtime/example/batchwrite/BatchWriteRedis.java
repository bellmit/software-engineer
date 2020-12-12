package runtime.example.batchwrite;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import runtime.utils.publicextract.PublicExtractUtil;
import runtime.utils.datasource.CustomSocketStream;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 10:47
 * @Description
 */
public class BatchWriteRedis {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        CustomSocketStream.getSocketTestData(env, "node01", 6377)
                .countWindowAll(3)
                .apply(new PublicExtractUtil.CustomWindowFunction())
                .addSink(new PublicExtractUtil.SinkRedisFunction());


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
