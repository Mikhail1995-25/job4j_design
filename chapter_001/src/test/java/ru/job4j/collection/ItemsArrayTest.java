package ru.job4j.collection;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemsArrayTest {
    private ItemsArray<String> items;

    @Before
    public void setUp() {
        items = new ItemsArray<>();
        items.add("first");
        items.add("second");
        items.add("third");
    }

    @Test
    public void whenAddElements() {
        assertThat(items.get(0), is("first"));
        assertThat(items.get(1), is("second"));
        assertThat(items.get(2), is("third"));
    }

    @Test
    public void whenItemsAdd() {
        assertThat(items.get(2), is("third"));
    }

    @Test
    public void whenExpectedTestIterator() {
        Iterator iterator = items.iterator();
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.next(), is("third"));
    }
}
