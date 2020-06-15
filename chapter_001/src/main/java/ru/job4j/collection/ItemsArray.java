package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ItemsArray<E> implements Iterable<E> {
    Node<E> first;
    Node<E> last;
    private int val = 0;
    private int modCount = 0;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    public void add(E value) {
       if (val == 0) {
           Node<E> node = new Node<>(value);
           first = node;
           last = node;
           val++;
           modCount++;
       } else {
           Node<E> node = new Node<>(value);
           node.previous = last;
           last.next = node;
           last = node;
           val++;
           modCount++;
       }
    }

    E get(int index) {
        if (index <= val / 2) {
            Node<E> result = first;
            for (int i = 1; i <= Objects.checkIndex(index, val); i++) {
                result = result.next;
            }
            return result.element;
        }
        Node<E> result = last;
        for (int i = 0; i < val - index - 1; i++) {
            result = result.previous;
        }
        return result.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = first;
            private int point = modCount;

            @Override
            public boolean hasNext() {
                if (point != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E res = node.element;
                node = node.next;
                return res;
            }
        };
    }
}
