import java.util.*;

public class A {
    private int d = 1;
    private int a = 1;
    private boolean haha;

    protected int getD() {
        return d;
    }

    public boolean isHaha() {
        return haha;
    }

    public A() {

    }

    public A(boolean a) {
        this.haha = a;
    }

    public void setHaha(boolean haha) {
        this.haha = haha;
    }

    static Random r = new Random();
    public static void main(String[] args) {
        ArrayList<Integer> warm = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            warm.add(r.nextInt(3));
        }
        System.out.println(warm);


        // "a" is target
        ArrayList<Boolean> hold = new ArrayList<>();
        ArrayList<Boolean> change = new ArrayList<>();
        double total = 1000_000;

        for(int i = 0; i < total; i++) {
            List<String> pool = step1();
            Integer old = getIndex();
            hold.add(isTarget(pool, old));
            change.add(change(pool, old));
        }

        System.out.println("hold:" + successCount(hold) / total);
        System.out.println("change:" + successCount(change) / total);
    }

    private static List<String> step1() {
        List<String> pool = Arrays.asList(new String[]{"a", "b", "c"});
        Collections.shuffle(pool);
        return pool;
    }

    private static Integer getIndex(){
        return r.nextInt(3);
    }

    private static boolean isTarget(List<String> pool, Integer index) {
        return pool.get(index).equals("a");
    }

    private static boolean change(List<String> pool, Integer old) {
        if(pool.get(old).equals("a")) {
            return false;
        } else {
            return true;
        }
    }

    private static double successCount(ArrayList<Boolean> list) {
        double count = 0;
        for(Boolean b : list) {
            if(b) {
                count++;
            }
        }
        return count;
    }

    protected void print() {
        System.out.println(getD());
    }

    public void pPrint() {
        print();
    }
}
