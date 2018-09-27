package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static comp1110.ass2.TwistGame.positionToPlaceCode;
import static org.junit.Assert.assertTrue;

public class PositionAndPlaceCodeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    private void test(int input_placement,String expected){
         String out=positionToPlaceCode(input_placement);
        assertTrue("Input was '"+input_placement+"', expected "+expected+" but got "+out, expected.equals(out));
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName()+ " '"+input_placement+ "'  Pass");
    }

    @Test
    public void Test00(){
        test(0,"1A");
    }

    @Test
    public void Test15(){
        test(15,"8B");
    }
    @Test
    public void Test31(){
        test(31,"8D");

    }


}
