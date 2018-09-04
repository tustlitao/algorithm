package algorithm.sort;

/**
 *
 * 关于Merge的优化
 *
 *1. 对于小数组 可采用Insertion排序
 *
 * 2.归并两有序子数组时 归并的方法可优化
 *      1）. 判断子数组的头部和尾部，若有 a. height <= b.low ,则可以直接放置到数组中，减少归并
 *      2）. 在归并时若其中一个达到尾部， 则直接将其与的放置于数组中，
 *          （此点优化在浙大数据结构链表归并中见到）
 */




public class MergeSort {

    private static Comparable[] temp;

    public static void sort(Comparable[] a) {
        temp = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int height) {
        if(low+10 >= height) {
            Insertion.sort(a, low, height);
            return;
        }
        int mid = (low+height)/2;
        sort(a, low, mid);
        sort(a, mid+1, height);
        merge(a, low, mid, height);
    }

    private static void merge(Comparable[] a, int low, int mid, int height) {
        for(int k = low; k <= height; k++) temp[k] = a[k];
        int i = low;
        int j = mid+1;
        for(int k = low; k < height; k++) {
            if(i > mid) a[k] = temp[j++];
            else if(j > height) a[k] = temp[i++];
            else if(SortUtils.less(a[i],a[j])) a[k] = temp[i++];
            else a[k] = temp[j++];
        }
    }

}
