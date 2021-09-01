package mockito;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    public void setUp() {
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown() {
        validNumber = null;
    }

    @Test
    public void checkTest(){
        assertTrue(validNumber.check(5));
    }

    @Test
    public void checkNegativeTest(){
        assertFalse(validNumber.check(-5));
    }

    @Test
    public void checkStringTest(){
        assertFalse(validNumber.check("5"));
    }

    @Test
    public void checkZeroTest(){
        assertTrue(validNumber.checkZero(-57));
    }

    @Test
    public void checkZeroStringTest(){
        assertFalse(validNumber.checkZero("5"));
    }

    @Test
    public void checkZero0Test(){
        assertThrows(ArithmeticException.class, ()->validNumber.checkZero(0));
    }

    @Test
    public void doubleToIntTest(){
        assertEquals(9, validNumber.doubleToInt(9.999));
    }

    @Test
    public void doubleToIntErrorTest(){
        assertEquals(0, validNumber.doubleToInt("9.999"));
    }

}