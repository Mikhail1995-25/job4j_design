package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenExpectedAnalizy() {
        Analizy analizy = new Analizy();
        String source = "./src/main/next.txt";
        String target = "./src/main/unavailable.txt";
        analizy.unavailable(source, target);
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            assertThat(in.readLine(), is("10:58:01 10:59:01"));
            assertThat(in.readLine(), is("11:01:02 11:02:02"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void rude() throws IOException {
        File source = folder.newFile("./src/main/next.txt");
        File target = folder.newFile("./src/main/unavailable.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.print("10:57:01 10:59:01");
        }
    }
}
