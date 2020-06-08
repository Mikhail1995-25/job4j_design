package ru.job4j.it;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.SimpleArray;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class SimpleArrayTest {
    SimpleArray<Integer> simple;

    @Test
    public void whenAddItem() {
        simple = new SimpleArray<>(5);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.add(5);
        assertThat(simple.get(0), is(1));
        assertThat(simple.get(1), is(2));
        assertThat(simple.get(2), is(3));
        assertThat(simple.get(3), is(4));
        assertThat(simple.get(4), is(5));
    }

    @Test
    public void whenReplaceItem() {
        simple = new SimpleArray<>(5);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.add(5);
        simple.set(3, 10);
        assertThat(simple.get(3), is(10));
    }

    @Test
    public void whenRemoveItem() {
        simple = new SimpleArray<>(5);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.add(5);
        simple.remove(3);
        assertThat(simple.get(3), is(5));
    }
}
