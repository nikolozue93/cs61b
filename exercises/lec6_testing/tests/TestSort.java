import static com.google.common.truth.Truth.assertThat;
import org.junit.jupiter.api.Test;

public class TestSort {
    @Test
    public void testFindSmallest(){
        String[] input = {"rawr", "a", "zaza", "newway"};
        int expected = 1;
        int actual = Sort.findSmallest(input, 0);

        expected = 3;
        actual = Sort.findSmallest(input, 2);
        assertThat(actual).isEqualTo(expected);
    }

    /** Tests the sort method of the Sort class. */
    @Test
    public void testSort(){
        String[] input = {"rawr", "a", "zaza", "newway"};
        String[] expected = {"a", "newway", "rawr", "zaza"};
        Sort.sort(input);

        assertThat(input).isEqualTo(expected);
    }
}
