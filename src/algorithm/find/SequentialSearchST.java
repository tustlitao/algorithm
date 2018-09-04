package algorithm.find;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SequentialSearchST<Key, Value> {

    private Node first;

    private int size;

    public SequentialSearchST() {

        this.first = null;

        this.size = 0;
    }

    private class Node {

        Key key;

        Value val;

        Node next;

        public Node(Key key, Value val, Node next) {

            this.key = key;

            this.val = val;

            this.next = next;
        }
    }


    public int size() {

        return size;

    }

    public boolean isEmpty() {

        return size() == 0;
    }

    public boolean contains(Key key) {

        if(key == null) throw new IllegalArgumentException("argument contains() is null");

        return get(key) != null;
    }

    public Value get(Key key) {

        if(key == null) throw new IllegalArgumentException("argument get() is null");

        for(Node x = first; x != null; x = x.next) {

            if(key.equals(x.key)) return x.val;

        }

        return null;
    }

    public void put(Key key, Value val) {

        if(key == null) throw new IllegalArgumentException("argument put() is null");

        if(val == null) {

            delete(key);

            return;
        }

        for(Node x = first; x != null; x = x.next) {

            if(key.equals(x.key)) {

                x.val = val;

                return;
            }
        }

        first = new Node(key, val, first); //头插法

        size++;
    }

    /**
     *
     * @param key
     *
     * 由于没有定义一个固定的头结点， 以普通方式写要判断头部
     *
     * 以递归方式可以巧妙避开
     */


    public void delete(Key key) {

        if(key == null) throw new IllegalArgumentException("argument delete() is null");

        first = delete(first, key); //递归方式删除节点

        return;
    }

    public Node delete(Node x, Key key) {

        if(x == null) return null;

        if(key.equals(x.key)) {

            size--;

            return x.next;
        }

        x.next = delete(x.next, key);

        return x;

    }

    public Iterable<Key> keys() {

        List<Key> list = new ArrayList<Key>();

        for(Node x = first; x != null; x = x.next) {

            list.add(x.key);
        }

        return list;
    }

    public static void main(String[] args) {

        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

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
