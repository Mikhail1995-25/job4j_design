package ru.job4j.collection;

import net.sf.saxon.trans.SymbolicName;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
   ForwardLinked<T> in = new ForwardLinked<>();
   ForwardLinked<T> out = new ForwardLinked<>();


   public T poll() {
      T res;
      int index = in.getSize() - 1;
      for (int i = 0; i < index; i++) {
         out.addFirst(in.deleteFirst());
      }
      res = in.deleteFirst();
      for (int i = 0; i < index; i++) {
         in.addFirst(out.deleteFirst());
      }
      return res;
   }

   public void push(T value) {
      in.addFirst(value);

   }
}
