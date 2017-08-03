import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hht on 2017/8/3.
 */
public class Pool {

    private ConcurrentLinkedQueue<Sorting> queue = new ConcurrentLinkedQueue<>();

    public Sorting poll() {
        return queue.poll();
    }

    public void offer(Sorting s) {
        queue.offer(s);
    }


    public void fill(int size) {
        for(int i = 0; i < size; i++) {
            queue.offer(new Sorting());
        }
    }
}