package runtime.pojo;

import com.alibaba.fastjson.JSON;
import lombok.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 14:21
 * @Description
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Version1Pojo {
    private long income;
    private int orderType;
    private long orderMoney;
    private long phone;
    private String orderId;
    private String ip;
    private String identityCard;
    private String userName;
    private int userId;
    private String email;
    private String CommodityType;
    private long timestamp;
    private String behaviorState;
    private String Str;

    public static Version1Pojo jsonFromClass(String eventStr) {
        try {
            return JSON.parseObject(eventStr, Version1Pojo.class);
        } catch (Exception e) {
            System.out.println("Json 异常");
            return new Version1Pojo();
        }
    }


}