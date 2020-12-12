package runtime.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.istack.NotNull;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/11 17:05
 * @Description
 */
public class SingletonClass {


    private static Properties PROPS = new Properties();
    private static KafkaProducer<String, String> kafkaProducer = null;

    private static ComboPooledDataSource dataSource;
    private int i = 0;


    public SingletonClass() {
        dataSource = new ComboPooledDataSource();
        System.out.println("SingletonClass被初始化 " + ++i + " 次" + "ComboPooledDataSource 初始化一次");
    }

    /**
     * @return 返回DataSource
     */
    private DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @return 获取Connect连接
     */
    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     *
     * @param conn      不为空
     * @param statement 可为空
     * @param resultSet 可为空
     */
    public void close(@NotNull Connection conn, @Nullable PreparedStatement statement, @Nullable ResultSet resultSet) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void threadPool() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

//        executor.scheduleAtFixedRate(
//                () -> {
//
//                    //kafkaProducer.send(new ProducerRecord<String, String>(GlobalConfigUtil.kafkaInputTopic, GlobalConfigUtil.DEFAULT_SEND_KAFKA));
//                    kafkaProducer.send(new ProducerRecord<>(GlobalConfigUtil.kafkaInputTopic,
//                            "{\"age\":23,\"days\":0,\"depression\":\"#\",\"ear\":\"耳廓变红\",\"faceImg\":\"http://dsj.tstzyls.com/image/face/39391602050735677.png?Expires=1917410735&OSSAccessKeyId=LTAI4FjJMmm6QXg8tZKV5Hjn&Signature=vMaJ4JBA03cRfeDDvhFRqzEsGRA%3D\",\"familyId\":5,\"healthGoals\":\"耳鼻喉\",\"heartRate\":\"85\",\"height\":155,\"oldScore\":88,\"sex\":2,\"specificAppeal\":\"感冒了，喉咙咳嗽，耳朵发痒\",\"status\":0,\"tongueImg\":\"http://dsj.tstzyls.com/image/tongue/96191602050735597.png?Expires=1917410735&OSSAccessKeyId=LTAI4FjJMmm6QXg8tZKV5Hjn&Signature=ot6x9iWtDcFyVA0HTjJkqLZxatM%3D\",\"userFamilyId\":7,\"userId\":258369,\"viscera\":\"肝胆湿热证\",\"weight\":45}"
//                    ));
//                    kafkaProducer.flush();//刷新
//                    //kafkaProducer.close();
//                }, 0, 2, TimeUnit.HOURS);

    }

}
