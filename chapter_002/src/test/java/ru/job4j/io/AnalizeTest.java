package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenExpectedAnalizy() {
        Analizy analizy = new Analizy();
        String source = "./src/main/next.txt";
        String target = "./src/main/unavailable.txt";
        analizy.unavailable(source, target);
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(list.contains("10:57:01 10:59:01"), is(true));
        assertThat(list.contains("11:01:02 11:02:02"), is(true));
    }
}
