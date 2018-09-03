package algorithm.huffman;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private List<HuffmanNode> mHeap;

    /**
     * 创建最小堆
     *
     *        a -- 数据所在的数组
     */

    public MinHeap(int a[]) {
        mHeap = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            HuffmanNode node = new HuffmanNode(a[i],null,null,null);
            mHeap.add(node);
        }

        for(int i = a.length/2 - 1; i >= 0; i--) {
            filterDown(i, a.length - 1);
        }
    }

    /*
        最小堆得向下调整算法

        start -- 被下调节点的起始位置
        end -- 截止范围
     */
    protected void filterDown(int start, int end) {
        int currentPos = start; // 当前节点位置
        int minPos = 2 * currentPos + 1; // 左子树位置
        HuffmanNode currentNode = mHeap.get(currentPos); //当前节点

        //用来选择最小root
        while (minPos <= end) {
            if (minPos < end && (mHeap.get(minPos)).compareTo(mHeap.get(minPos + 1)) > 0) {
                minPos++;
            }

            if (currentNode.compareTo(mHeap.get(minPos)) <= 0) {
                break;
            } else {
                mHeap.set(currentPos, mHeap.get(minPos));
                currentPos = minPos;
                minPos = 2 * currentPos + 1;
            }
        }
        mHeap.set(currentPos, currentNode);
    }

    /*
        最小堆向上调整算法
     */

    protected void filterUp(int start) {
        int currentPos = start; //当前节点位置
        int parentPos = (currentPos-1)/2; //父节点位置
        HuffmanNode currentNode = mHeap.get(currentPos);

        while(currentPos > 0) {
            if(mHeap.get(parentPos).compareTo(currentNode) <=0 ) {
                break;
            } else {
                mHeap.set(currentPos, mHeap.get(parentPos));
                currentPos = parentPos;
                parentPos = (currentPos-1)/2;
            }
        }
    }

    public void insert(HuffmanNode node) {
        int size = mHeap.size();

        mHeap.add(node);
        filterUp(size);
    }

    protected void swapNode(int i,int j) {
        HuffmanNode tmp = mHeap.get(i);
        mHeap.set(i, mHeap.get(j));
        mHeap.set(j, tmp);
    }


    /*
    取出root，调整最小堆
     */
    public HuffmanNode dumpFromMinimum() {
        int size = mHeap.size();

        if(size == 0) {
            return null;
        }

        HuffmanNode node = (HuffmanNode) mHeap.get(0).clone();

        mHeap.set(0, mHeap.get(size-1));

        mHeap.remove(size-1);

        if(mHeap.size() > 1) {
            filterDown(0, mHeap.size()-1);
        }
        return node;
    }

    public void destroy() {
        mHeap.clear();
        mHeap = null;
    }
}
