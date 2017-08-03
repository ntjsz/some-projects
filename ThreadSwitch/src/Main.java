public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        //m.testSortingOnce();
        //m.testOnePipeline();
        m.testSomePipelines();
    }

    public void testSortingOnce() {
        StopWatch stopWatch = new StopWatch();
        Sorting sorting = new Sorting();

        System.out.println("start...");

        stopWatch.stop();
        sorting.sortByStraightInsertion();
        stopWatch.stop();

        System.out.println("end...");
        System.out.println("time: " + stopWatch.getElapseTotal());
    }

    public void testOnePipeline() {
        int num = 10;
        Pool pool0 = new Pool();
        Pool pool1 = new Pool();
        Pipeline pipeline = new AutoSwitchPipeline(0, num);
        pipeline.setFrom(pool0);
        pipeline.setTo(pool1);
        pool0.fill(num);
        Thread thread = new Thread(pipeline);
        thread.start();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(pipeline.remainCount == 0) {
                break;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pipeline.stopWatch.printDetails();
    }


    public void testSomePipelines() {
        PipelineManager manager0 = new PipelineManager(1, 10);
        manager0.init();

        PipelineManager manager1 = new PipelineManager(1, 10);
        manager1.init();

        PipelineManager manager2 = new PipelineManager(1, 10);
        manager2.init();

        PipelineManager manager3 = new PipelineManager(1, 10);
        manager3.init();

        manager0.start();
        manager1.start();
        manager2.start();
        manager3.start();
    }
}
