package ru.job4j.collection;
import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class SimpleSetTest {
   private SimpleSet<Integer> array;

    @Before
    public void setUp() {
        array = new SimpleSet<Integer>();
        array.addSet(1);
        array.addSet(2);
        array.addSet(3);
        array.addSet(3);
        array.addSet(4);
        array.addSet(5);
    }

    @Test
    public void whenExpectedSetCollection() {
        Iterator<Integer> it = array.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }

    @Test
    public void whenTestSetExpected() {
        SimpleSet<String> set = new SimpleSet<>();
        set.addSet("Mikhail");
        set.addSet("Vlad");
        set.addSet("Vlad");
        set.addSet("Sergey");
        set.addSet("Mikhail");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("Mikhail"));
        assertThat(it.next(), is("Vlad"));
        assertThat(it.next(), is("Sergey"));
    }
}
