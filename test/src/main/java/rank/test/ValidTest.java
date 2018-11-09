package rank.test;

import rank.RankTreeMap;

import java.util.Map;
import java.util.Random;

public class ValidTest {

    private int max;
    private int count;
    private RankTreeMap<Integer, String> map;

    public ValidTest(int max, int count) {
        this.max = max;
        this.count = count;
    }

    public RankTreeMap<Integer, String> makeTree() {
        map = new RankTreeMap<>();
        Random random = new Random();
        for(int i = 0; i < count; i++) {
            int key = random.nextInt(max);
            map.put(key, null);
        }
        return map;
    }

    public void removeSome(int max, int count) {
        Random random = new Random();
        for(int i = 0; i < count; i++) {
            int key = random.nextInt(max);
            map.remove(key);
        }
    }

    public void test() {
        int rank = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if(RankTreeMap.getRank(entry) != rank) {
                System.out.println("错啦");
                return;
            }
            rank++;
        }
    }
}
