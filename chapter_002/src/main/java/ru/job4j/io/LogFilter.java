package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public static List<String> filter(String lines) {
        List<String> line = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lines))) {
            String lin;
            while (in.ready()) {
                lin = in.readLine();
                String[] arr = lin.split(" ");
                if (arr[arr.length - 2].equals("404")) {
                    line.add(lin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line : log) {
                out.write(line);
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String line : log) {
            System.out.println(line);
        }
        save(log, "404.txt");
    }
}
