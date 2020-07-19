package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        StringBuilder string = new StringBuilder();
       try (BufferedReader reader = new BufferedReader(new FileReader(source));
      BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
          String str = reader.readLine();
          String res = null;
          boolean b = true;
          while (str != null) {
              if (b && (str.startsWith("400") || str.startsWith("500"))) {
               res = str.substring(4);
               b = false;
              }
              if (!b && (str.startsWith("200") || str.startsWith("300"))) {
                string.append(res.strip()).
                        append(" ").
                        append(str.substring(4).strip()).
                        append(System.lineSeparator());
                  b = true;
              }
              str = reader.readLine();
          }
          out.write(string.toString());
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
