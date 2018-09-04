package algorithm.sort;

public class ShellSort {
    public static void sort(Comparable[] a) {
      int N = a.length;

      int h = 1;
      while(h < N/3) h = 3*h+1;

      while (h >= 1) {
          for(int i = h; i < N; i++) {
              for(int j = i; j >=h && SortUtils.less(a[j],a[j-h]); j-=h)
                  SortUtils.exchange(a, j, j-h);
          }
          h/=3;
      }

    }
}