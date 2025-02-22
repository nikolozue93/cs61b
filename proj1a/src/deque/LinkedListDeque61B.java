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
        Node first = new Node(sentinel, x, sentinel);
        sentinel.next.prev = first;
        first.next = sentinel.next; // we can directly do this, in first node creation, in next parameter place
        sentinel.next = first;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node secondToLast = sentinel.prev;
        Node newLast = new Node(secondToLast, x, sentinel);
        // as sentinel.prev is always pointing at the last element
        // we should change it
        // if we were to live it unchanged, then every other time
        // we affect the last item, the list wont grow
        sentinel.prev = newLast;

        secondToLast.next = newLast;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node p = this.sentinel.next;

        // we can use size? don't think so (Didn't work)
        while(p != sentinel){
            list.add(p.item);
            p = p.next;
        }
        return list;
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
        if(this.isEmpty()){
            return null;
        }
        T item = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;

        return item;
    }

    @Override
    public T removeLast() {
        if(this.isEmpty()){
            return null;
        }
        T item = sentinel.prev.item;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;

        return item;
    }

    @Override
    public T get(int index) {

        if(index == -1 || index >= size){
            return null;
        }

        Node sent = sentinel;
        for(int i = 0; i <= index; i++){
            sent = sent.next;
        }

        return sent.item;
    }

    // if the first statement is not true, we are sending the first real node
    // to the helper function
    @Override
    public T getRecursive(int index) {
        if(isEmpty() || index > size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index).item;
    }

    /** We need helper function to create a copy node, which wont affect
     * the original list. Point us to the node we are seeking for
     * and then return
     */
    public Node getRecursiveHelper(Node node, int index){
        if(index == 0){
            return node;
        }
        return getRecursiveHelper(node.next, index - 1);
    }


    /** These last two methods are my experiments, they are both working correctly
     * we are removing any node we want, with just index.
     */

//    public Node getNode(int index){
//        if(this.isEmpty() || index > this.size() || index < 0){
//            return null;
//        }
//
//        Node curr = sentinel.next;
//        while(index > 0){
//            curr = curr.next;
//            index--;
//        }
//
//        return curr;
//    }
//
//    @Override
//    public T remove(int index){
//        if(this.isEmpty() || index > this.size() || index < 0){
//            return null;
//        }
//
//        Node curr = this.getNode(index); // lets imagine this returns Node with get()
//        T item = curr.item;
//
//        curr.prev.next = curr.next;
//        curr.next.prev = curr.prev;
//        size -= 1;
//        return item;
//    }
}
