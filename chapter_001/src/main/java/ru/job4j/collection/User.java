package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public static void main(String[] args) {
        User first = new User("Vlad", 2, new GregorianCalendar(1998, 2, 20));
        User second = new User("Vlad", 2, new GregorianCalendar(1998, 2, 20));

        Map<User, Object> map = new HashMap<>();
        map.put(first, "Vlad");
        map.put(second, "Vlad");

        for (User user : map.keySet()) {
            System.out.println(map);
        }
    }
}
/**
 * Происходит вывод целого уникального числа, за счет метода hashcode().
 * при запуске программы ключи обьектов сравниваются на null,
 * далее генерерируется hash. Поэтому мы получаем результат в виде целого числа.
 */