package ru.job4j.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Mikhail Pushkarev";
        int age = 24;
        LOG.debug("User info name : {}, age : {}", name, age);
    }
}
