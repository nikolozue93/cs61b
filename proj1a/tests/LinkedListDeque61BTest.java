import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import deque.*;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /* In this test we verify that the size method works correctly
       With different kind of data types, and with different added elements */
    public void testSize(){
         Deque61B<String> lld0 = new LinkedListDeque61B<>();

         lld0.addLast("Last");
         lld0.addFirst("First");
         assertThat(lld0.size() == 2).isTrue();


         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(-5);

         assertThat(lld1.size() == 3).isTrue();
         assertThat(lld1.size() == 9).isFalse();
    }

    @Test
    public void testIsEmpty(){
        Deque61B<String> lld0 = new LinkedListDeque61B<>();
        assertThat(lld0.isEmpty()).isTrue();

        lld0.addLast("Last");
        assertThat(lld0.isEmpty()).isFalse();

        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void testGet(){
        Deque61B<String> lld0 = new LinkedListDeque61B<>();
        //assertWithMessage("Index out of bounds").that(lld0.get(228) == null);
        lld0.addLast("Last");
        lld0.addFirst("First");
        lld0.addFirst("Firsttt");
        lld0.addFirst("Firsttt");
        lld0.addFirst("heh");
        lld0.addFirst("das");
        lld0.addFirst("dsadw");
        String s = lld0.get(6);

        assertThat(s.equals("Last")).isTrue();
        s = lld0.get(0);
        assertThat(s.equals("dsadw")).isTrue();

        s = lld0.get(2);
        assertThat(s.equals("heh")).isTrue();
        s = lld0.get(5);
        assertThat(s.equals("First")).isTrue();

        // verifying null return
        s = lld0.get(-1);
        s = lld0.get(2421);
    }

    @Test
    public void testGetRecursive(){
        Deque61B<String> lld0 = new LinkedListDeque61B<>();
        //assertWithMessage("Index out of bounds").that(lld0.get(228) == null);
        lld0.addLast("Last");
        lld0.addFirst("First");
        lld0.addFirst("Firsttt");
        lld0.addFirst("Firsttt");
        lld0.addFirst("heh");
        lld0.addFirst("das");
        lld0.addFirst("dsadw");
        String s = lld0.getRecursive(6);

        assertThat(s.equals("Last")).isTrue();
        s = lld0.getRecursive(0);
        assertThat(s.equals("dsadw")).isTrue();

        s = lld0.getRecursive(2);
        assertThat(s.equals("heh")).isTrue();
        s = lld0.getRecursive(5);
        assertThat(s.equals("First")).isTrue();

        // verifying null return
//        s = lld0.getRecursive(-1);
//        s = lld0.getRecursive(2421);
    }

    @Test
    public void testRemoveFirst(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back");
        lld1.addFirst("middle");
        lld1.addFirst("front");
        lld1.addLast("Last");
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly( "middle", "back", "Last").inOrder();
        assertThat(lld1.size() == 3).isTrue();

        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(  "back", "Last").inOrder();

        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(  "Last").inOrder();

        lld1.removeFirst();
        assertThat(lld1.toList().isEmpty()).isTrue();

        lld1.removeFirst();

        assertThat(lld1.size() == 0).isTrue();
    }

    @Test
    public void testRemoveLast(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back");
        lld1.addFirst("middle");
        lld1.addFirst("front");
        lld1.addLast("Last");
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly( "front", "middle", "back").inOrder();
        assertThat(lld1.size() == 3).isTrue();

        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(  "front", "middle").inOrder();

        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(  "front").inOrder();

        lld1.removeLast();
        assertThat(lld1.toList().isEmpty()).isTrue();

        lld1.removeLast();

        assertThat(lld1.size() == 0).isTrue();
    }

//    @Test
//    public void testing(){
//        Deque61B<Double> lld1 = new LinkedListDeque61B<>();
//        int SR = 44100;
//        double frequency = 5126.7;
//        int capacity = (int) Math.round(SR / frequency);
//
//        for(int i = 0; i < capacity; i++){
//            lld1.addFirst(0.0);
//        }
//
//        double r;
//        for(int i = 0; i < lld1.size(); i++){
//            r = Math.random() - 0.5;
//
//            lld1.remove(i);
//
//            lld1.addFirst(r);
//        }
//    }

    @Test
    public void testing(){
        Deque61B<Double> lld1 = new LinkedListDeque61B<>();
        int SR = 44100;
        double frequency = 5126.7;
        int capacity = (int) Math.round(SR / frequency);

        for(int i = 0; i < capacity; i++){
            lld1.addFirst(0.0);
        }

        double r;
        for(int i = 0; i < lld1.size(); i++){
            r = Math.random() - 0.5;
            lld1.addFirst(r);
            lld1.removeLast();
        }
    }
//    @Test
//    public void testGetNode(){
//        Deque61B<String> lld1 = new LinkedListDeque61B<>();
//
//        lld1.addFirst("back");
//        lld1.addFirst("middle");
//        lld1.addFirst("front");
//        lld1.addLast("Last");
//        lld1.remove(1);
//        assertThat(lld1.toList()).containsExactly( "front", "back", "Last").inOrder();
//        assertThat(lld1.size() == 3).isTrue();
//
//        lld1.remove(0);
//        assertThat(lld1.toList()).containsExactly(  "back", "Last").inOrder();
//
//        lld1.remove(1);
//        assertThat(lld1.toList()).containsExactly(  "back").inOrder();
//
//        lld1.remove(0);
//        assertThat(lld1.toList().isEmpty()).isTrue();
//
//        lld1.removeLast();
//
//        assertThat(lld1.size() == 0).isTrue();
//    }
}