import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addFirst(-1);
        lld1.addLast(2);
        lld1.addFirst(-2);
        lld1.addFirst(3);
        lld1.addLast(4);
        lld1.addLast(8);

        Deque61B<Character> lld2 = new ArrayDeque61B<>();
        lld2.addLast('a');
        lld2.addLast('b');
        lld2.addFirst('c');
        lld2.addLast('d');
        lld2.addLast('e');
        lld2.addFirst('f');
        lld2.addLast('g');
        //lld2.addFirst('h');

        List<Character> toList = lld2.toList();

        assertThat(lld2.toList()).containsExactly('e', 'g', 'f', 'c', 'a', 'b', 'd').inOrder();
    }

    @Test
    /* In this test we verify that the size method works correctly
       With different kind of data types, and with different added elements */
    public void testSize(){
        Deque61B<String> lld0 = new ArrayDeque61B<>();

        lld0.addLast("Last");
        lld0.addFirst("First");
        assertThat(lld0.size() == 2).isTrue();


        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(-5);

        assertThat(lld1.size() == 3).isTrue();
        assertThat(lld1.size() == 9).isFalse();
    }

    @Test
    public void testIsEmpty(){
        Deque61B<String> lld0 = new ArrayDeque61B<>();
        assertThat(lld0.isEmpty()).isTrue();

        lld0.addLast("Last");
        assertThat(lld0.isEmpty()).isFalse();

        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void testGet(){
        Deque61B<String> lld0 = new ArrayDeque61B<>();
        //assertWithMessage("Index out of bounds").that(lld0.get(228) == null);
        lld0.addLast("Last");
        lld0.addFirst("First");
        lld0.addFirst("Firsttt");
        lld0.addFirst("Firsttt");
        lld0.addFirst("heh");
        lld0.addFirst("das");
        lld0.addFirst("dsadw");
        lld0.addFirst("ky");
        lld0.addFirst("ewq");
        String s = lld0.get(7);

        assertThat(s.equals("dsadw")).isTrue();
        s = lld0.get(0);
        assertThat(s.equals("das")).isTrue();

        s = lld0.get(2);
        assertThat(s.equals("Firsttt")).isTrue();
        s = lld0.get(5);
        assertThat(s.equals("Last")).isTrue();

        // verifying null return
        s = lld0.get(-1);
        s = lld0.get(2421);
    }

    @Test
    public void testRemoveFirst(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("back");
        lld1.addFirst("middle");
        lld1.addFirst("front");
        lld1.addLast("Last");
        lld1.removeFirst();

        List<String> toList = lld1.toList();
        assertThat(lld1.toList()).containsExactly( "middle", "back", "Last").inOrder();
        assertThat(lld1.size() == 3).isTrue();

        lld1.removeFirst();
        toList = lld1.toList();
        assertThat(lld1.toList()).containsExactly(  "back", "Last").inOrder();

        lld1.removeFirst();
        toList = lld1.toList();
        assertThat(lld1.toList()).containsExactly(  "Last").inOrder();

        lld1.removeFirst();
        toList = lld1.toList();
        assertThat(lld1.toList().isEmpty()).isTrue();

        lld1.removeFirst();

        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void testRemoveLast(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

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

        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void testGetRecursive(){
        Deque61B<String> lld0 = new ArrayDeque61B<>();
        //assertWithMessage("Index out of bounds").that(lld0.get(228) == null);
        lld0.addLast("Last");
        lld0.addFirst("First");
        lld0.addFirst("Firsttt");
        lld0.addFirst("Firsttt");
        lld0.addFirst("heh");
        lld0.addFirst("das");
        lld0.addFirst("dsadw");
        String s = lld0.getRecursive(7);

        assertThat(s.equals("dsadw")).isTrue();
        s = lld0.getRecursive(0);
        assertThat(s.equals("das")).isTrue();

        s = lld0.getRecursive(2);
        assertThat(s.equals("Firsttt")).isTrue();
        s = lld0.getRecursive(5);
        assertThat(s.equals("Last")).isTrue();

        // verifying null return
        s = lld0.getRecursive(-1);
        s = lld0.getRecursive(2421);
    }
}
