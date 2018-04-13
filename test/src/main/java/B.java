import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class B extends A {
    public int index1;
    public int index2;
    public int index3;

    public B(int index1, int index2, int index3) {
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getIndex3() {
        return index3;
    }

    public static void main(String[] args) {
        List<B> list = new ArrayList<>();
        list.add(new B(1, 1, 3));
        list.add(new B(1, 2, 3));
        list.add(new B(2, 1, 3));
        list.add(new B(2, 2, 1));
        list.add(new B(2, 2, 3));
        list.add(new B(3, 1, 3));
        list.add(new B(3, 2, 3));
        list.add(new B(3, 2, 1));

        list.sort(Comparator.comparing(B::getIndex1)
                .thenComparing(Comparator.comparing(B::getIndex2).reversed())
                .thenComparing(b -> b.index3));
        System.out.println(list);
    }
}
