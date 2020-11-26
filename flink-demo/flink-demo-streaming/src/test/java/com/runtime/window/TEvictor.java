package com.runtime.window;

import org.apache.flink.api.common.state.ReducingState;
import org.apache.flink.streaming.api.windowing.evictors.Evictor;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.streaming.runtime.operators.windowing.TimestampedValue;

import java.util.Iterator;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 16:47
 * @Description
 */
public class TEvictor {
    public static final long maxCount = 5;


    public static void main(String[] args) {
    }

    private static void evict(Iterable<TimestampedValue<Object>> elements, int size, Evictor.EvictorContext ctx) {
        if (size <= maxCount) {
            return;
        } else {
            int evictedCount = 0;
            for (Iterator<TimestampedValue<Object>> iterator = elements.iterator(); iterator.hasNext(); ) {
                iterator.next();
                evictedCount++;
                if (evictedCount > size - maxCount) {
                    break;
                } else {
                    iterator.remove();
                }
            }
        }
    }

}
