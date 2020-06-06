package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(final int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return isPrimeNumber();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    public  boolean isPrimeNumber() {
        boolean b = false;
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                point = i;
                b = true;
                break;
            }
        }
        return b;
    }
}