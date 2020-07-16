package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
   private List<String> line = new ArrayList<>();
   private List<String> list = new ArrayList<>();


    public void unavailable(String source, String target) {
        boolean b = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(line::add);
            for (String string : line) {
                if((string.startsWith("200") || string.startsWith("300")) && !b) {
                   String[] arr = string.split(" ");
                   list.add(arr[1]);
                    b = true;
                }
                if ((string.startsWith("400") || string.startsWith("500")) && b) {
                    String[] arr = string.split(" ");
                    list.add(arr[1]);
                    b = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addunavailable(list, target);
    }

    private void addunavailable(List<String> lines, String target) {
        int i = 0;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String string : lines) {
                out.write(string);
                i++;
                if (i % 2 == 0) {
                    out.write(System.lineSeparator());
                } else {
                    out.write(" ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
