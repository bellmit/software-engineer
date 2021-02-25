package runtime.example.outputtag;

import com.alibaba.fastjson.JSON;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import runtime.utils.JedisPoolUtil;
import runtime.utils.TimeUtil;
import runtime.utils.datasource.CustomSocketStream;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/1/5 10:46
 * @Description
 */
public class CustonPipelinedSinkRedis {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        CustomSocketStream.getSocketTestData(env, "node01", 8899)
                .countWindowAll(5)
                .process(new ProcessAllWindowFunction<String, Object, GlobalWindow>() {
                    @Override
                    public void process(Context context, Iterable<String> elements, Collector<Object> out) throws Exception {
                        Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
                        Pipeline pipeline = jedis.pipelined();

                        for (String everyTryPojo : elements) {
                            System.out.println(everyTryPojo);
                            TimeUnit.MILLISECONDS.sleep(1);
                            pipeline.hset("aaa:", TimeUtil.GET_YYYY_MM_DD_HH_MM_SS_SSS(), everyTryPojo);
                        }

                        pipeline.sync();
                    }
                });


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
