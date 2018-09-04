package algorithm.sort;

public class SortUtils {
    public static boolean less(Comparable a, Comparable b) {
        if(a.compareTo(b) < 0) return true;
        return false;
    }
    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void print(Comparable[] a) {
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i].toString()+" ");
        System.out.println();
    }

    public static void print(Comparable[] a, int low, int height) {
        for(int i = low; i <= height; i++) {
            System.out.print(a[i].toString()+" ");
        }
        System.out.println();
    }
}
