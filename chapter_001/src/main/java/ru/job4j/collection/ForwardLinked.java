package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

   public void addFirst(T value) {
       Node<T> first = new Node<>();
       first.value = value;
       if (head == null) {
           head = first;
           last = first;
       } else {
           first.next = head;
           head = first;
       }
   }

   public void addLast(T value) {
       Node<T> tail = new Node<>();
       tail.value = value;
       if (last == null) {
           head = tail;
           last = tail;
       } else {
           last.next = tail;
           last = tail;
       }
   }


   void delete() {
       if (head == null) {
           throw new NoSuchElementException();
       } else if (head == last) {
          head = null;
          last = null;
          return;
       }
       Node<T> node = head;
       while (node.next != null) {
           if (last == node.next) {
               last = node;
           }
           node.next = node.next.next;
           return;
       }
       node = node.next;
   }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null || last != null;
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






    }
}
