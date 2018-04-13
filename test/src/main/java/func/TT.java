package func;

import java.util.ArrayList;
import java.util.List;

public class TT {

    public static void main(String[] args) {
        ArrayList<Cell> cells = new ArrayList<>();

        for(int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                cells.add(new Cell(x, y));
            }
        }

        System.out.println(cells);
    }

    public static List<Integer> getCandidates(int mask) {
        List<Integer> members = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            if((mask & (1 << i)) != 0) {
                members.add(i + 1);
            }
        }
        return members;
    }

    static class Cell {
        int x;
        int y;
        int group;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            this.group = x / 3 * 3 + y / 3;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y +"," + group + "]";
        }
    }

}
