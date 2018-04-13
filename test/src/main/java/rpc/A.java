package rpc;

import java.util.ArrayList;

public class A {

    public String get(int i) {
        String rtn = "A" + i;
        ArrayList<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};


        return rtn;
    }
}
