import java.util.ArrayList;

/**
 * Created by hht on 2017/8/2.
 */
public abstract class Pipeline implements Runnable{
    protected volatile boolean isInterrupted = false;

    protected int index = 0;
    protected int remainCount = 0;
    protected Pool from;
    protected Pool to;
    public StopWatch stopWatch;

    public Pipeline(int index, int taskNum) {
        this.index = index;
        this.remainCount = taskNum;
    }

    public void setFrom(Pool from) {
        this.from = from;
    }

    public void setTo(Pool to) {
        this.to = to;
    }
}
