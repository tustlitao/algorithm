package algorithm.sort;


/**
 *
 * 针对快速排序的算法优化有以下几点
 * 1. 对于小数组部分，切换到插入排序
 * 2. 切分部分，取low， mid， height 的中位数作为切分元素， 并将此三元素按序放置
 * 3. 有关于大量重复元素的数组的优化 可使用quick3way算法
 *
 */

public class QuickSort {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int low, int height) {
        if(low>=height) return;
        int j = partition(a, low, height);
        sort(a, low, j-1);
        sort(a, j+1, height);
    }
    private static int partition(Comparable[] a, int low, int height) {
        int i = low;
        int j = height+1;
        Comparable v = a[low];
        while(true) {
            while(SortUtils.less(a[++i], v)) if (i == height) break;
            while(SortUtils.less(v, a[--j])) if (j == low) break;
            if(i >= j) break;
            SortUtils.exchange(a, i, j);
        }
        SortUtils.exchange(a, low, j);
        return j;
    }
}
