package ru.job4j.collection;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class HashDataTest {

    @Test
    public void whenTestAddItem() {
        HashData<Integer, String> data = new HashData<>();
        data.insert(1, "one");
        data.insert(2, "two");
        data.insert(3, "three");
        assertThat(data.get(1), is("one"));
        assertThat(data.get(2), is("two"));
        assertThat(data.get(3), is("three"));
    }

    @Test
    public void whenTestExpectedDeleteItem() {
        HashData<String, String> data = new HashData<>();
        data.insert("1", "Mikhail");
        data.insert("2", "Vlad");
        assertThat(data.delete("1"), is(true));
        assertThat(data.delete("2"), is(true));
    }

    @Test
    public void whenTestSameKeys() {
        HashData<Integer, String> data = new HashData<>();
        data.insert(1, "one");
        data.insert(1, "two");
        assertThat(data.get(1), is("one"));
    }

    @Test
    public void whenTestArrayLengthOrIncrease() {
        HashData<Integer, Integer> data = new HashData<>();
        data.insert(1, 2);
        data.insert(2, 4);
        data.insert(3, 5);
        data.insert(4, 6);
        data.insert(5, 7);
        data.insert(6, 8);
        data.insert(7, 9);
        data.insert(8, 10);
        data.insert(9, 11);
        data.insert(10, 12);
        data.insert(11, 13);
        data.insert(12, 14);
        data.insert(13, 15);
        data.insert(14, 16);
        data.insert(15, 17);
        data.insert(16, 18);
        data.insert(17, 19);
        assertThat(data.get(17), is(19));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTestIterator() {
        HashData<Integer, Integer> data = new HashData<>();
        data.insert(1, 2);
        data.insert(2, 3);
        Iterator<Integer> it = data.iterator();
        assertThat(2, is(it.next()));
        assertThat(3, is(it.next()));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExpectedIterator() {
        HashData<Integer, String> data = new HashData<>();
        data.insert(1, "one");
        data.insert(2, "two");
        data.insert(3, "three");
        Iterator<String> it = data.iterator();
        assertThat("one", is(it.next()));
        assertThat("two", is(it.next()));
        assertThat("three", is(it.next()));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenUseIteratorAndModList() {
        HashData<Integer, String> data = new HashData<>();
       data.insert(1, "one");
       Iterator<String> it = data.iterator();
       data.insert(2, "two");
       it.next();
    }
}
