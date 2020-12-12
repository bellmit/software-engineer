package runtime.utils;

import org.apache.flink.configuration.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 10:52
 * @Description
 */
public class JedisPoolUtil {

    private static final String HOST = GlobalConfigUtil.redisConnectHost;
    private static final int PORT = GlobalConfigUtil.redisConnectPort;

    private static volatile JedisPool jedisPool = null;


    private JedisPoolUtil() {
    }


    /**
     * 获取RedisPool实例（单例）
     *
     * @return
     */
    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {

                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    /**
                     * 最大连接数
                     */
                    poolConfig.setMaxTotal(5);
                    /**
                     * 最大空闲连接数
                     */
                    poolConfig.setMaxIdle(0);
                    /**
                     * 最大等待时间
                     */
                    poolConfig.setMaxWaitMillis(50000);
                    /**
                     * 检查连接可用性, 确保获取的redis实例可用
                     */
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig, HOST, PORT, 10000, GlobalConfigUtil.redisConnectPassword);

                }
            }
        }
        return jedisPool;
    }
}
