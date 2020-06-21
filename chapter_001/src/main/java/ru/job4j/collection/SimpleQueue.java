package ru.job4j.collection;

public class SimpleQueue<T> {
   private ForwardLinked<T> in = new ForwardLinked<>();
   private ForwardLinked<T> out = new ForwardLinked<>();


   public T poll() {
      if (out.getSize() == 0) {
         while (0 < in.getSize()) {
            out.addFirst(in.deleteFirst());
         }
      }
      return out.deleteFirst();
   }

   public void push(T value) {
      in.addFirst(value);

   }
}
