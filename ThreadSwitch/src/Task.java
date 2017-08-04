import java.util.ArrayList;

/**
 * Created by hht on 2017/8/3.
 */
public class Task implements Phase{
    private ArrayList<Sorting> list = new ArrayList<>();
    public StopWatch stopWatch = new StopWatch();
    private int stepNum = 0;

    public Task(int stepNum) {
        this.stepNum = stepNum;
        for(int i = 0; i < stepNum; i++) {
            list.add(new Sorting());
        }
        stopWatch.clear();
    }

    @Override
    public void phaseStart(int index) {
        stopWatch.stop();
        list.get(index).sortByStraightInsertion();
        stopWatch.stop();
    }
}
