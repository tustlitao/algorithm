package algorithm.sort;

public class HeapSort {
    public static void  sort(Comparable[] a) {
        int N = a.length-1;
        for(int k = (N-1)/2; k >= 0; k--)
            sink(a, k, N);
        while(N > 0) {
            exchange(a, 0, N--);
            sink(a,0, N);
        }
    }

    private static void sink(Comparable[] a, int k, int n) {
        while(2*k + 1 <= n) {
            int j = 2*k+1;
            if(j < n && less(a, j, j+1)) j++;
            if(!less(a, k, j)) break;
            exchange(a, k, j);
            k = j;
        }
    }
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
