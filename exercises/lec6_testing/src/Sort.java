public class Sort {
    public static void sort(String[] x){
        sort(x, 0);
    }

    /** Sorts the array starting at the index start */
    public static void sort(String[] x, int start){
        if(start == x.length){
            return;
        }
        int smallest = findSmallest(x, start);
        swap(x, start, smallest);
        sort(x, start + 1);
    }

    public static void swap(String[] x, int a, int b){
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }

    public static int findSmallest(String[] s, int start){
        int smallest = start;
        for(int i = start; i < s.length; i++){
            int comp = s[smallest].compareTo(s[i]);

            if(comp > 0){
                smallest = i;
            }
        }

        return smallest;
    }
}
