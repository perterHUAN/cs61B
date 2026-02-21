import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    int size;
    // 哨兵
    Node<T> sentinel;

    public static class Node<T> {
        T item;
        Node<T> next;
        Node<T> pre;
    }

    public LinkedListDeque61B() {
        sentinel = new Node<>();
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    @Override
    public void addFirst(T x) {
        ++size;

        Node<T> newNode = new Node<>();
        newNode.item = x;

        newNode.pre = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.pre = newNode;
        sentinel.next = newNode;
    }

    @Override
    public void addLast(T x) {
        ++size;

        Node<T> newNode = new Node<>();
        newNode.item = x;

        newNode.pre = sentinel.pre;
        sentinel.pre.next = newNode;
        newNode.next = sentinel;
        sentinel.pre = newNode;
    }

    @Override
    public List<T> toList() {
        List<T> res = new ArrayList<>();
        Node<T> node = sentinel.next;
        while (node != sentinel) {
            res.add(node.item);
            node = node.next;
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

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        --size;

        Node<T> firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.pre = sentinel;

        firstNode.next = null;
        firstNode.pre = null;

        return firstNode.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        --size;

        Node<T> lastNode = sentinel.pre;
        sentinel.pre = lastNode.pre;
        lastNode.pre.next = sentinel;

        lastNode.next = null;
        lastNode.pre = null;

        return lastNode.item;
    }

    @Override
    public T get(int index) {
        Node<T> node = sentinel.next;
        while(index > 0 && node != sentinel) {
            node = node.next;
            --index;
        }
        return index == 0 && node != sentinel ? node.item : null;
    }

    private T getRecursiveHelper(Node<T> currentNode, int index) {
        if (index <= 0 || currentNode == sentinel) {
            return index == 0 && currentNode != sentinel ? currentNode.item : null;
        }

        return getRecursiveHelper(currentNode.next, index - 1);
    }
    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }
}
