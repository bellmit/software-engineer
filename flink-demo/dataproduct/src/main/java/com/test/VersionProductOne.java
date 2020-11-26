package com.test;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 14:11
 * @Description
 */
public class VersionProductOne {

    static String BASE;
    static String FIRST_NAME;
    static String GIRL;
    static String BOY;
    static String[] PRO;
    static String[] EMAIL_SUFFIX;
    static String[] TEL_FIRST;
    static Random RANDOM;
    static String[] BEHAVIOR_STATE;
    static int[][] RANGE;
    static String[] PROVINCES;
    static String[] ASYNIOINCOME;

    static {


        // ip范围
        RANGE = new int[][]{{607649792, 608174079},// 36.56.0.0-36.63.255.255
                {1038614528, 1039007743},// 61.232.0.0-61.237.255.255
                {1783627776, 1784676351},// 106.80.0.0-106.95.255.255
                {2035023872, 2035154943},// 121.76.0.0-121.77.255.255
                {2078801920, 2079064063},// 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497},// 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785},// 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137},// 182.80.0.0-182.92.255.255
                {-1236271104, -1235419137},// 182.80.0.0-182.92.255.255
                {-770113536, -768606209},// 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        EMAIL_SUFFIX = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
        BASE = "abcdefghijklmnopqrstuvwxyz0123456789";
        FIRST_NAME = "赵钱孙李周吴郑王冯陈褚卫";
        GIRL = "秀娟英华慧巧美";
        BOY = "伟刚勇毅俊峰强";
        PRO = "重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街".split(",");
        TEL_FIRST = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        RANDOM = new Random();
        BEHAVIOR_STATE = new String[]{"ABC", "ACC", "BCC"};

        PROVINCES = new String[]{
                "11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82"
        };

        ASYNIOINCOME = new String[]{
                "11111", "22222", "33333", "44444", "55555", "66666"
        };


    }


    public static void main(String[] args) throws InterruptedException {


        Properties props = new Properties();
        //设置配置参数
        props.put("bootstrap.servers", "node01:9092");
        props.put("acks", "0");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //1.创建kafka生产者对象 需要配置参数   <key（数据的标识，默认可以是给null）,value（发送的数据）>
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);

        //for (int i = 0; i < 10 ; i++) {
        for (int i = 0; i < 5; i++) {
//        for (; ; ) {
            // Thread.sleep(5000);
            //Thread.sleep(300);
            // kafkaProducer.send(new ProducerRecord<>("DDL", JSON.toJSONString(getData())));
            kafkaProducer.send(new ProducerRecord<>("test2", JSON.toJSONString(getData())));
            kafkaProducer.flush();//刷新
            System.out.println(getData());
        }
    }


    public static HashMap<Object, Object> testSqlApi() {

        HashMap<Object, Object> map = new HashMap<>();

        map.put("userId", RANDOM.nextInt(5));
        map.put("money", RANDOM.nextInt(30000));
        map.put("timeStamp", System.currentTimeMillis());
        //map.put("")

        return map;
    }


