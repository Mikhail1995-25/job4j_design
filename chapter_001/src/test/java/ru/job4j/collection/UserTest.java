package ru.job4j.collection;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class UserTest {

    @Test
    public void whenUserTestModelThenExpected() {
        User first = new User("Vlad", 2, new GregorianCalendar(1998, Calendar.MARCH, 20));
        User second = new User("Vlad", 2, new GregorianCalendar(1998, Calendar.MARCH, 20));
        assertThat(first.equals(second), is(false));
    }
}
