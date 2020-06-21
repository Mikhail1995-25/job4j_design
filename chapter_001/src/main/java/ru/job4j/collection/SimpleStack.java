package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public void pushLast(T value) {
        linked.addLast(value);
    }

    public T popLast() {
        return linked.deleteLast();
    }
}