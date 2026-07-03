import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    public void testFirst() {
        assertTrue(true);
    }

    @Test
    @Order(2)
    public void testSecond() {
        assertTrue(true);
    }

    @Test
    @Order(3)
    public void testThird() {
        assertTrue(true);
    }
}
