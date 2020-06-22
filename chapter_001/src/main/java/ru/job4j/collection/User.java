package ru.job4j.collection;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children + ", birthday=" + birthday + '}';
    }

    public static void main(String[] args) {
        User first = new User("Vlad", 2, new GregorianCalendar(1998, 2, 20));
        User second = new User("Vlad", 2, new GregorianCalendar(1998, 2, 20));

        Map<User, Object> map = new HashMap<>();
        map.put(first, "Vlad");
        map.put(second, "Vlad");

        /**
         * 3. Переопределить только hashCode [#297000]
         * для вычисления hashcode используется формула 31 * поле * поле.
         * так как hashcode обьектов у нас будет одинаковый, но метод equals будет разный.
         * Далее перейдем к выполнению метода endEntry() который добавит значение в карту,
         * найдет свободную ячейку и вставит значение
         */
        System.out.println(first.hashCode() + " " + second.hashCode());

        //for (User user : map.keySet()) {
          // System.out.println(user.toString());
            /**2. Не перекрывать equals hashCode [#297002]
             * Так как методы equals и hashcode не переопределены, hashcode будет разный у каждого
             * обьекта, хотя по бизнес логике они должны быть одинаковыми,
             * 1 мы проверяем что таблица не пустая, далее
             * вычисляем hashcode у ключа по битовым сдвигам,
             * далее определяем позицию indexOf.
             *
             */
        }
        }
    //}
