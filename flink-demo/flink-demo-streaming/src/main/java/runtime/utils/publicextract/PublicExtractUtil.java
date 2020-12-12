package runtime.utils.publicextract;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import runtime.utils.JedisPoolUtil;
import runtime.utils.SplitSeparator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 11:24
 * @Description
 */
public class PublicExtractUtil {

    /**
     * Apply Function
     */
    public static class CustomWindowFunction implements AllWindowFunction<String, String, GlobalWindow> {

        @Override
        public void apply(GlobalWindow window, Iterable<String> values, Collector<String> out) throws Exception {
            for (Iterator<String> iterator = values.iterator(); iterator.hasNext(); ) {
                String next = iterator.next();
                System.out.println(next);
                out.collect(next);
            }
        }
    }

    // ------------------------------------------------------------------------
    //  Sink Function  {Sink Mysql  Sink Redis}
    // ------------------------------------------------------------------------


    /**
     * Sink Mysql Function
     */
    public static class SinkMysqlFunction extends RichSinkFunction<String> {

        private Connection conn;

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            this.conn = new ComboPooledDataSource().getConnection();
            //this.conn = new SingletonClass().getConnection();
        }

        @Override
        public void invoke(String value) throws Exception {
            PreparedStatement statement = this.conn.prepareStatement(SplitSeparator.BATCH_WRITE_MYSQL);
            statement.setString(1, value);
            statement.executeUpdate();
        }
    }

    /**
     * Sink Redis Function
     */
    public static class SinkRedisFunction extends RichSinkFunction<String> {

        private Jedis jedis;

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            JedisPool pool = JedisPoolUtil.getJedisPool();
            this.jedis = pool.getResource();
        }

        @Override
        public void invoke(String value) throws Exception {
            jedis.set("socketStream", value);
        }

        @Override
        public void close() throws Exception {
            super.close();
        }
    }


}
