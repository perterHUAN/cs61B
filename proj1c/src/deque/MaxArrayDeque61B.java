package deque;

import com.github.javaparser.metamodel.LiteralExprMetaModel;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> _comparator;
    public MaxArrayDeque61B(Comparator<T> comparator) {
        _comparator = comparator;
    }

    public T max() {
        if (this.size == 0) {
            return null;
        }
        Iterator<T> iterator = this.iterator();
        T res = iterator.next();

        while (iterator.hasNext()) {
            T item = iterator.next();

            if (_comparator.compare(res, item) < 0) {
               res = item;
            }
        }
        return res;
    }

    public T max(Comparator<T> comparator) {
        if (this.size == 0) {
            return null;
        }
        Iterator<T> iterator = this.iterator();
        T res = iterator.next();

        while (iterator.hasNext()) {
            T item = iterator.next();

            if (comparator.compare(res, item) < 0) {
                res = item;
            }
        }
        return res;
    }
}
