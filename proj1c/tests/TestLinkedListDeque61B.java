import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestLinkedListDeque61B {

    @Test
    void testIterator() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("a");
        lld1.addFirst("b");
        lld1.addFirst("c");
        lld1.addLast("d");

        assertThat(lld1).containsExactly("c", "b", "a", "d");
    }

    @Test
    void testToEqual() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addFirst("a");
        lld1.addFirst("b");
        lld1.addFirst("c");

        lld2.addFirst("a");
        lld2.addFirst("b");
        lld2.addFirst("c");

        assertThat(lld1).isEqualTo(lld2);
    }


    @Test
    void testToString() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("a");
        lld1.addFirst("b");
        lld1.addFirst("c");

        assertThat(lld1.toString()).isEqualTo("{c, b, a}");
    }
}
