import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    public void checkWhether2016YearIsLeapYear() {
        //given
        Validation object = new Validation();
        int inputYear = 2016;


        //when
        boolean booleanResult = object.leapYear(inputYear);

        //then
        Assertions.assertTrue(booleanResult);

    }

    @Test
    public void checkWhether2022YearIsLeapYear() {
        //given
        Validation object = new Validation();
        int inputYear = 2022;


        //when
        boolean booleanResult = object.leapYear(inputYear);

        //then
        Assertions.assertFalse(booleanResult);

    }
}