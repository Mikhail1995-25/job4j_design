package ru.job4j.collection;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class AnalizeTest {
    List<Analize.User> prev = new ArrayList<>();
    List<Analize.User> cur = new ArrayList<>();

    @Before
    public void whenTest() {
        prev.add(new Analize.User(1, "User1"));
        prev.add(new Analize.User(2, "User2"));
        prev.add(new Analize.User(3, "User3"));
        prev.add(new Analize.User(4, "User4"));

        cur.add(new Analize.User(1, "User1"));
        cur.add(new Analize.User(2, "User2"));
        cur.add(new Analize.User(5, "User5"));
        cur.add(new Analize.User(6, "User6"));
        cur.add(new Analize.User(7, "User7"));
        cur.add(new Analize.User(8, "User8"));
        cur.add(new Analize.User(9, "User9"));
        cur.add(new Analize.User(10, "User10"));
    }

    @Test
    public void whenExpectedAnalizeUsersNameAndId() {
        Analize info = new Analize();
       System.out.println(info.diff(prev, cur).toString());
    }
}
