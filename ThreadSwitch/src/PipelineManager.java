import java.util.ArrayList;

/**
 * Created by hht on 2017/8/3.
 */
public class PipelineManager {
    public ArrayList<Pool> pools = new ArrayList<>();
    public ArrayList<Pipeline> pipes = new ArrayList<>();

    public int pipeNum;
    public int taskNum;

    public ArrayList<Thread> threads = new ArrayList<>();

    public PipelineManager(int pipeNum, int taskNum) {
        this.pipeNum = pipeNum;
        this.taskNum = taskNum;

    }

    public void init() {
        for(int i = 0; i < pipeNum + 1; i++) {
            pools.add(new Pool());
        }

        for(int i = 0; i < pipeNum; i++) {
            Pipeline pipeline = getPipeline(i);
            pipeline.setFrom(pools.get(i));
            pipeline.setTo(pools.get(i + 1));
            pipes.add(pipeline);
        }

        pools.get(0).fill(taskNum);

        for(int i = 0; i < pipeNum; i++) {
            Thread thread = new Thread(pipes.get(i));
            threads.add(thread);
        }
    }

    public void start() {
        for(int i = 0; i < pipeNum; i++) {
            threads.get(i).start();
        }

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(pipes.get(pipes.size() - 1).remainCount == 0) {
                break;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < pipeNum; i++) {
            System.out.println("Pipe " + i + ":");
            pipes.get(i).stopWatch.printDetails();
            System.out.println();
        }
    }

    private Pipeline getPipeline(int index) {
        return new AutoSwitchPipeline(index, taskNum);
    }
}
