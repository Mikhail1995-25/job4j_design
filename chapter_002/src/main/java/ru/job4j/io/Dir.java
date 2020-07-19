package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s" + file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Not directory %s" + file.getAbsoluteFile());
        }
       System.out.println("Имя файла -> " + file.getName());
        System.out.println("Размер файла -> " + file.length() + " байт");
    }
}
