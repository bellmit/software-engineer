package com.test;

import com.alibaba.fastjson.JSON;
import lombok.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/23 14:48
 * @Description
 */
public class CustomSourceProduct {
    static String[] TEL_FIRST;
    static String[] BEHAVIOR_STATE;
    static Random RANDOM;

    static {
        RANDOM = new Random();
        TEL_FIRST = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        BEHAVIOR_STATE = new String[]{"ABC", "ACC", "BCC"};
    }

    public static void main(String[] args) {

        Properties props = new Properties();
        //设置配置参数
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
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
        for (int i = 0; i < 100000; i++) {
            kafkaProducer.send(new ProducerRecord<>("software", JSON.toJSONString(getData())));

            // System.out.println(getData());
            kafkaProducer.flush();//刷新
            System.out.println(getData());

        }
        kafkaProducer.close();
        System.out.println(System.currentTimeMillis() - l);
    }

    public static CustomSource getData() {

            int income = RANDOM.nextInt(50000);
            return new CustomSource(BEHAVIOR_STATE[RANDOM.nextInt(BEHAVIOR_STATE.length)], TEL_FIRST[RANDOM.nextInt(TEL_FIRST.length)], income, System.currentTimeMillis());

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class CustomSource {
        private String str;
        private String stateStage;
        private Integer money;
        private Long timeStamp;
    }

}
