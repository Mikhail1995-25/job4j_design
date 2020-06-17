package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        T result;
       if (head != null) {
           Node<T> node = head;
           result = node.value;
           head = node.next;
           node.next = null;
       } else {
           throw new NoSuchElementException();
       }
       return result;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result;
        if (head.next == null) {
            result = head.value;
            head = null;
        } else {
            Node<T> n = head;
            result = n.next.value;
            n.next = null;
        }
       return result;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
