package ru.job4j.collection;
import org.junit.Test;
import org.junit.Before;

import java.util.*;
import static org.junit.Assert.*;

public class MailTest {

    private Map<String, Set<String>> userMap = new TreeMap<>();

    @Before
    public void whenTest() {
        userMap.put("User1", (new TreeSet<>(Set.of("xxx@mail.ru", "foo@gmail.com", "lol@mail.ru"))));
        userMap.put("User2", (new TreeSet<>(Set.of("foo@gmail.com", "ups@pisem.net"))));
        userMap.put("User3", (new TreeSet<>(Set.of("xyz@pisem.net", "vasy@pupkin.com"))));
        userMap.put("User4", (new TreeSet<>(Set.of("ups@pisem.net", "aaa@bbb.ru"))));
        userMap.put("User5", (new TreeSet<>(Set.of("xyz@pisem.net"))));
    }

    @Test
    public void whenExpectedMailClass() {
        Map<String, Set<String>> mail = Mail.mails(userMap);
        assertEquals(2, mail.size());
    }
}
