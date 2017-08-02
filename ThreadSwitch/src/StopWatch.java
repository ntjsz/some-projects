import java.util.ArrayList;

/**
 * Created by hht on 2017/8/2.
 */
public class StopWatch {
    private ArrayList<Long> moments = new ArrayList<>();
    private int count = 0;

    /**
     * 1000 - us
     * 1000_000 - ms
     */
    private long unit = 1000;   // us

    public static StopWatch getNewOne() {
        return new StopWatch();
    }

    public void start() {
        long current = System.nanoTime();
        moments.clear();
        moments.add(current);
    }

    public void stop() {
        long current = System.nanoTime();
        moments.add(current);
    }


    public long getElapseTotal() {
        long start = moments.get(0);
        long end = moments.get(moments.size() - 1);
        long elapse = end - start;
        return elapse / unit;
    }


    public long getNextElapse() {
        if (moments.size() < count + 2) {
            return -1;
        }

        long start = moments.get(count);
        long end = moments.get(count + 1);
        long elapse = end - start;
        count++;
        return elapse / unit;
    }
}
