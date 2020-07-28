package ru.job4j.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotServer {

    private static final Logger LOG = LoggerFactory.getLogger(BotServer.class.getName());

    public static void main(String[] args) {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        try (BufferedReader out = new BufferedReader(new FileReader("chat.txt"))) {
            out.lines().forEach(list::add);
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
        boolean job = true;
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            while (job) {
                Socket socket = serverSocket.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(
                             socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("Hello")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello, dear friend.".getBytes());
                        } else if (str.contains("Exit")) {
                            socket.close();
                            job = false;
                            break;
                        } else if (str.contains("Any")) {
                            int i = random.nextInt(list.size());
                            String string = list.get(i);
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(string.getBytes());
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Socket not found", e);
        }
    }
    }

