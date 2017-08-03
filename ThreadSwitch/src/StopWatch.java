import java.util.ArrayList;

/**
 * Created by hht on 2017/8/2.
 */
public class StopWatch {
    private ArrayList<Long> moments;
    private int count = 0;

    /**
     * 1000 - us
     * 1000_000 - ms
     */
    private long unit = 1000;   // us

    public StopWatch() {
        moments = new ArrayList<>();
    }
    public StopWatch (int size) {
        moments = new ArrayList<>(size);
    }

    public void clear() {
        moments.clear();
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


    public void printDetails() {
        int count = 0;
        int nextLine = 20;
        while (true) {
            long time = getNextElapse();
            if(time < 0) {
                return;
            }

            System.out.print(time + ", ");
            if(count % nextLine == nextLine - 1) {
                System.out.println();
            }
            count++;
        }
    }
}
