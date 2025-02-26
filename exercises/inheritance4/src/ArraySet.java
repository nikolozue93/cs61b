import java.util.*;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet(){
        items = (T[]) new Object[100];
        size = 0;
    }

    public boolean contains(T x){
        for(int i = 0; i < size; i++){
            if(items[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof ArraySet oas){
            if(oas.size() != this.size()){
                return false;
            }

            for(T x : this){
                if(!oas.contains(x)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void add(T x){
        if(x == null){
            throw new IllegalArgumentException("can't add null");
        }
        if(contains(x)){
            return;
        }
        items[size] = x;
        size++;
    }

    public int size(){
        return size;
    }

    /** returns an iterator (a.k.a seer) into ME */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;
        public ArraySetIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = items[wizPos];
            wizPos++;
            return item;
        }
    }

//    @Override
//    public String toString(){
//        StringBuilder returnSB = new StringBuilder("{");
//        for(int i = 0; i < size - 1; i++){
//            returnSB.append(items[i].toString());
//            returnSB.append(", ");
//        }
//        returnSB.append(items[size - 1].toString());
//        returnSB.append("}");
//        return returnSB.toString();
//    }

    /** Alternate, fancier toString() */
    @Override
    public String toString(){
        List<String> listOfItems = new ArrayList<>();
        for(T x : this){
            listOfItems.add(x.toString());
        }
        return "{" + String.join(", ", listOfItems) + "}";
    }

    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<Glerp>();
        for(Glerp x : stuff){
            returnSet.add(x);
        }

        return returnSet;
    }

    public static void main(String[] args){
//        Set<Integer> javaset = new HashSet<>();
//        javaset.add(5);
//        javaset.add(23);
//        javaset.add(18);
//
//        Iterator<Integer> seer = javaset.iterator();
//        while(seer.hasNext()){
//            int i = seer.next();
//            System.out.println(i);
//        }

        /** if we were to do the same iterable(for-each) loop with our data struct
         *  we wouldnt be able to, cause we need to implement it
         */
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(18);

//        Iterator<Integer> asser = aset.iterator();
//        while(asser.hasNext()){
//            int i = asser.next();
//            System.out.println(i);
//        }
        System.out.println(aset);

        ArraySet<String> asetOfStrings = ArraySet.of("hi", "im", "nick");
        System.out.println(asetOfStrings);
    }
}
