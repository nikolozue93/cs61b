import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;
    private static final int INITIAL_CAPACITY = 8;
    private int capacity;
    private static final int factor = 2; // factor of resizing

    // if every AD61B has to have size 8 by default, why shouldn't we exploit it via first/last
    public ArrayDeque61B() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = items.length;
        nextFirst = 0;
        nextLast = 1;
    }

    /** We get the nextFirst with floorMod, for example, at first it will be 4
     * then 3, when it reaches -1, we get 7, it loops back
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if(size == capacity){
            resize(size * factor);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, capacity);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if(size == capacity) {
            resize(size * factor);
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, capacity);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i = 0; i < capacity; i++) {
            T item = this.get(i);

            if(item != null) {
                returnList.add(item);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * for these two methods, i think it will be better to start from 0-1?
     * nop, so if we shouldnt use recursion/looping, we will directly
     * go to nextLast, nextFirst, and with them we can know which one is the
     * first and which one is the last
     *
     * To avoid messing with arrays index bounds, we make sure we in bounds
     * with Math.floorMod method. Then we save that element in first.
     * then we equate with the index that element to null and change nextFirst.
     */
    @Override
    public T removeFirst() {
        if(!isEmpty()) {

            if(capacity >= 15 && size <= capacity / 4) {
                resize(capacity / factor);
            }

            int index = Math.floorMod(nextFirst + 1, capacity);
            T first = this.get(index);

            items[index] = null;
            nextFirst = index;
            size -= 1;

            return first;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if(!isEmpty()) {

            if(capacity >= 15 && size <= capacity / 4) {
                resize(capacity / factor);
            }

            int index = Math.floorMod(nextLast - 1, capacity);
            T last = this.get(index);

            items[index] = null;
            nextLast = index;
            size -= 1;

            return last;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if(isEmpty() || index < 0 || index > capacity) {
            return null;
        }
        //return items[index];
        return items[(nextFirst + 1 + index) % capacity];
    }


    /**
     * Having recursive get function here, is so stupid
     */
//    @Override
//    public T getRecursive(int index) {
//        if(isEmpty() || index > capacity || index < 0) {
//            return null;
//        }
//
//        if(index == 0) {
//            return items[index];
//        }
//        return helper(0, index);
//    }
//
//    public T helper(int starter, int index){
//        if(starter == index){
//            return items[starter];
//        }
//        return helper(starter + 1, index);
//    }


    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public void resize(int newCapacity) {
        if(isEmpty()){
            return;
        }

        T[] resized = (T[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            resized[i] = get(i);
        }

        this.capacity = newCapacity;
        nextFirst = newCapacity - 1;
        nextLast = size;

        items = resized;
    }

}
