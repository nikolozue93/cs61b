import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;

    // if every AD61B has to have size 8 by default, why shouldn't we exploit it via first/last
    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4; // items.length / 2
        nextLast = 5; // (items.length / 2) + 1
    }

    /** We get the nextFirst with floorMod, for example, at first it will be 4
     * then 3, when it reaches -1, we get 7, it loops back
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if(size != items.length){
            items[nextFirst] = x;
            nextFirst = Math.floorMod(nextFirst - 1, items.length);
            size += 1;
        }
    }

    @Override
    public void addLast(T x) {
        if(size != items.length){
            items[nextLast] = x;
            nextLast = Math.floorMod(nextLast + 1, items.length);
            size += 1;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i = 0; i < items.length; i++) {
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

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if(isEmpty() || index < 0 || index > size){
            return null;
        }
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
