public class Sorting {
    public static int num = 1000;
    private int[] array;

    public Sorting() {
        array = new int[num];
        for (int i = 0; i < array.length; i++) {
            array[i] = num - i;
        }
    }


    /**
     * acending result
     */
    public void sortByStraightInsertion() {
        if(array.length == 1) {
            return;
        }

        for(int i = 1; i < array.length; i++) {
            int m = i;
            for(int j = i - 1; j > -1; j--) {
                if(array[m] < array[j]) {
                    swap(m, j);
                    m = j;
                } else {
                    break;
                }
            }
        }
    }

    private void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public void print() {
        int nextLine = 30;
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
            if(i % nextLine == nextLine - 1) {
                System.out.println();
            }
        }
    }
}
