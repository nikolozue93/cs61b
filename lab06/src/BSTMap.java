import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
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

    public int compareRoots(Node other) {
        return 0;
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

        put(root, key, value);
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
            return new Node(key, value, x.size);
        } else if(compare < 0) {
            x.right = new Node(key,value, 1);
        } else if(compare > 0) {
            x.left = new Node(key, value, 1);
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
        int compare = key.compareTo(this.root.key);

        if(compare == 0)

        return false;
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
        this.root = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
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
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /**
     * Prints out your BSTMap in order of increasing Key.
     */
    public void printInOrder(){

    }
}
