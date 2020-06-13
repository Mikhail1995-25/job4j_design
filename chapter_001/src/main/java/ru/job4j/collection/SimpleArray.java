package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int point = 0;
    private int pointer = 0;

    public T get(int index) {
         return (T) container[Objects.checkIndex(index, point)];
    }

    public void add(T model) {
        if (point >= container.length) {
           container = Arrays.copyOf(container, container.length * 2);
        }
        container[point++] = model;
        pointer++;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            int i = 0;
            int p = pointer;
            @Override
            public boolean hasNext() {
                return i < point;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (p != pointer) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[i++];
            }
        };
    }

}
