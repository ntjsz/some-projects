import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hht on 2017/8/3.
 */
public class Pool {

    private ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue<>();

    public Task poll() {
        return queue.poll();
    }

    public void offer(Task s) {
        queue.offer(s);
    }


    public void fill(int size) {
        for(int i = 0; i < size; i++) {
            queue.offer(new Task(1));
        }
    }

    public void fill(int size, int step) {
        for(int i = 0; i < size; i++) {
            queue.offer(new Task(step));
        }
    }
}