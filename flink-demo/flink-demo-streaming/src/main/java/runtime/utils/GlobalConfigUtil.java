package runtime.utils;

import java.util.ResourceBundle;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @Description
 * @date 2019/12/14 13:53
 */
public class GlobalConfigUtil {


    //获取一个资源加载器
    //资源加载器会自动加载CLASSPATH中的application.properties配置文件
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    public static String kafkaBootstrapServers = resourceBundle.getString("kafka.bootstrap.servers");
//    public static String kafkaZookeeperConnect = resourceBundle.getString("kafka.zookeeper.connect");
//    public static String kafkaInputTopic = resourceBundle.getString("kafka.input.topic1");
//    public static String kafkaInputTopic_2 = resourceBundle.getString("kafka.input.topic2");
//    public static String groupId = resourceBundle.getString("group.id");
//    public static String checkPointHdfsDir = resourceBundle.getString("checkPointHdfs.Dir");
//    // public static String kafkaOutputTopic = resourceBundle.getString("kafka.output.topic");
//    //public static String KeyTypeRule = resourceBundle.getString("Key.typeRule");
//

    public static String redisConnectHost = resourceBundle.getString("Redis.connect.host");
    public static int redisConnectPort = Integer.parseInt(resourceBundle.getString("Redis.connect.port"));
    public static String redisConnectPassword = resourceBundle.getString("Redis.connect.password");


    //public static String DEFAULT_SEND_KAFKA = resourceBundle.getString("Default.send.kafka");



}
