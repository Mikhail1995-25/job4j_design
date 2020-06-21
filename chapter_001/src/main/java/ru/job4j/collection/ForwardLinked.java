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
        if (size == 0) {
            head = first;
            last = first;
        } else {
            first.prev = last;
            last.next = first;
            last = first;
        }
        modCount++;
        size++;
    }



   public void addFirst(T value) {
      Node<T> tail = new Node<>(value);
      if (size == 0) {
          head = tail;
          last = tail;
      } else {
          head.prev = tail;
          tail.next = head;
          head = tail;
      }
       size++;
       modCount++;
   }

   public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
       T data = head.value;
       if (size == 1) {
           last = null;
           head = null;
       } else {
           head = head.next;
           head.prev = null;
       }
       size--;
       return data;
   }

   public T deleteLast() {
       T data = last.value;
       if (size == 1) {
           last = null;
           head = null;
       } else {
           last = last.prev;
           last.next = null;
       }
       size--;
       return data;
   }

   public int getSize() {
       return size;
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
