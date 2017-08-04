import java.awt.*;

/**
 * Created by hht on 2017/8/3.
 */
public class AutoSwitchPipeline extends Pipeline{
    public AutoSwitchPipeline(int index, int taskNum) {
        super(index, taskNum);
        stopWatch = new StopWatch(taskNum * 2);
    }

    @Override
    public void run() {
        while (!isInterrupted) {
            Task task= from.poll();
            if(task == null) {
                continue;
            }

            stopWatch.stop();
            task.phaseStart(index);
            to.offer(task);
            stopWatch.stop();
            remainCount--;
        }
    }
}
