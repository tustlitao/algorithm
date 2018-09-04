package algorithm.sort;

public class MergeBUSort {
    public static Comparable[] temp;

    public static void sort(Comparable[] a) {
        int N = a.length;
        temp = new Comparable[N];
        for(int size = 1; size < N; size +=size) {
            for(int low = 0; low < N-size; low += size+size)
                merge(a, low, low+size-1, Math.min(N-1, low+size+size-1));
        }

    }

    private static void merge(Comparable[] a, int low, int mid, int height) {
        int i = low;
        int j = mid+1;

        for(int k = low; k <= height; k++) {
            temp[k] = a[k];
        }
        for(int k = low; k <= height; k++) {
            if(i > mid) {
                a[k] = temp[j++];
            } else if(j > height) {
                a[k] = temp[i++];
            } else if(SortUtils.less(temp[i], temp[j])) {
                a[k] = temp[i++];
            } else{
                a[k] = temp[j++];
            }
        }
    }

}
