package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int point = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean b =  point < data.length && column < data[point].length;
        if (!b && point < data.length) {
            point++;
            column = 0;
            return hasNext();
        }
        return b;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[point].length) {
            point++;
            column = 0;
        }
        return data[point][column++];
    }
}