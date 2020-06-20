package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        T first = linked.iterator().next();
        linked.deleteFirst();
        return first;
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public void pushLast(T value) {
        linked.addLast(value);
    }

    public T popLast() {
        T last = linked.iterator().next();
        linked.deleteLast();
        return last;
    }
}
