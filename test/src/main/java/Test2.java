import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.HashMap;
import java.util.Map;

public class Test2 {

    private volatile Map<Integer, Integer> map;

    public Map<Integer, Integer> getMap() {
        if(map == null) {
            synchronized (this) {
                if(map == null) {
                    map = new HashMap<>();
                    map.put(1,1);
                }
            }
        }
        return map;
    }

    public void read() {
        try {
            getMap().get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        map = null;
    }

    public static void main(String[] args) {
        Test2 t = new Test2();
        Thread t1 = new Thread(() -> {
            while (true) {
                t.read();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
/*                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                t.clear();
            }
        });

        t1.start();
        t2.start();
    }
}
