package trans;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/20 22:27
 * @Description
 */
public class StreamingSourceFromCollection {
    public static void main(String[] args) throws Exception {
        //步骤一：获取环境变量
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //步骤二：模拟数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("hadoop");
        data.add("spark");
        data.add("flink");
        //步骤三：获取数据源
        DataStreamSource<String> dataStream = env.fromCollection(data); //步骤四：transformation操作 SingleOutputStreamOperator<String> addPreStream = dataStream.map(new MapFunction<String, String>() { @Override public String map(String word) throws Exception { return "kaikeba_" + word; } }); //步骤五：对结果进行处理（打印） addPreStream.print().setParallelism(1); //步骤六：启动程序 env.execute("StreamingSourceFromCollection"); } }

    }
}