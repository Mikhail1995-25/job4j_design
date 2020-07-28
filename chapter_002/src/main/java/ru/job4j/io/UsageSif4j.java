package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageSif4j {

    private final static Logger LOG = LoggerFactory.getLogger(UsageSif4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace massage");
        LOG.debug("debug massage");
        LOG.info("info massage");
        LOG.warn("warn massage");
        LOG.error("error massage");
    }
}
