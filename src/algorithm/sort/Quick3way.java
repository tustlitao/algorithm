package algorithm.sort;

public class Quick3way {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int low, int height) {
        if(low >= height) return;
        int lt = low;
        int i = low+1;
        int gt = height;
        Comparable v = a[low];
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if(cmp < 0) SortUtils.exchange(a, lt++, i++);
            else if(cmp > 0) SortUtils.exchange(a, i, gt--);
            else i++;
        }
        sort(a, low, lt-1);
        sort(a, gt+1, height);
    }
}
