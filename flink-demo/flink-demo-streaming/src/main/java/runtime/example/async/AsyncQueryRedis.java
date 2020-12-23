package runtime.example.async;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
import runtime.utils.JedisPoolUtil;
import runtime.utils.datasource.CustomSocketStream;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/11 15:56
 * @Description 异步查询 Mysql
 */
public class AsyncQueryRedis {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        DataStreamSource<String> stream = CustomSocketStream.getSocketTestData(env, "node01", 6777);

        env.setParallelism(3);
        AsyncDataStream.
                orderedWait(
                        stream,
                        new AsyncDatabaseRequest(),
                        5000,
                        TimeUnit.MILLISECONDS,
                        100)
                .print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * An implementation of the 'AsyncFunction' that sends requests and sets the callback.
     */

    static class AsyncDatabaseRequest extends RichAsyncFunction<String, Tuple2<String, String>> {

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
        public void asyncInvoke(String input, ResultFuture<Tuple2<String, String>> resultFuture) throws Exception {

            Cache<Object, Object> cache = CacheBuilder.newBuilder()
                    .maximumSize(1000)
                    .expireAfterAccess(300, TimeUnit.MILLISECONDS)
                    .expireAfterWrite(300, TimeUnit.MILLISECONDS)
                    .build();




            if (cache.get("1") == null){
                // issue the asynchronous request, receive a future for result
                final String asyncInvokeResult = client.get("asyncInvoke");
                System.out.println("1");
                cache.put("1",asyncInvokeResult);
            }

            // set the callback to be executed once the request by the client is complete
            // the callback simply forwards the result to the result future
            CompletableFuture.supplyAsync(new Supplier<String>() {
                @SneakyThrows
                @Override
                public String get() {
                    return (String) cache.get("1");
                }
            }).thenAccept((String dbResult) -> {
                resultFuture.complete(Collections.singleton(new Tuple2<>(input, dbResult)));
            });
        }

        @Override
        public void timeout(String input, ResultFuture<Tuple2<String, String>> resultFuture) throws Exception {
            System.out.println("超时");
        }
    }


}
