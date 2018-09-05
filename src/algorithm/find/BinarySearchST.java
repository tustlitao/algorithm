package algorithm.find;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * 简单有序泛型符号表API
 *
 * void put(Key key, Value value); //增 or 改
 *
 * void get(Key key);   //查
 *
 * void delete(Key key);    //删
 *
 * boolean contains(Key key);
 *
 * boolean isEmpty();
 *
 * int size();
 *
 * Iterable<Key> keys();
 *
 *
 * Key min();
 *
 * Key max();
 *
 * Key floor(Key key); 小于等于Key的最大键
 *
 * Key ceiling(Key key); 大于等于Key的最小键
 *
 * int rank(Key key); 小于Key的键的数量
 *
 * Key select(int rank); 排名为Key的键
 *
 * @param <Key>
 * @param <Value>
 */




public class BinarySearchST<Key extends Comparable<Key>, Value>{

    private Key[] keys;

    private Value[] vals;

    private int N;

    public BinarySearchST(int capacity) {

        keys = (Key[]) new Comparable[capacity];

        vals = (Value[]) new Comparable[capacity];
    }

    public int size() {

        return N;
    }

    public Value get(Key key) {

        if(isEmpty()) return null;

        int i = rank(key);

        if(i < N && keys[i].compareTo(key) == 0) return vals[i];

        else return null;
    }

    public int rank(Key key) {

        int lo = 0, hi = N-1;

        while(lo <= hi) {

            int mid = (lo+hi)/2;

            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) hi = mid - 1;

            else if (cmp > 0) lo = mid + 1;

            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {

        int i = rank(key);

        if(i <  N && keys[i].compareTo(key) == 0) {

            vals[i] = val;

            return;
        }

        for(int j = N; j > i; j--) {

            keys[j] = keys[j-1];

            vals[j] = vals[j-1];
        }

        keys[i] = key;

        vals[i] = val;

        N++;
    }

    public void delete(Key key) {

        int i = rank(key);

        if(i < N && keys[i].compareTo(key)==0) {

            for(int j = i; j < N-1; j++) {

                keys[j] = keys[j+1];

                vals[j] = vals[j+1];
            }
        }

        keys[N-1] = null;

        vals[N-1] = null;

        N--;
    }

    public boolean isEmpty() {

        return N==0;
    }

    public Key min() { return keys[0];}

    public Key max() {return keys[N-1];}

    public Key select(int k) {

        if(k < keys.length && k >=0) throw new IllegalArgumentException("select k will must be >0 and < ST'capacity ");

        return keys[k];
    }


    public Iterable<Key> keys() {

        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {

        List<Key> list = new ArrayList<Key>();

        int hiRank = rank(hi);

        for(int i = rank(lo); i < hiRank; i++) {

            list.add(keys[i]);
        }
        if(rank(hi) < N && keys[hiRank].compareTo(hi) == 0) {

            list.add(keys[hiRank]);
        }

        return list;
    }

    public static void main(String[] args) {

        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);

        Scanner in = new Scanner(System.in);

        int i = 0;

        String key = null;

        while(!"".equals(key = in.nextLine())) {

            st.put(key, i++);
        }

        for(String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }

    }
}
