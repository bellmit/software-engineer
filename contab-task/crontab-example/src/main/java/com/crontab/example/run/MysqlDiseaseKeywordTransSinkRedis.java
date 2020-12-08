package com.crontab.example.run;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/3 15:22
 * @Description Mysql 疾病 Keyword 经过 转换 写入到 Redis中 更新时长 1h
 */
public class MysqlDiseaseKeywordTransSinkRedis {
    private static final String HOST = "node03";
    private static final int PORT = 6379;
    private static volatile JedisPool jedisPool = null;
    private static final String PASS = "RedssN0De03..scss.u1p.!";

    private static final String RedisHk = "atm";


    public static void main(String[] args) throws SQLException {
        MysqlDiseaseSinkRedis(getAllMap(), 60 * 60 * 12);
        System.out.println(getAllMap());
        System.exit(0);
    }


    /**
     * 获取RedisPool实例（单例）
     *
     * @return RedisPool实例
     */
    public static JedisPool getJedisPoolInstance() {
        if (jedisPool == null) {
            synchronized (MysqlDiseaseKeywordTransSinkRedis.class) {
                if (jedisPool == null) {

                    /**
                     *       // 最大连接数
                     *      // 最大空闲连接数
                     *     // 最大等待时间
                     *    // 检查连接可用性, 确保获取的redis实例可用
                     */

                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(5);
                    poolConfig.setMaxIdle(0);
                    poolConfig.setMaxWaitMillis(50000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig, HOST, PORT, 10000, PASS);
                }
            }
        }

        return jedisPool;
    }

    /**
     * Mysql 疾病数据 写入到 redis
     * @param diseaseMap
     * @param TTL
     */
    public static void MysqlDiseaseSinkRedis(HashMap<String, String> diseaseMap, int TTL) {
        Jedis jedis = null;
        try {
            jedis = getJedisPoolInstance().getResource();

            Pipeline pipeline = jedis.pipelined();

            for (String everyKey : diseaseMap.keySet()) {
                String everyDiseaseIndex = diseaseMap.get(everyKey);
                pipeline.hset(RedisHk, everyKey, everyDiseaseIndex.substring(0, everyDiseaseIndex.length() - 1));
            }

            pipeline.syncAndReturnAll();
            //jedis.expire("atm", TTL);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取Mysql 疾病 Keyword 数据
     * @return Tuple2(Id,keyword)
     * @throws SQLException
     */
    public static ArrayList<Tuple2<Integer, String>> getMysqlDiseaseAllSource() throws SQLException {

        Connection conn = new ComboPooledDataSource().getConnection();

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM health_needs_illness");
        ResultSet query = statement.executeQuery();
        //  封装所有疾病数据
        ArrayList<Tuple2<Integer, String>> allDiseaseSource = new ArrayList<>();

        while (query.next()) {
            int id = query.getInt("id");
            String illness_keyWord = query.getString("illness_keyword");
            allDiseaseSource.add(new Tuple2<>(id, illness_keyWord));
        }
        try {
            conn.close();
            statement.close();
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDiseaseSource;
    }

    /**
     * 将 Tuple2 数据转换成 去重后的 HashMap
     * @return
     * @throws SQLException
     */
    public static HashMap<String, String> getAllMap() throws SQLException {
        ArrayList<Tuple2<Integer, String>> list = MysqlDiseaseKeywordTransSinkRedis.getMysqlDiseaseAllSource();
        HashMap<String, String> map = new HashMap<>();

        //  遍历List 集合
        for (Tuple2<Integer, String> everyDisease : list) {
            //  获取每一个疾病 对应的 Keyword
            //  遍历 每一个 keyword
            String[] evertDiseaseKeyWord = everyDisease.getA2().split(",");
            for (int i = 0; i < evertDiseaseKeyWord.length; i++) {
                // 遍历List 集合
                for (Tuple2<Integer, String> tuple2 : list) {
                    // 从中找出包含当前关键字的 疾病
                    if (tuple2.getA2().contains(evertDiseaseKeyWord[i])) {
                        //  存在则 更新key
                        if (map.containsKey(evertDiseaseKeyWord[i])) {
                            map.put(evertDiseaseKeyWord[i], map.get(evertDiseaseKeyWord[i]) + "," + everyDisease.getA1());
                            //map.put(evertDiseaseKeyWord[i], new HashSet<>().add(everyDisease.f0));
                        } else {//  不存在则直接插入
                            map.put(evertDiseaseKeyWord[i], everyDisease.getA1() + "");
                        }
                    }

                }
            }
        }
        HashMap<String, String> newHashMap = new HashMap<>(16);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String[] everyDiseaseIndexAll = entry.getValue().split(",");
            HashSet<String> set = new HashSet<>();
            for (String everyIndex : everyDiseaseIndexAll) {
                set.add(everyIndex);
            }
            String tempSet = "";
            for (String everySetValue : set) {
                tempSet += (everySetValue + ",");
            }

            newHashMap.put(entry.getKey(), tempSet);
        }
        return newHashMap;
    }

    /**
     * Class Pojo
     *
     * @param <K>
     * @param <V>
     */
    public static class Tuple2<K, V> {
        private K a1;
        private V a2;

        public Tuple2(K a1, V a2) {
            this.a1 = a1;
            this.a2 = a2;
        }

        public K getA1() {
            return a1;
        }

        public V getA2() {
            return a2;
        }

    }


}
