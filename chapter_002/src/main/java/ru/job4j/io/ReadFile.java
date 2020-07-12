package ru.job4j.io;

import java.io.FileInputStream;

public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            //System.out.println(text);
            String[] line = text.toString().split(System.lineSeparator());
            for (String lines : line) {
                System.out.println(lines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
