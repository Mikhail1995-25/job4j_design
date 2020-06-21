package ru.job4j.collection;


import java.util.Iterator;


public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray<E> container = new SimpleArray<>();

    public void addSet(E value) {
        if (!contains(value)) {
            container.add(value);
        }
    }

    public boolean contains(E element) {
        boolean b = false;
        for (E result : container) {
            if (result.equals(element)) {
                b = true;
                break;
            }
        }
        return b;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
