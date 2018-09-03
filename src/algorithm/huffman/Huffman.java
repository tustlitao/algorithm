package algorithm.huffman;

public class Huffman {

    private HuffmanNode mRoot;

    public Huffman(int a[]) {
        HuffmanNode parent = null;
        MinHeap heap;

        heap = new MinHeap(a);

        for(int i = 0; i < a.length-1; i++) {
            HuffmanNode left = heap.dumpFromMinimum();
            HuffmanNode right = heap.dumpFromMinimum();
            parent = new HuffmanNode(left.value+right.value, left, right, null);
            left.parent = parent;
            right.parent = parent;

            heap.insert(parent);
        }

        mRoot = parent;

        heap.destroy();
    }

    /*
     * 前序遍历"Huffman树"
     */
    private void preOrder(HuffmanNode tree) {
        if(tree != null) {
            System.out.print(tree.value+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"Huffman树"
     */
    private void inOrder(HuffmanNode tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.print(tree.value+" ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }


    /*
     * 后序遍历"Huffman树"
     */
    private void postOrder(HuffmanNode tree) {
        if(tree != null)
        {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.value+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * 销毁Huffman树
     */
    private void destroy(HuffmanNode tree) {
        if (tree==null)
            return ;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree=null;
    }

    public void destroy() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"Huffman树"
     *
     * value        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(HuffmanNode tree, int value, int direction) {

        if(tree != null) {

            if(direction==0)	// tree是根节点
                System.out.printf("%2d is root\n", tree.value);
            else				// tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.value, value, direction==1?"right" : "left");

            print(tree.left, tree.value, -1);
            print(tree.right,tree.value,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.value, 0);
    }
}
