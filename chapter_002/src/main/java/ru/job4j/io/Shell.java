package ru.job4j.io;

import java.util.ArrayList;
import java.util.List;

public class Shell {
    private List<String> list = new ArrayList<>();
    private static final String STR = "/";

    public void cd(String path) {
        if (path.equals(STR)) {
            list.add(STR);
        } else {
            String[] str = path.split("/");
            list.add(STR);
            for (int i = 0; i < str.length; i++) {
                if (str[i].equals("..")) {
                    list.remove(list.size() - 1);
                    list.remove(list.size() - 1);
                } else if (!str[i].equals("..")) {
                    list.add(str[i]);
                }
            }
        }
    }

    public String pwd() {
        return list.toString().replace("[", "").replace("]", "").replace(",", "")
                .replace(" ", "");
    }
}
