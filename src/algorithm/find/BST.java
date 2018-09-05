package algorithm.find;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 二叉查找树的API
 *
 * int size();
 *
 * boolean isEmpty();
 *
 * Value get(Key key);
 *
 * void put(Key key, Value value);
 *
 * Key min();
 *
 * Key max();
 *
 * Key floor();
 *
 * Key ceiling();
 *
 * Key select(int k); //返回排名为k的键
 *
 * int rank(Key key); //返回键为key的排名
 *
 * void deleteMin(); //删除最小结点
 *
 * void deleteMax(); //删除最大结点
 *
 * Iterable<Key> keys();//迭代器的返回
 *
 * Iterable<Key> keys(Key lo, Key hi);//范围查找
 *
 * void delete(Key key); //删除键为key的结点
 *
 *
 * 操作时需要注意：发生插入 删除操作时，注意更新结点数 - N
 *
 * 给delete方法写注释, 方便以后查阅
 *
 * @param <Key>
 * @param <Value>
 */


public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {

        private Key key;

        private Value value;

        private Node left, right;

        private int N; //该根的节点数

        public Node(Key key, Value value, int N) {

            this.key = key;

            this.value = value;

            this.N = N;
        }
    }

    public int size() { return size(root); }

    public int size(Node node) {

        if(node == null) return 0;

        else             return node.N;
    }

    public boolean isEmpty() {

        if(root == null) return true;

        else return false;
    }

    public Value get(Key key) {

        return get(root, key);
    }

    private Value get(Node x, Key key) {

        if(x == null) return null;

        int cmp = key.compareTo(x.key);

        if(cmp < 0) return get(x.left, key);

        else if (cmp > 0) return get(x.right, key);

        else return x.value;
    }



    public void put(Key key, Value value) {

        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {

        if(x == null) return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);

        if(cmp < 0) x.left = put(x.left, key, value);

        else if (cmp > 0) x.right = put(x.right, key, value);

        else x.value = value;

        x.N = size(x.left) + size(x.right) + 1;

        return x;

    }

    public Key min() {

        return min(root).key;
    }

    public Node min(Node x) {

        if(x.left == null) return x;

        else return min(x.left);
    }

    public Key max() {

        return max(root).key;
    }

    public Node max(Node x) {

        if(x.right == null) return x;

        else return max(x.right);
    }

    public Key floor(Key key) {

        Node x = floor(root, key);

        if(x == null) return null;

        else return x.key;
    }

    public Key ceiling(Key key) {

        Node x = ceiling(root, key);

        if(x == null) return null;

        else return x.key;
    }

    public Node ceiling(Node x, Key key) {

        if(x == null) return null;

        int cmp = key.compareTo(x.key);

        if(cmp==0) return x;

        if(cmp < 0) return ceiling(x.right, key);

        Node t = ceiling(x.left, key);

        if(t != null) return t;

        else return x;
    }

    public Node floor(Node x, Key key) {

        if(x == null) return null;

        int cmp = key.compareTo(x.key);

        if(cmp==0) return x;

        if(cmp < 0) return floor(x.left, key);

        Node t = floor(x.right, key);

        if(t != null) return t;

        else return x;
    }

    public Key select(int k) {

        return select(root, k).key;
    }

    private Node select(Node x, int k) {

        if(x == null) return null;

        int t = size(x.left);

        if(t > k) return select(x.left, k);

        else if(t < k) return select(x.right, k);

        else return x;
    }

    public int rank(Key key) {

        return rank(key, root);
    }

    private int rank(Key key, Node x) {

        if(x == null) return 0;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) return rank(key, x.left);

        else if(cmp > 0) return 1 + size(x.left) + rank(key, x.right);

        else return size(x.left);
    }

    public void deleteMin() {

        root  = deleteMin(root);
    }

    public Node deleteMin(Node x) {

        if(x.left == null) return x.right;

        x.left = deleteMin(x.left);

        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    public void delete(Key key) {

        root = delete(root, key);
    }


    /**
     *
     * 递归形式寻找结点 栈返回后更新 N
     *
     * 找到删除节点 x 后 用T.Hibbard提出的解决方案进行删除
     *
     *      1.将指向即将被删除的结点的链接保存为t; Node t = x;
     *
     *      2.将x指向它的后继结点min(t.right); x = min(t.right);
     *
     *      3.将x的右链接指向deleteMin(t.right); x.right = deleteMin(t.right);
     *
     *      4.将x的左链接设为t.left  x.left = t.left;
     *
     * 记得更新结点数 N
     * @param x
     * @param key
     * @return
     */

    private Node delete(Node x, Key key) {

        //1.寻找删除结点
        if(x == null) return null;

        int cmp = key.compareTo(x.key);

        if(cmp < 0) x = delete(x.left, key);

        else if (cmp > 0)x = delete(x.right, key);

        else {

            //2. 删除结点
            if(x.right == null) return x.left;

            if(x.left == null) return x.right;

            Node t = x;

            x = min(t.right);

            x.right = deleteMin(x.right);

            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {

        List<Key> list = new ArrayList<Key>();

        keys(root, list, lo, hi);

        return list;
    }

    private void keys(Node x, List<Key> list, Key lo, Key hi) {

        if (x == null) return;

        int cmpLo = lo.compareTo(x.key);

        int cmpHi = hi.compareTo(x.key);

        if(cmpLo < 0) keys(x.left, list, lo, hi);

        if(cmpLo <= 0 && cmpHi >= 0) list.add(x.key);

        if(cmpHi > 0) keys(x.right, list, lo, hi);
    }
}
