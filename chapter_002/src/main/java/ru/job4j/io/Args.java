package ru.job4j.io;

import java.util.*;

public class Args {

    private String[] args;
    private Map<String, String> values = new HashMap<>();

    public Args(String[] args) {
        this.args = args;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void attributes(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].substring(1).equals("m")
                    || (args[i].substring(1).equals("r")
                    || (args[i].substring(1).equals("f")))) {
                values.put(args[i].substring(1), null);
            } else if (args[i].substring(1).equals("n")
                    || (args[i].substring(1).equals("o")
                    || (args[i].substring(1).equals("d")))) {
                values.put(args[i].substring(1), args[i + 1]);
            }
        }
    }

    public String valid(String args) {
        if (!values.containsKey(args)) {
            throw new IllegalArgumentException("-d - директория в которая начинать поиск. "
                   + " -n - имя файл, маска, либо регулярное выражение. "
                   + " -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение. "
                    + " -o - результат записать в файл.");
        }
        return values.get(args);
    }

    public String mask() {
        return valid("m");
    }

    public String regex() {
        return valid("r");
    }

    public String full() {
        return valid("f");
    }

    public String name() {
        return valid("n");
    }

    public String write() {
        return valid("o");
    }

    public String directory() {
        return valid("d");
    }
}
