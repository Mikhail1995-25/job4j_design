package ru.job4j.io;

import net.sf.saxon.trans.SymbolicName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
        Analizy analizy = new Analizy();
        File source = folder.newFile("next.txt");
        File target = folder.newFile("unavailable.txt");
        try (PrintWriter out = new PrintWriter(new FileWriter(source))) {
            out.println("200 10:56:01" + System.lineSeparator()
                        + "200 10:57:01" + System.lineSeparator()
                        + "400 10:58:01" + System.lineSeparator()
                        + "200 10:59:01" + System.lineSeparator()
                        + "500 11:01:02" + System.lineSeparator()
                        + "200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder string = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(string::append);
            String result = ("10:58:01 10:59:01" + "11:01:02 11:02:02");
            assertThat(result, is(string.toString()));
        }
    }
}
