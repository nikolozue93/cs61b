import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
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
        lld2.addFirst('h');


        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

}
