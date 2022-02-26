package bankteller.Test;

import bankteller.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Date test.
 */
class DateTest {

    /**
     * test case 1 ,current date, will always be valid
     */
    @Test
    void isValid() {

        Date date1 = new Date();
        if(date1.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }

    }

    /**
     * test case 2, valid string constructor input
     */
    @Test
    void isValid2(){
        Date date2 = new Date("07/9/2020");
        if(date2.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }

    }

    /**
     * test case 3 messed up String construtor input will not pass
     */
    @Test
    void isValid3(){
        Date date3 = new Date("07/2020");
        if(date3.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }
    }

    /**
     * test case 4 valid leap year
     */
    @Test
    void isValid4() {
        Date date4 = new Date("02/29/2020");
        if(date4.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }
    }

    /**
     * test case 5 invalid leap year
     */
    @Test
    void isValid5() {
        Date date5 = new Date("02/29/2019");
        if(date5.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }
    }

    /**
     * test case 6 Valid format, invalid date 1
     */
    @Test
    void isValid6() {
        Date date6 = new Date("02/209/2019");
        if(date6.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }
    }

    /**
     * test case 7  Valid format, invalid date 2
     */
    @Test
    void isValid7() {
        Date date7 = new Date("-1/2/01");
        if(date7.isValid()) {
            System.out.println("Test case Passed");
        }else{
            System.out.println("Test case Failed");
        }
    }

    /**
     * Day greater than 31 for Apr,Jul,
     */
    @Test
    void isValid8() {

    }


}