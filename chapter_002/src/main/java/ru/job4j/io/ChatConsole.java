package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ChatConsole {

    private static final String COMMAND_STOP = "стоп";
    private static final String COMMAND_NEXT = "продолжить";
    private static final String COMMAND_FINISH = "закончить";

    private List<String> write = new ArrayList<>();
    private List<String> reader = new ArrayList<>();

    private Random random = new Random();

    private boolean stopFlag = false;
    private boolean flag = false;

    private File textFirst;
    private File textSecond;

    public ChatConsole(File textFirst, File textSecond) {
        this.textFirst = textFirst;
        this.textSecond = textSecond;
    }

    public void write() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(textFirst)))) {
            for (String string : write) {
                out.println(string + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reader() {
        try (BufferedReader in = new BufferedReader(new FileReader(textSecond))) {
            in.lines().forEach(reader::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chatJob() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (!stopFlag) {
                System.out.println("Введите фразу: ");
                String str = in.readLine();
                write.add(str);
                reader();
                if (COMMAND_STOP.equals(str)) {
                    System.out.println("Чат остановлен!");
                    flag = true;
                } else if (COMMAND_NEXT.equals(str)) {
                    System.out.println("Продолжаем:");
                    flag = false;
                } else if (COMMAND_FINISH.equals(str)) {
                    System.out.println("Чат завершен!");
                    write();
                    stopFlag = true;
                    flag = true;
                }

                if (!flag) {
                    int i = random.nextInt(reader.size());
                    write.add(reader.get(i));
                    System.out.println(reader.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatConsole chatConsole = new ChatConsole(new File("dest.txt"), new File("results.txt"));
        chatConsole.chatJob();
    }
}
