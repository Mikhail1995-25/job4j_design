package ru.job4j.collection;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class ModelTest {

    @Test
    public void whenTestHash() {
        Model model = new Model("blue", 123);
        Model model1 = new Model("blue", 123);
        Map<Integer, Model> map = new HashMap<>();
        map.put(1, model);
        assertThat(map.get(1), is(model));
        assertThat(map.get(1), is(model1));

    }
}
