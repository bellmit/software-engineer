package runtime.testing.window.code;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ProcessingTimeTrigger;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/27 10:24
 * @Description
 */
public class CustomTumblingProcessingTimeWindows extends WindowAssigner<Object, TimeWindow> {
    private static final long serialVersionUID = 1L;

    private final long size;

    private final long offset;

    private CustomTumblingProcessingTimeWindows(long size, long offset) {
        if (Math.abs(offset) >= size) {
            throw new IllegalArgumentException("TumblingProcessingTimeWindows parameters must satisfy abs(offset) < size");
        }

        this.size = size;
        this.offset = offset;
    }

    @Override
    public Collection<TimeWindow> assignWindows(Object element, long timestamp, WindowAssignerContext context) {
        final long now = context.getCurrentProcessingTime();
        long start = TimeWindow.getWindowStartWithOffset(now, offset, size);
        System.out.println("timestamp" + timestamp + "\t" + "窗口开始时间: " + start + "\t" + "窗口结束时间: " + start + size);

        return Collections.singletonList(new TimeWindow(start, start + size));
    }

    public long getSize() {
        return size;
    }

    @Override
    public Trigger<Object, TimeWindow> getDefaultTrigger(StreamExecutionEnvironment env) {
        return ProcessingTimeTrigger.create();
    }

    @Override
    public String toString() {
        return "TumblingProcessingTimeWindows(" + size + ")";
    }

    /**
     * Creates a new {@code TumblingProcessingTimeWindows} {@link WindowAssigner} that assigns
     * elements to time windows based on the element timestamp.
     *
     * @param size The size of the generated windows.
     * @return The time policy.
     */
    public static CustomTumblingProcessingTimeWindows of(Time size) {
        return new CustomTumblingProcessingTimeWindows(size.toMilliseconds(), 0);
    }

    /**
     * Creates a new {@code TumblingProcessingTimeWindows} {@link WindowAssigner} that assigns
     * elements to time windows based on the element timestamp and offset.
     *
     * <p>For example, if you want window a stream by hour,but window begins at the 15th minutes
     * of each hour, you can use {@code of(Time.hours(1),Time.minutes(15))},then you will get
     * time windows start at 0:15:00,1:15:00,2:15:00,etc.
     *
     * <p>Rather than that,if you are living in somewhere which is not using UTC±00:00 time,
     * such as China which is using UTC+08:00,and you want a time window with size of one day,
     * and window begins at every 00:00:00 of local time,you may use {@code of(Time.days(1),Time.hours(-8))}.
     * The parameter of offset is {@code Time.hours(-8))} since UTC+08:00 is 8 hours earlier than UTC time.
     *
     * @param size   The size of the generated windows.
     * @param offset The offset which window start would be shifted by.
     * @return The time policy.
     */
    public static CustomTumblingProcessingTimeWindows of(Time size, Time offset) {
        return new CustomTumblingProcessingTimeWindows(size.toMilliseconds(), offset.toMilliseconds());
    }

    @Override
    public TypeSerializer<TimeWindow> getWindowSerializer(ExecutionConfig executionConfig) {
        return new TimeWindow.Serializer();
    }

    @Override
    public boolean isEventTime() {
        return false;
    }
}
