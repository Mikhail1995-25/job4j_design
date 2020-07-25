package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgZip {

    private final String[] args;
    private final Map<String, String> values = new HashMap<>();


    public ArgZip(String[] args) {
        this.args = args;
    }

    public String directory() {
        return parameters("d");
    }

    public String exclude() {
        return parameters("e");
    }

    public String outPut() {
        return parameters("o");
    }

    public void separator(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }

        Arrays.stream(args)
                .map(m -> m.split("="))
                .forEach(value -> values.put(value[0].substring(1), value[1]));
    }

    public String parameters(String args) {
        if (!values.containsKey(args)) {
            throw new IllegalArgumentException();
        }
        return values.get(args);
    }
}
