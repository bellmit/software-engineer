package runtime.utils.datasource;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/22 16:46
 * @Description
 */
public class CustomTopicWitchSoftWareSourceProduce {
    public static void main(String[] args) {
        Properties props = new Properties();
        //设置配置参数
        props.put("bootstrap.servers", "node01:9092");
        props.put("acks", "-1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        long l = System.currentTimeMillis();
        //1.创建kafka生产者对象 需要配置参数   <key（数据的标识，默认可以是给null）,value（发送的数据）>
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);

        //for (int i = 0; i < 10 ; i++) {
        for (int i = 0; i < 10000; i++) {
            kafkaProducer.send(new ProducerRecord<>("software", String.valueOf(i)));

            // System.out.println(getData());
            kafkaProducer.flush();//刷新

        }
        kafkaProducer.close();
        System.out.println(System.currentTimeMillis() - l);
    }
}
