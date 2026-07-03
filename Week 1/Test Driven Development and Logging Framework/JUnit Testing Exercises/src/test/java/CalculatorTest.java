import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdd() {
        int a = 10;
        int b = 20;
        int expected = 30;
        int result = calculator.add(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testSubtract() {
        int a = 15;
        int b = 5;
        int expected = 10;
        int result = calculator.subtract(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testMultiply() {
        int a = 5;
        int b = 6;
        int expected = 30;
        int result = calculator.multiply(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testDivide() {
        int a = 10;
        int b = 2;
        double expected = 5.0;
        double result = calculator.divide(a, b);
        assertEquals(expected, result, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }

    @Test
    public void testAssertions() {
        assertEquals(5, calculator.add(2, 3));
        assertTrue(calculator.add(5, 3) > 5);
        assertFalse(calculator.subtract(5, 3) > 5);
        assertNull(null);
        assertNotNull(calculator);
    }
}
