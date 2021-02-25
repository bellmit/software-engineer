package runtime.testing.state;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import runtime.utils.CheckpointRestoreByIDEUtils;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/24 15:17
 * @Description
 */
public class SavePointRestoreJob {
    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        CheckpointRestoreByIDEUtils.run(env.getStreamGraph(),"");




        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
