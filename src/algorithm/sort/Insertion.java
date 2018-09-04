package algorithm.sort;

/**
 *
 * Insertion在小数组时表现的性能很稳定
 *
 * 所以在优化归并 快速等算法时可以使用，以提高效率
 *
 */



public class Insertion {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int low, int height) {
        for(int i = low; i <= height; i++) {
            for(int j = i; j > 0 && SortUtils.less(a[j],a[j-1]); j--)
                SortUtils.exchange(a, j, j-1);
        }
    }
}