    /**
     * 返回中文姓名
     */
    private static String name_sex = "";

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 返回Email
     *
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getEmail(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * BASE.length());
            sb.append(BASE.charAt(number));
        }
        sb.append(EMAIL_SUFFIX[(int) (Math.random() * EMAIL_SUFFIX.length)]);
        return sb.toString();
    }

    /**
     * 返回手机号
     */
    private static String getPhone() {
        int index = getNum(0, TEL_FIRST.length - 1);
        String first = TEL_FIRST[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 获取中文名称
     *
     * @return
     */
    private static String getChineseName() {
        int index = getNum(0, FIRST_NAME.length() - 1);
        String first = FIRST_NAME.substring(index, index + 1);
        int sex = getNum(0, 1);
        String str = BOY;
        int length = BOY.length();
        if (sex == 0) {
            str = GIRL;
            length = GIRL.length();
            name_sex = "女";
        } else {
            name_sex = "男";
        }
        index = getNum(0, length - 1);
        String second = str.substring(index, index + 1);
        int hasThird = getNum(0, 1);
        String third = "";
        if (hasThird == 1) {
            index = getNum(0, length - 1);
            third = str.substring(index, index + 1);
        }
        return first + second + third;
    }

    //生成ip
    public static String getIp() {
        int index = RANDOM.nextInt(10);
        String ip = num2ip(RANGE[index][0] + RANDOM.nextInt(RANGE[index][1] - RANGE[index][0]));
        return ip;
    }

    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (ip >> 24) & 0xff;
        b[1] = (ip >> 16) & 0xff;
        b[2] = (ip >> 8) & 0xff;
        b[3] = ip & 0xff;
        x = b[0] + "." + b[1] + "." + b[2] + "." + b[3];

        return x;
    }


    public static String getIdentityCard() {
        String id = "420222199204179999";
        // 随机生成省、自治区、直辖市代码 1-2

        String province = randomOne(PROVINCES);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirth(20, 50);
        // 随机生成顺序号 15-17(随机性别)
        String no = RANDOM.nextInt(899) + 100 + "";
        // 随机生成校验码 18
        String[] checks = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X"};
        String check = randomOne(checks);
        // 拼接身份证号码
        id = province + city + county + birth + no + check;
        return id;

    }

    /**
     * 从String[] 数组中随机取出其中一个String字符串
     */
    public static String randomOne(String[] s) {
        return s[RANDOM.nextInt(s.length - 1)];
    }

    /**
     * 返回地址
     */
  /* private static String getAddress() {
       int index = getNum(0, road.length - 1);
       String first = road[index];
       String second = getNum(11, 150) + "号";
       String third = "-" + getNum(1, 20) + "-" + getNum(1, 10);
       return first + second + third;
   }*/

    /**
     * 随机生成两位数的字符串（01-max）,不足两位的前面补0
     */
    public static String randomCityCode(int max) {
        int i = RANDOM.nextInt(max) + 1;
        return i > 9 ? i + "" : "0" + i;
    }

    /**
     * 随机生成minAge到maxAge年龄段的人的生日日期
     */
    public static String randomBirth(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + RANDOM.nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());

    }


    /**
     * 数据封装
     */
    public static Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        int income = RANDOM.nextInt(50000);
        /*if (income < 500) {
            income = income * 2 + 300;
        }*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");

        /**
         *  用户id    姓名  手机号 地址  性别  订单编号    订单金额    订单类型    ip  身份证 邮箱  收入  时间戳 设置日期格式
         */

        map.put("userId", RANDOM.nextInt(30) + "");
        map.put("userName", getChineseName());
        map.put("phone", getPhone());
        //map.put("address", getAddress());
        map.put("sex", name_sex);
        map.put("orderId", df.format(new Date()) + "#csc#" + UUID.randomUUID());
        map.put("orderMoney", (income / 5 - 1) + "");
        map.put("orderType", (RANDOM.nextInt(3) + 1) + "");
        map.put("ip", getIp());
        map.put("identityCard", getIdentityCard());
        map.put("CommodityType", RANDOM.nextInt(31) + "");
        map.put("email", getEmail(6, 15));
        //map.put("income", income + "");
        map.put("income", ASYNIOINCOME[RANDOM.nextInt(6)]);
        map.put("behaviorState", BEHAVIOR_STATE[RANDOM.nextInt(2)]);
        map.put("timestamp", System.currentTimeMillis() + "");
        return map;
    }


    /**
     * @param MinLon：最小经度 MaxLon： 最大经度
     *                    MinLat：最小纬度
     *                    MaxLat：最大纬度
     * @return @throws
     * @Description: 在矩形内随机生成经纬度
     */
    public static Map<String, String> randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        Map<String, String> map = new HashMap<String, String>();
        map.put("J", lon);
        map.put("W", lat);
        return map;
    }

}

