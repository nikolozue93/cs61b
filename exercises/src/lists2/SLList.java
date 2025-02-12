package lists2;

import edu.princeton.cs.algs4.In;

public class SLList {

    private static class IntNode{
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    private IntNode first;
    private int size;

    public SLList(){
        //first = new IntNode(x, null);
        first = null;
        size = 0;
    }

    public void addFirst(int x){
        first = new IntNode(x, first);
        size++;
    }

    public int getFirst(){
        return first.item;
    }

    public void addLast(int x){
        size++;

        if(first == null){
            addFirst(x);
        }

        IntNode p = first;
        while(p.next != null){
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    public int getLast(){
        IntNode p = first;

        while(p != null){
            p = p.next;
        }

        return p.item;
    }

//    public int size(){
//        IntNode p = first;
//        int size = 1; // I think this should be 1
//
//        while(p.next != null){
//            size++;
//            p = p.next;
//        }
//        return size;
//    }

//    private static int size(IntNode p){
//        if(p.next == null){
//            return 1;
//        }
//        /* OR
//        if(p == null){
//            return 0;
//        }
//         */
//
//        return 1 + size(p.next);
//    }
//
//    public int size(){
//        return size(first);
//    }


    /*
    fastened the calculation of size, so everytime the size of the list changes, we update it
    by maintaining a special variable that caches the size of the list
     */
    public int size(){
        return size;
    }

    public static void main(String[] args){
        SLList L = new SLList();
        //System.out.println(L.getFirst());
        L.addLast(20);
        System.out.println(L.getFirst());
        System.out.println(L.getLast());
        System.out.println(L.size());
    }
}
