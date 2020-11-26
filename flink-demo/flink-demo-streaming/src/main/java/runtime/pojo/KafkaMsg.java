package runtime.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @Description
 * @date 2020/5/28 16:10
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMsg {
    private Version1Pojo value;
    private String topic;
    private Integer partiton;
    private Long offset;

}