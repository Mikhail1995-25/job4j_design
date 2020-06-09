package ru.job4j.generic;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    Store<Role> role = new RoleStore<>();

    @Before
    public void setUp() {
        role.add(new Role("123"));
        role.add(new Role("456"));
        role.add(new Role("789"));
    }

    @Test
    public void whenDeleteItem(){
        assertThat(role.delete("123"), is(true));
        assertNull(role.findById("123"));
    }

    @Test
    public void whenReplaceItem() {
        assertThat(role.replace("123", new Role("456")), is(true));
    }
}
