import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    public class Node{
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BSTMap(){

    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException();
        }

        root = put(root, key, value);
    }

    /**
     * Helper method for put
     * @param x
     * @param key
     * @param value
     * @return
     */
    private Node put(Node x, K key, V value){
        if(x == null) {
            return new Node(key, value, 1);
        }

        int compare = key.compareTo(x.key);
        if(compare == 0) {
            x.val = value;
        } else if(compare < 0) {
            x.left = put(x.left, key,value );
        } else if(compare > 0) {
            x.right = put(x.right, key, value);
        }

        x.size = 1 + size(x.left) + size(x.right);

        return x;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        return get(root, key);
    }

    private V get(Node x, K key){
        if(x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);

        if(compare < 0){
            return get(x.left, key);
        } else if(compare > 0){
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if(key == null){
            throw new IllegalArgumentException();
        }


        return containsKey(root, key);
    }

    private boolean containsKey(Node x, K key){
        if (x == null) {
            return false;
        }

        int compare = key.compareTo(x.key);

        if(compare > 0){
            return containsKey(x.right, key);
        } else if(compare < 0){
            return containsKey(x.left, key);
        } else {
            return true;
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if(x == null){
            return 0;
        }
        return x.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * These two select methods are helper for print in order
     * As we are interested in having order, we start traversing
     * left subtree, which we get via variable t. if t is more than k
     * that it means the node we are looking for is in the left subtree
     * we go through this with recursion and when k > t, we go to the right
     *
     * where we subtract t + 1 from k, t is from the left subtree and that 1 is x itself
     * t (left nodes) + 1 (current node) = t + 1 total nodes before right subtree
     * @param k
     * @return
     */
    private Node select(int k){
        if(k < 0 || k > size()){
            throw new IllegalArgumentException();
        }

        return select(root, k);
    }

    // see the above explanation
    private Node select(Node x, int k){
        if(x == null){
            return null;
        }

        int t = size(x.left);
        if(t > k){
            return select(x.left, k);
        } else if(t < k){
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }


    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        // LinkedHashSet preserves insertion order
        // when we retrieve keys with select in sorted order
        // this data struct helps us to preserve this order
        // unlike HashSet
        Set<K> BSTSet = new LinkedHashSet<>();
        for(int i = 0; i < root.size; i++){
            BSTSet.add(select(i).key);
        }
        return BSTSet;
    }

    private K min(){
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
    }

    private K max(){
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right == null){
            return x;
        }
        return max(x.right);
    }

    private void deleteMin(){
        root = deleteMin(root);
    }

    /**
     * Here if we dont have left subtree, we return right one
     * as it means that the root is the smallest element
     *
     * even if we have left subtree, we recursively go down
     * and when we reach the last element which has no children on the left
     * we return its right node, changing its parent pointer to its childs right one
     * even if its right is null or actual node,
     * doesnt matter, the smallest one is deleted
     */
    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }

        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if(!containsKey(key)){
            return null;
        }

        V toRemove = get(key);
        root = remove(root, key);
        return toRemove;
    }

    /**
     * If the given key is greater than the current node's key,
     * we recursively search in the right subtree. If it’s less,
     * we go into the left subtree.
     *
     * If compare == 0, we've found the node to remove.
     *
     * We then check if the node has 0 or 1 child:
     * - If the right child is null, we return the left child.
     * - If the left child is null, we return the right child.
     * This effectively deletes the node and replaces it with
     * its only child, or null if it has no children.
     *
     * If the node has two children:
     * - We store a reference to the current node (t).
     * - Then we find the smallest node in the right subtree
     *   (the in-order successor) and assign it to x.
     * - We delete that successor from the right subtree
     *   using deleteMin.
     * - We assign t.left (the original left subtree) to x.left.
     * This replaces the deleted node with its successor, while
     * preserving the BST structure.
     */

    private Node remove(Node x, K key){
        if(x == null){
            return null;
        }

        int compare = key.compareTo(x.key);
        if(compare > 0) {
            x.right = remove(x.right, key);
        } else if(compare < 0) {
            x.left = remove(x.left, key);
        } else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* Iterator of the BSTMap. */
    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node src) {
            while (src != null) {
                // Push root node and all left nodes to the stack.
                stack.push(src);
                src = src.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node curr = stack.pop();

            if (curr.right != null) {
                Node temp = curr.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }

    /**
     * Prints out your BSTMap in order of increasing Key.
     */
    public void printInOrder(){
        for(int i = 0; i < root.size; i++){
            System.out.println(select(i).key + "  " + select(i).val);
        }
    }
}
