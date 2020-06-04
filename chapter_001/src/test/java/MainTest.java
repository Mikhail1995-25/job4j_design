import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class MainTest {
    @Test
    public void whenMain() {
        String s = Mains.see();
        assertThat(s, is("Hello word"));
    }
}
