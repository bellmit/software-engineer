package runtime.example.async;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import runtime.utils.JedisPoolUtil;
import runtime.utils.datasource.CustomKafkaConsumer;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/22 16:34
 * @Description
 */
public class AsyncBatchSinkRedis {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(8);
        SingleOutputStreamOperator<Iterable<String>> operator = CustomKafkaConsumer.getResultMethod(env, args, "software")
                .countWindowAll(5)
                .apply(new AllWindowFunction<String, Iterable<String>, GlobalWindow>() {

                    @Override
                    public void apply(GlobalWindow window, Iterable<String> values, Collector<Iterable<String>> out) throws Exception {
                        out.collect(values);
                    }
                });


        AsyncDataStream
                .unorderedWait(
                        operator,
                        new CustomAsyncBatchSinkRedis(),
                        5000,
                        TimeUnit.MILLISECONDS,
                        10
                ).print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class CustomAsyncBatchSinkRedis extends RichAsyncFunction<Iterable<String>, String> {
        /**
         * The database specific client that can issue concurrent requests with callbacks
         */
        private transient Jedis client;

        @Override
        public void open(Configuration parameters) throws Exception {
            JedisPool pool = JedisPoolUtil.getJedisPool();
            client = pool.getResource();
        }

        @Override
        public void close() throws Exception {
            client.close();
        }

        @Override
        public void asyncInvoke(Iterable<String> input, ResultFuture<String> resultFuture) throws Exception {

            System.out.println(input);

            for (String everyNum : input) {
                String tempUK = UUID.randomUUID().toString();

                client.set(tempUK, everyNum);
                client.pexpire(tempUK, 5000);
            }
            resultFuture.complete(Collections.singleton("Sink Redis success"));

        }

        @Override
        public void timeout(Iterable<String> input, ResultFuture<String> resultFuture) throws Exception {
            System.out.println("超时");

        }
    }

}
