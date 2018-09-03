package algorithm.huffman;

/**
 * Huffman节点类
 *
 */


public class HuffmanNode implements Comparable, Cloneable{

    protected int value;//即data数据

    protected HuffmanNode left;
    protected HuffmanNode right;
    protected HuffmanNode parent;

    public HuffmanNode(int value, HuffmanNode left, HuffmanNode right, HuffmanNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }


    @Override
    public int compareTo(Object o) {
        return this.value - ((HuffmanNode)o).value;
    }

    @Override
    protected Object clone() {
        Object obj = null;

        try {
            obj = (HuffmanNode) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return obj;
    }
}
