package ru.job4j.collection;

public class SimpleQueue<T> {
   private final SimpleStack<T> in = new SimpleStack<>();
   private final SimpleStack<T> out = new SimpleStack<>();
   int size = 0;

   public T poll() {
      for (int i = 0; i < size; i++) {
          T t = in.pop();
          out.push(t);
      }
      size--;
       return out.pop();
   }

   public void push(T value) {
       size++;
       in.push(value);
   }
}
