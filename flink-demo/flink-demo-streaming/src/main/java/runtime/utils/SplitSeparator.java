package runtime.utils;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/11 14:55
 * @Description
 */
public interface SplitSeparator {

    String SPLIT_1 = "-";
    String SPLIT_2 = "";
    String NORMAL_STR = "正常";
    String SPLIT_4 = ",";
    String SPLIT_5 = "#";
    String SPLIT_6 = " ";


    String LIGHT = "轻";
    String OVERWEIGHT = "超重";
    String MILD_OBESITY = "轻度肥胖";
    String MODERATE_OBESITY = "中度肥胖";
    String SEVERE_OBESITY = "重度肥胖";

    String HEARTBEAT_SLOW = "心跳缓慢";
    String HEARTBEAT_TOO_FAST = "心跳过快";

    /**
     * todo 拼接时/切分时 分隔符
     */

    String SPLICING_SPLIT_MARK = "#CSC#";
    String SPLICING_SPLIT_MARK_TWO = "#CS-#";
    String SPLICING_SPLIT_MARK_THREE = "%";
    String SPLICING_SPLIT_MARK_FIVE = "#@SM@#";
    String SPLICING_SPLIT_MARK_SIX = "#CSCS#";
    String SPLICING_SPLIT_SCSS = "#scss#";


    /**
     * todo  执行的SQL语句
     */
    String QUERY_MYSQL_HEALTH_NEEDS_ILLNESS_SQL = "SELECT * FROM health_needs_illness";
    String SYMPTOMS_ANALYZE_RESULTS = "INSERT INTO health_score_results ( user_id, issue_keyword, issue, health_needs, health_needs_id, family_id, user_family_id, score, analyze_results, height, weight, result_array, `status`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String QUERY_MYSQL_KAFKA_BACKUPS_1 = "SELECT backups FROM kafka_backups WHERE user_id = ";
    String QUERY_MYSQL_KAFKA_BACKUPS_2 = " AND status = 7 ORDER BY `create_time` DESC LIMIT 10";

    String BATCH_WRITE_MYSQL = "INSERT INTO batch_write_mysql (user_value_info) VALUES (?)";


    /**
     * todo  广播时线程睡眠的时间
     */
    Long THREAD_TIME_STAMP = 1000 * 60 * 30L;


    /**
     * todo  通过长度  ;
     */
    String HEALTHY = "健康";
    String SUB_HEALTH = "亚健康";
    String DANGER = "危险";
    String HIGH_RISK = "高危";
    String FIRST_AID = "急救";


    /**
     * todo HealthNeedsIllnessPojo 结果的封装
     */

    String ANALYSIS_ILLNESS_NAME_ONE = "高风险项";
    String ANALYSIS_ILLNESS_NAME_TWO = "疾病名称";

    String ANALYSIS_ILLNESS_DESCRIBE = "疾病描述";
    String ANALYSIS_DIAGNOSTIC_METHOD = "诊断方法";
    String ANALYSIS_PREVENTION_METHOD = "预防方法";
    String ANALYSIS_ADVICE_DRUG = "建议中成药";
    String ANALYSIS_ADVICE_DIET = "建议饮食";
    String ANALYSIS_SPORTS = "建议运动";
    String ANALYSIS_COMPLICATION = "常见并发症";
    String ANALYSIS_ADVICE_PRESCRIPTION = "建议药方";

    /**
     * 分数 封装 上下浮动分数
     */
    String ANALYSIS_LOWER = "相比上次健康下降";
    String ANALYSIS_UPPER = "相比上次健康上升";
    String ANALYSIS_EQUAL = "当前状态良好,继续保持";
    String ANALYSIS_Str = "分";

    /**
     * 超过人数
     */
    String ANALYSIS_SAME_STAGE = "超过同阶段人";
    String ANALYSIS_WHOLE_COUNTRY = "超过全国同阶段人";
    String ANALYSIS_FRIEND = "超过朋友同阶段人";


    /**
     * todo 特殊值
     */
    String ERROR_INFO = "异常";

    /**
     * todo 封装数据时的Key
     */
    String KEY_1 = "info1";
    String KEY_2 = "info2";


    /**
     * todo 写入redis
     */

    String UKR1 = "UA1:";
    String UKR2 = "UA2:";
    String ANALYSIS_STR = "analysis";
    String SINGLE = "single:";


}
