public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.testSortingOnce();
    }

    public void testSortingOnce() {
        StopWatch stopWatch = StopWatch.getNewOne();
        Sorting sorting = new Sorting();

        System.out.println("start...");

        stopWatch.start();
        sorting.sortByStraightInsertion();
        stopWatch.stop();

        System.out.println("end...");
        System.out.println("time: " + stopWatch.getElapseTotal());
    }
}
