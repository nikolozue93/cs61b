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

    /* The first item if it exists is at sentinel.next */
    private IntNode sentinel;
    private int size;

    private IntNode first;

    /** creating empty SLList */
    public SLList(){
        //first = new IntNode(x, null);
        //first = null;

        sentinel = new IntNode(11, null); // what number we put in here doesnt really matter, as we are never retrieving that info, it doesnt change anything for us
                                                 // it is just holding data, we can put any number there
        size = 0;
    }

    /** 1 item SLList */
    public SLList(int x){
        sentinel = new IntNode(11, null);
        sentinel.next = new IntNode(x, null);

//        first = new IntNode(x, null);
//        size = 1;
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        //first = new IntNode(x, first);
        size++;
    }

    public int getFirst(){
        return sentinel.next.item;
        //return first.item;
    }

    public void addLast(int x){
        size++;

//        if(first == null){
//            first = new IntNode(x, null);
//        }

//        IntNode p = first;

        IntNode p = sentinel;
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
