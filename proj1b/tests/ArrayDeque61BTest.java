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
    void testAddFirst() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(3);

        assertThat(dq.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    void testAddLast() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);

        assertThat(dq.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    void testAddFirstAndAddLast() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        dq.addFirst(1);
        dq.addLast(3);
        dq.addFirst(2);
        dq.addLast(4);

        assertThat(dq.toList()).containsExactly(2, 1, 3, 4).inOrder();
    }

    @Test
    void testResizeUpOnAddFirst() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();
        List<Integer> expected =  new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            dq.addFirst(i);
        }

        for (int i = 9; i >= 0; --i) {
            expected.add(i);
        }

        assertThat(dq.toList()).isEqualTo(expected);
    }


    @Test
    void testResizeUpOnAddLast() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();
        List<Integer> expected =  new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            dq.addLast(i);
            expected.add(i);
        }

        assertThat(dq.toList()).isEqualTo(expected);
    }

    @Test
    void testGet() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        assertThat(dq.get(-1)).isNull();
        assertThat(dq.get(10)).isNull();

        dq.addFirst(1);
        dq.addFirst(1);
        assertThat(dq.get(0)).isEqualTo(1);
    }


    @Test
    void testIsEmpty() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        assertThat(dq.isEmpty()).isTrue();

        dq.addLast(1);

        assertThat(dq.isEmpty()).isFalse();
    }

    @Test
    void testSize() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(3);

        assertThat(dq.size()).isEqualTo(3);
    }

    @Test
    void testRemoveFirst() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(3);

        dq.removeFirst();

        assertThat(dq.toList()).containsExactly(2, 1).inOrder();
    }

    @Test
    void testRemoveLast() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);

        dq.removeLast();

        assertThat(dq.toList()).containsExactly(1, 2).inOrder();
    }


    @Test
    void testRemoveLastAndRemoveFirst() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();

        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);

        dq.removeLast();
        dq.removeFirst();

        assertThat(dq.toList()).containsExactly(2).inOrder();
    }

    @Test
    void testResizeDownOnRemoveLast() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();
        // 8
        // usage < 0.25 (2) -> 8/2 = 4
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);

        dq.removeLast();
        dq.removeLast();

        assertThat(dq.toList()).containsExactly(1).inOrder();
        assertThat(dq.getCapacity()).isEqualTo(4);
    }

    @Test
    void testResizeDownOnRemoveFirst() {
        ArrayDeque61B<Integer> dq = new ArrayDeque61B<>();
        // 8
        // usage < 0.25 (2) -> 8/2 = 4
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);

        dq.removeFirst();
        dq.removeFirst();

        assertThat(dq.toList()).containsExactly(3).inOrder();
        assertThat(dq.getCapacity()).isEqualTo(4);
    }

}
