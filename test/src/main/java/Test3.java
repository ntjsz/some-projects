import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_TIME;

public class Test3 {
    public static void main(String[] args) {
        //System.out.println(tableSizeFor(4));
        test4();

        /*int[] d = new int[3];
        d[0] = 1;
        System.out.println(d[0]);
        d[0]--;
        System.out.println(d[0]);*/
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    static void test0() {
        LocalTime time = LocalTime.parse("07:00", ISO_TIME);
        System.out.println(time);
        long startTime =
                LocalDateTime.now()
                        .withHour(time.getHour())
                        .withMinute(time.getMinute())
                        .withSecond(time.getSecond())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli();
        System.out.println(startTime);
    }

    static void test1() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if(entry.getKey().equals(2)) {
                iterator.remove();
            }
        }
        System.out.println(map);
    }


    static void test2() {
        B b = new B(1, 2, 3);
        try {
            Field field = A.class.getDeclaredField("a");
            field.setAccessible(true);
            int s = field.getInt(b);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void test3() {
        List<A> list = new ArrayList<>();
        list.add(new A(true));
        list.add(new A(false));
        list.add(new A(true));
        list.add(new A(true));
        list.add(new A(false));
        list.add(new A(true));

        Collections.sort(list, Comparator.comparing(a -> ((A)a).isHaha()).reversed());
        System.out.println(list);
    }

    static void test4() {
        long a = (Integer.MAX_VALUE + 1) * 2L;
        System.out.println(a);
    }
}
