package comp1110.ass2;

import org.junit.Rule;
import org.junit.rules.Timeout;

import static comp1110.ass2.TwistGame.decodeTotype_position;
import static org.junit.Assert.assertTrue;

public class PositionAndPlaceCodeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    private void test(String input_placement,String expected){
        char[][] out=decodeTotype_position(input_placement);
        assertTrue("Input was '"+input_placement+"', expected "+expected+" but got "+out, expected.equals(out));
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName()+ " '"+input_placement+ "'  Pass");
    }

    public void offBoardTest(){
        test();
    }

    public void onBoardTest(){


    }

    public void

}
