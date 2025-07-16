package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Nikoloz Beridze
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size = 0; // N - num of key-val pairs in the hashmap
    private static final int defaultInitialCapacity = 16;
    private static final double defaultLoadFactor = 0.75; // max load factor allowed, before resizing
    private double loadFactor;



    /** Constructors */
    public MyHashMap() {
        this(defaultInitialCapacity, defaultLoadFactor);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, defaultLoadFactor);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.size = 0;
        buckets = new Collection[initialCapacity];

        for(int i = 0; i < initialCapacity; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
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
        int hashcode = key.hashCode();
        hashcode = Math.floorMod(hashcode, buckets.length);

        if(containsKey(key)){ // if it contains the key, we find the corresponding node and update its value
            for(Node n : buckets[hashcode]){
                if(key.equals(n.key)){
                    n.value = value;
                    break;
                }
            }
        } else { // if it doesnt contain the key, i create the new node and add to the corresponding bucket
            Node n = new Node(key, value);
            buckets[hashcode].add(n);

            size++;
        }


        if( ((double) size / buckets.length) > loadFactor){
            buckets = resize(buckets);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int hashcode = key.hashCode();
        hashcode = Math.floorMod(hashcode, buckets.length);

        if(buckets[hashcode] == null || buckets[hashcode].isEmpty()){ // when corresponding key doesnt exist
            return null;
        }

        for(Node n : buckets[hashcode]){ // iterating through nodes of that bucket if the bucket isnt null
            if(key.equals(n.key)){
                return n.value;
            }
        }

        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    private Collection<Node>[] resize(Collection<Node>[] buckets){
        Collection<Node>[] resized = new Collection[buckets.length * 2]; // doubling the size

        for(int i = 0; i < resized.length; i++){
            resized[i] = createBucket();
        }

        for(int i = 0; i < buckets.length; i++){ // iterating through the old buckets
            for(Node n : buckets[i]){ // iterating through nodes of each bucket
                int hashcode = n.key.hashCode();
                hashcode = Math.floorMod(hashcode, resized.length); // getting new hashes for each node

                resized[hashcode].add(n); // adding those nodes to their new corresponding places in the new bucket
            }
        }
        return resized;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        for(int i = 0; i < buckets.length; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        V removed = null;

        if(containsKey(key)){

            int hashcode = key.hashCode();
            hashcode = Math.floorMod(hashcode, buckets.length);

            // in this way it would work whatever the data struct the user will choose
            // and is not bound by any of the data structs, as its the main idea of this lab
            Collection updated = createBucket();

            for(Node n : buckets[hashcode]){
                if(!key.equals(n.key)){
                    updated.add(n);
                } else {
                    removed = n.value;
                }
            }
            buckets[hashcode] = updated;
            size--;
        }

        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


}
