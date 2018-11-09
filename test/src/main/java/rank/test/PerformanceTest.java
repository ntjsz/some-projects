package rank.test;

import rank.RankTreeMap;

import java.util.*;
import java.util.function.Supplier;

public class PerformanceTest {

    private int keyMax;
    private int mapSize;
    private int testTimes;
    private Random random = new Random();

    private List<List<Integer>> template;

    public PerformanceTest(int keyMax, int mapSize, int testTimes) {
        this.keyMax = keyMax;
        this.mapSize = mapSize;
        this.testTimes = testTimes;
    }

    public void makeTemplate() {
        template = new ArrayList<>();
        for (int i = 0; i < testTimes; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < random.nextInt(mapSize); j++) {
                list.add(random.nextInt(keyMax));
            }
            template.add(list);
        }
    }

    public void warmup() {
        for (int i = 0; i < 1000; i++) {
            Map<Integer, String> treeMap = new TreeMap<>();
            Map<Integer, String> rankMap = new RankTreeMap<>();
            for (int j = 0; j < 1000; j++) {
                treeMap.put(random.nextInt(10000), null);
                rankMap.put(random.nextInt(10000), null);
            }
        }
    }

    public long put(Supplier<Map<Integer, String>> constructor) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < template.size(); i++) {
            Map<Integer, String> map = constructor.get();
            List<Integer> list = template.get(i);
            for (int j = 0; j < list.size(); j++) {
                map.put(list.get(j), null);
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long remove(Supplier<Map<Integer, String>> constructor) {
        List<Map<Integer, String>> maps = new ArrayList<>();
        for (int i = 0; i < template.size(); i++) {
            Map<Integer, String> map = constructor.get();
            List<Integer> list = template.get(i);
            for (int j = 0; j < list.size(); j++) {
                map.put(list.get(j), null);
            }
            maps.add(map);
        }

        for (int i = 0; i < template.size(); i++) {
            List<Integer> list = template.get(i);
            Collections.shuffle(list);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < maps.size(); i++) {
            Map<Integer, String> map = maps.get(i);
            for (Integer key : template.get(i)) {
                map.remove(key);
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long rankMapSlowly(Supplier<Map<Integer, String>> constructor) {
        List<Map<Integer, String>> maps = new ArrayList<>();
        for (int i = 0; i < template.size(); i++) {
            Map<Integer, String> map = constructor.get();
            List<Integer> list = template.get(i);
            for (int j = 0; j < list.size(); j++) {
                map.put(list.get(j), null);
            }
            maps.add(map);
        }

        int rank = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < maps.size(); i++) {
            Map<Integer, String> map = maps.get(i);
            for (Integer key2 : template.get(i)) {
                rank = 0;
                for (Integer key : map.keySet()) {
                    if (key.equals(key2)) break;
                    rank++;
                }
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long rankRankMap() {
        List<RankTreeMap<Integer, String>> maps = new ArrayList<>();
        for (int i = 0; i < template.size(); i++) {
            RankTreeMap<Integer, String> map = new RankTreeMap<>();
            List<Integer> list = template.get(i);
            for (int j = 0; j < list.size(); j++) {
                map.put(list.get(j), null);
            }
            maps.add(map);
        }

        int rank = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < maps.size(); i++) {
            RankTreeMap<Integer, String> map = maps.get(i);
            for (Integer key : template.get(i)) {
                rank = map.rank(key);
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
