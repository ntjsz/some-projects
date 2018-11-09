package rank.test;

import rank.RankTreeMap;

import java.util.Random;
import java.util.TreeMap;

public class RankTest {

    public static void main(String[] args) {
        //validPut();
        //validRemove();
        //performancePut();
        //performanceRemove();
        performanceRank();
    }

    private static void validPut() {
        int testNum = 10000;
        Random r = new Random();
        for (int i = 1; i < testNum; i++) {
            ValidTest putTest = new ValidTest(r.nextInt(i) + 1, r.nextInt(i) + 1);
            putTest.makeTree();
            putTest.test();
        }
    }

    private static void validRemove() {
        int testNum = 10000;
        Random r = new Random();
        for (int i = 1; i < testNum; i++) {
            ValidTest putTest = new ValidTest(r.nextInt(i) + 1, r.nextInt(i) + 1);
            putTest.makeTree();
            putTest.removeSome(r.nextInt(i) + 1, r.nextInt(i) + 1);
            putTest.test();
        }
    }

    private static void performancePut() {
        long tree = 0;
        long rank = 0;
        for (int i = 0; i < 500; i++) {
            PerformanceTest test = new PerformanceTest(100000, 5000, 500);
            test.makeTemplate();
            test.warmup();
            tree += test.put(TreeMap::new);
            rank += test.put(RankTreeMap::new);
        }

        System.out.println(tree);
        System.out.println(rank);
    }

    private static void performanceRemove() {
        long tree = 0;
        long rank = 0;
        for (int i = 0; i < 500; i++) {
            PerformanceTest test = new PerformanceTest(100000, 5000, 500);
            test.makeTemplate();
            test.warmup();
            tree += test.remove(TreeMap::new);
            rank += test.remove(RankTreeMap::new);
        }

        System.out.println(tree);
        System.out.println(rank);
    }

    private static void performanceRank() {
        long tree = 0;
        long rank = 0;
        for (int i = 0; i < 500; i++) {
            PerformanceTest test = new PerformanceTest(100000, 5000, 500);
            test.makeTemplate();
            test.warmup();
            tree += test.rankMapSlowly(TreeMap::new);
            rank += test.rankRankMap();
        }

        System.out.println(tree);
        System.out.println(rank);
    }
}
