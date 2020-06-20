package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;
    private int modCount;
    private int size = 0;

   public void addLast(T value) {
       Node<T> first = new Node<>(value);
       if (head == null) {
           head = first;
       } else {
           last = head;
           while (last.next != null) {
               last = last.next;
               first.prev = last;
               last.next = first;
           }
           modCount++;
           size++;
       }
   }

   public void addFirst(T value) {
       Node<T> tail = new Node<>(value);
       if (size > 0) {
           head.prev = tail;
       }
       tail.next = head;
       head = tail;
       if (size <= 0) {
           last = tail;
       }
       size++;
       modCount++;
   }

   public T deleteFirst() {
       T data = null;
       if (size != 0) {
           data = last.value;
           last = last.prev;
       } else {
           throw new NoSuchElementException();
       }
       size--;
       modCount++;
       return data;
   }

   public T deleteLast() {
       T data = null;
       last = head;
       if (last != null) {
           while (last.next != null) {
               last = last.next;
           }
           data = binds(last);
       }
       return data;
   }

   public T binds(Node<T> node) {
       T data = node.value;
       Node<T> prev = node.prev;
       Node<T> next = node.next;
       if (prev != null) {
           prev.next = null;
           last = prev;
       } else {
           last = null;
           head = null;
       }
       size--;
       modCount++;
       return data;
   }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            int mode = modCount;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (mode != modCount) {
                    throw new ConcurrentModificationException();
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
        Node<T> prev;
        public Node(T value) {
            this.value = value;
        }






    }
}
