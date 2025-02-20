package deque;

import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;
    
    private class Node {
        public Node prev;
        T item;
        public Node next;

        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque61B(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node firstNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
//        size += 1;
//        if(sentinel.next == sentinel){
//            //sentinel.next.item = x;
//            Node sentNext = sentinel.next;
//            sentinel.next = new Node(sentinel, x, sentinel);
//            //sentNext.prev ------------------------^^^^
//        } else {
//            Node first = sentinel.next;
//            Node second = first.prev;
//            sentinel.next = new Node(sentinel, x, second);
////            Node sentNext = sentinel.next;
////            Node sentPrev = sentNext.prev;
////            sentinel.next = new Node(sentinel, x, sentNext.next);
        }


        //sentPrev = sentNext;
//    }

    @Override
    public void addLast(T x) {
        // this DIDN'T WORK!! Node secondToLast = new Node(sentinel.prev, sentinel.prev.item, sentinel.next);
        Node secondToLast = sentinel.prev;
        Node newLast = new Node(secondToLast, x, sentinel);
        // as sentinel.prev is always pointing at the last element
        // we should change it
        // if we were to live it unchanged, then every other time
        // we affect the last item, the list wont grow
        sentinel.prev = newLast;

        secondToLast.next = newLast;
        //Node lastPrev = sentinel.prev.next;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node p = this.sentinel.next;

        // we can use size? don't think so
        while(p != sentinel){
            list.add(p.item);
            p = p.next;
        }

//        int len = this.size;
//        while(len > 0){
//            list.add(p.item);
//            len--;
//            p = p.next;
//        }

        return list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
