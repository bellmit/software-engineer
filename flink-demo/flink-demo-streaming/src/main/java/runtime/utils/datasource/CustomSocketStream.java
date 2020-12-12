package runtime.utils.datasource;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 9:28
 * @Description
 */
public class CustomSocketStream {

    public static DataStreamSource<String> getSocketTestData(StreamExecutionEnvironment env, String host, int port) {
        return env.socketTextStream(host,port);
    }

}
