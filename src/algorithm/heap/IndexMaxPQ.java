package algorithm.heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <Key>
 *
 *     pq即是最小堆， 堆按照keys数组排序， pq数组中存放了keys数组的下标
 *
 *     qp存放了qp最小堆的下标， 以保证O(1) 找到key元素
 *
 *     key存放可排序元素
 *
 */


public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMaxPQ(int maxN) {
        if(maxN < 0) throw new IllegalArgumentException();
        n = 0;
        keys = (Key[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for(int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i]!=-1;
    }

    public int size() {
        return n;
    }

    public void insert(int i, Key key) {
        if(contains(i)) throw new IllegalArgumentException("Index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int maxIndex() {
        if(n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public Key maxKey() {
        if(n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    public int delMax() {
        if(n == 0) throw new NoSuchElementException("Priority queue underflow");
        int max = pq[1];
        exchange(1, n--);
        sink(1);

        assert pq[n+1] == max;
        qp[max] = -1;
        keys[max] = null;
        pq[n+1] = -1;
        return max;
    }

    public Key keyOf(int i) {
        if(!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    public void changeKey(int i, Key key) {
        if(!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void increaseKey(int i, Key key) {
        if(!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) >= 0)
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");

        keys[i] = key;
        swim(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        if(!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly increase the key");

        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        if(!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exchange(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /****************二叉堆辅助函数*******************/

    private void swim(int k) {
        while(k>1 && less(k/2, k)) {
            exchange(k, k/2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while(2*k < n) {
            int j = 2*k;
            if(j < n && less(j, j+1)) j++;
            if(!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }



    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exchange(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] =j;

    }

    /************二叉树辅助函数结尾***************/

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMaxPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMaxPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        // insert a bunch of strings
        Integer[] integers = { 3, 7, 0, 9, 6, 5, 8, 4, 2, 1,10 };

        IndexMaxPQ<Integer> pq = new IndexMaxPQ<Integer>(integers.length);
        for (int i = 0; i < integers.length; i++) {
            pq.insert(i, integers[i]);
        }

        while (!pq.isEmpty()) {
            Integer key = pq.maxKey();
            int i = pq.delMax();
            System.out.println(i + " " + key);
        }


        System.out.println();

    }

}
