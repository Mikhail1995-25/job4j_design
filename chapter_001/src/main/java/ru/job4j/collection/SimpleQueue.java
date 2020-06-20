package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
   private final ForwardLinked<T> o = new ForwardLinked<>();

   public T poll() {
      return o.deleteFirst();
   }

   public void push(T value) {
       o.addFirst(value);

   }
}
