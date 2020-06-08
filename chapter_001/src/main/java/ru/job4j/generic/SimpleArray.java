package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int point = 0;

    public SimpleArray(int point) {
        this.data = new Object[point];
    }

    public void add(T model) {
        this.data[point++] = model;
    }

    public void set(int index, T model) {
          data[Objects.checkIndex(index, point)] = model;
    }

    public void remove(int index) {
        System.arraycopy(data, Objects.checkIndex(index + 1, point), data, index, data.length - 1 - index);
        data[data.length - 1] = null;
        point--;
    }

    public T get(int index) {
        return (T) data[Objects.checkIndex(index, point)];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return point < data.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}
