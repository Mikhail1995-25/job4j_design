package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ChatConsole {

    private static final String COMMAND_STOP = "стоп";
    private static final String COMMAND_NEXT = "продолжить";
    private static final String COMMAND_FINISH = "закончить";

    private List<String> write = new ArrayList<>();
    private List<String> reader = new ArrayList<>();

    private Random random = new Random();

    private boolean stopFlag = false;
    private boolean flag = false;

    public void general(File textFirst, File textSecond) {
        try (PrintWriter in = new PrintWriter(new BufferedOutputStream(new FileOutputStream(textFirst)));
             BufferedReader out = new BufferedReader(new FileReader(textSecond))) {
            out.lines().forEach(reader::add);
            Scanner scanner = new Scanner(System.in);
            while (!stopFlag) {
                String str = scanner.nextLine();
                write.add(str);
                if (str.equals(COMMAND_STOP)) {
                    flag = true;
                } else if (str.equals(COMMAND_NEXT)) {
                    flag = false;
                } else if (str.equals(COMMAND_FINISH)) {
                    stopFlag = true;
                    flag = true;
                }
                for (String string : write) {
                    in.println(string + System.lineSeparator());
                }

                if (!flag) {
                  int i = random.nextInt(reader.size());
                  write.add(reader.get(i));
                  System.out.println(reader.get(i));
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ChatConsole console = new ChatConsole();
        console.general(new File("dest.txt"), new File("results.txt"));
    }
}
