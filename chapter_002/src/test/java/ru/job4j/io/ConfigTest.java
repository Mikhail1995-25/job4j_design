package ru.job4j.io;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/pair_comment.propirties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenWithoutPairComment() {
        String path = "./src/main/app2.propirties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is("Belov"));
    }
}
