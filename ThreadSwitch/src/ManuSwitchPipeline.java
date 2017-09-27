/**
 * Created by hht on 2017/8/7.
 */
public class ManuSwitchPipeline extends Pipeline{

    public static final int timeSlice = 50;


    public ManuSwitchPipeline(int index, int taskNum) {
        super(index, taskNum);
        stopWatch = new StopWatch(taskNum * 2);
    }

    @Override
    public void run() {
        while (!isInterrupted) {
            long start = System.nanoTime();
            Task task= from.poll();
            if(task == null) {
                continue;
            }

            stopWatch.stop();
            task.phaseStart(index);
            to.offer(task);
            stopWatch.stop();
            remainCount--;

            long end = System.nanoTime();
            long elapse = (start - end)/1000_1000;
            if(elapse < timeSlice) {
                try {
                    Thread.sleep((int)(timeSlice - elapse));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
