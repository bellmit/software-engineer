package runtime.example.batchwrite;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import runtime.utils.publicextract.PublicExtractUtil;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/11 14:16
 * @Description 批量写入 Mysql
 */
public class BatchWriteMySql {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //DataStream<Version1Pojo> stream = CustomKafkaConsumer.getResultMethod(env, args, "T1", "Version1Pojo");

        env
                .socketTextStream("node01", 6377)
                .countWindowAll(3)
                .apply(new PublicExtractUtil.CustomWindowFunction())
                .addSink(new PublicExtractUtil.SinkMysqlFunction());


        //febalance

        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
