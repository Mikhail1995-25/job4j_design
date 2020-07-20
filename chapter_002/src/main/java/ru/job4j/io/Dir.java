package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s" + file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Not directory %s" + file.getAbsoluteFile());
        }
      System.out.println(String.format("size : %s", file.getTotalSpace()));
                System.out.print("Имя файла " + file.getName() + " Размер файла " + file.length() + " байт");
    }
}
