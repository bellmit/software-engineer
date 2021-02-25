import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/20 16:28
 * @Description
 */
public class demo {
    public static void main(String[] args) throws ParseException, InterruptedException {

//        Cache<String, String> googleCache = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterAccess(300, TimeUnit.MILLISECONDS)
//                .expireAfterWrite(300, TimeUnit.MILLISECONDS)
//                .build();
//
//
//        System.out.println(googleCache);
//        googleCache.put("1","HelloCache");
//
//        try {
//            System.out.println(googleCache.get("1"));
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }



        while (true){
            System.out.println(System.currentTimeMillis() % 3600000);
            long thisTimeStamp = System.currentTimeMillis() - System.currentTimeMillis() % 3600000;
            System.out.println("thisTimeStamp + 3600000 " + (thisTimeStamp + 3600000));
            //System.out.println(System.currentTimeMillis() - System.currentTimeMillis() % 3600000);
        }


    }

}
