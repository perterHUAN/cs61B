import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int initialSize = 8;
    private T[] store = (T[]) new Object[initialSize];
    private int frontIndex = 1;
    private int backIndex = 0;
    private int size = 0;
    private double RFECTOR = 1.5; // 扩容

    public ArrayDeque61B() {
    }

    @Override
    public void addFirst(T x) {
        if (size == store.length) {
            resizeUp();
        }
        ++size;
        frontIndex = Math.floorMod(frontIndex - 1, store.length);
        store[frontIndex] = x;
    }

    public void resizeUp() {
        T[] newStore = (T[]) new Object[(int)Math.ceil(store.length * RFECTOR)];
        for(int i = 0; i < size; ++i) {
            newStore[i] = store[Math.floorMod(frontIndex + i, store.length)];
        }
        frontIndex = 0;
        backIndex = size - 1;

        store = newStore;
    }

    @Override
    public void addLast(T x) {
        if (size == store.length) {
            resizeUp();
        }
        ++size;
        backIndex = Math.floorMod(backIndex + 1, store.length);
        store[backIndex] = x;
    }

    @Override
    public List<T> toList() {
        List<T> res = new ArrayList<>();
        int idx = frontIndex;
        int cnt = size;
        while (cnt > 0) {
            res.add(store[idx]);
            --cnt;
            idx = Math.floorMod(idx + 1, store.length);
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }


    private void resizeDown() {
        T[] newStore = (T[]) new Object[(int)Math.ceil(store.length / 2)];
        for (int i = 0; i < size; ++i) {
            newStore[i] = store[Math.floorMod(frontIndex + i, store.length)];
        }
        store = newStore;
        frontIndex = 0;
        backIndex = size - 1;
    }

    private double usageRadio() {
        return size * 1.0 / store.length;
    }

    @Override
    public T removeFirst() {
        --size;
        T last = store[frontIndex];
        store[frontIndex] = null;
        frontIndex = Math.floorMod(frontIndex + 1, store.length);

        if (usageRadio() < 0.25) {
            resizeDown();
        }

        return last;
    }

    @Override
    public T removeLast() {
        --size;
        T last = store[backIndex];
        store[backIndex] = null;
        backIndex = Math.floorMod(backIndex + 1, store.length);

        if (usageRadio() < 0.25) {
            resizeDown();
        }

        return last;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) return null;

        int idx = frontIndex;
        while (index > 0) {
            idx = Math.floorMod(idx + 1, store.length);
        }
        return store[idx];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public int getCapacity() {
        return store.length;
    }
}
