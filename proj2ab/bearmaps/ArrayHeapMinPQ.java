package bearmaps;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Hashtable;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<Node> heap;
    private Hashtable<T, Node> hash;
    private int size;
    public ArrayHeapMinPQ() {
        size = 0;
        heap = new ArrayList<>();
        hash = new Hashtable<>();
        Node zeroNode = new Node(null, 0);
        heap.add(0, zeroNode);
    }
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present. */
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        size += 1;
        Node n = new Node(item, priority);
        heap.add(size, n);
        n.heapIndex = size;
        hash.put(item, n);
        upSwapper(size);
    }
    private int parent(int index) {
        return index / 2;
    }
    private int rightChild(int index) {
        return (index * 2) + 1;
    }
    private int leftChild(int index) {
        return index * 2;
    }
    private int smallestChild(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        if (left > size) {
            return 0;
        }
        int smallest = right;
        if (right > size || heap.get(left).getPriority() <= heap.get(right).getPriority()) {
            smallest = left;
        }
        return smallest;
    }
    private void nodeSwap(Node one, Node two) {
        int tempIndex = one.getHeapIndex();
        one.heapIndex = two.getHeapIndex();
        two.heapIndex = tempIndex;
        heap.set(one.getHeapIndex(), one);
        heap.set(two.getHeapIndex(), two);
    }
    private void upSwapper(int current) {
        int parent = parent(size);
        int last = current;
        while (parent != 0 && heap.get(parent).getPriority() > heap.get(last).getPriority()) {
            Node top = heap.get(parent);
            Node bot = heap.get(last);
            nodeSwap(heap.get(parent), heap.get(last));
            last = parent;
            parent = parent(parent);
        }
    }
    private void downSwapper(int current) {
        int top = current;
        int smallest = smallestChild(top);
        while (smallest != 0 && heap.get(top).getPriority() > heap.get(smallest).getPriority()) {
            nodeSwap(heap.get(top), heap.get(smallest));
            top = smallest;
            smallest = smallestChild(top);
        }
    }
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return hash.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        return heap.get(1).getItem();
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T toReturn = heap.get(1).getItem();
        hash.remove(toReturn);
        heap.set(1, heap.get(size));
        heap.get(1).heapIndex = 1;
        size--;
        downSwapper(1);
        return toReturn;
    }
    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        Node toChange = hash.get(item);
        int index = toChange.getHeapIndex();
        heap.get(index).setPriorityValue(priority);
        if (heap.get(parent(index)).getPriority() > toChange.getPriority()) {
            upSwapper(index);
        } else {
            downSwapper(index);
        }
    }
    //@NaiveMinPQ
    private class Node {
        private T item;
        private double priorityValue;
        private int heapIndex;
        Node(T i, double val) {
            item = i;
            priorityValue = val;
        }
        public T getItem() {
            return this.item;
        }
        public double getPriority() {
            return this.priorityValue;
        }
        public void setPriorityValue(double p) {
            this.priorityValue = p;
        }
        public int getHeapIndex() {
            return this.heapIndex;
        }
    }
}
