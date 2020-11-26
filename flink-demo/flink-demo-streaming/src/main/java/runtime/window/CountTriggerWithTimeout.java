package runtime.window;

import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.flink.api.common.state.ReducingStateDescriptor;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 11:05
 * @Description
 */
public class CountTriggerWithTimeout<T> extends Trigger<T, TimeWindow> {

    private static Logger LOG = LoggerFactory.getLogger("CountTriggerWithTimeout");


    /**
     * 窗口最大数据量
     */
    private int maxCount;
    /**
     * event time / process time
     */
    private TimeCharacteristic timeType;

    //new ReducingStateDescriptor<>("counter",new Sum())



    @Override
    public TriggerResult onElement(T element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        return null;
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return null;
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return null;
    }

    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {

    }
}
