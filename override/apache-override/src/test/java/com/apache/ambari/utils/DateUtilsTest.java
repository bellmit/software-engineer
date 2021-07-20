package com.apache.ambari.utils;

import com.apache.amabri.utils.DateUtils;
import junit.framework.Assert;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/6/2 11:19
 * @Description
 */
public class DateUtilsTest {

    @Test
    public void testConvertToReadableTime() throws ParseException {
        // 2014-01-08 04:15:37
        Long timestamp = 1389125737000L;
        String readableTime = DateUtils.convertToReadableTime(timestamp);

        // expected format with tz set to utc
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        //parse using formatter with TZ=local and format using above formatter with TZ=UTC
        Assert.assertEquals("2014-01-08 04:15:37",
                sdf.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(readableTime)));

    }

    @Test
    public void testConvertToTimestamp() {
        Long timestamp = 1389125737000L;
        String time = "2014-01-08 04:15:37";
        String format = "yyyy-MM-dd HH:mm:ss";
        Long convertToTimestamp = DateUtils.convertToTimestamp(time, format);

        Assert.assertEquals(timestamp, convertToTimestamp);
    }

    @Test
    public void testConvertToDate() throws ParseException {
        String time = "2013-11-18T14:29:29-0000";
        Date convertToDate = DateUtils.convertToDate(time);

        Assert.assertEquals("Mon Nov 18 22:29:29 CST 2013",convertToDate);
    }

}
