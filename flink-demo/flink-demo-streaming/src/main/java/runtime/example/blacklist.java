package runtime.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.MapFunction;
import runtime.utils.SplitSeparator;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/20 15:58
 * @Description
 */
public class blacklist {



    // 输入的广告点击事件样例类
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class AdClickEvent {
        //用户ID
        private Long userId;
        //广告ID
        private Long adId;
        //省份
        private String province;
        //城市
        private String city;
        //用户点击广告的时间
        private Long timeStamp;
    }


    public static class CustomMapFun implements MapFunction<String, AdClickEvent> {
        @Override
        public AdClickEvent map(String value) throws Exception {
            String[] thisLineData = value.split(SplitSeparator.SPLIT_4);
            return new AdClickEvent(Long.parseLong(thisLineData[0]), Long.parseLong(thisLineData[1]), thisLineData[2], thisLineData[3], Long.parseLong(thisLineData[4]));
        }
    }
}
