package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static comp1110.ass2.TestUtility.GOOD_PLACEMENTS;
import static comp1110.ass2.TwistGame.ReorderPieces;
import static org.junit.Assert.assertTrue;

/**
 * Create by Sean 2018-09-22
 * Last modify: 2018-09-22 00:04:40 by Sean
 */
public class ReorderPiecesTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    private void test(String input_placement,String expected){
        String out=ReorderPieces(input_placement);
        assertTrue("Input was '"+input_placement+"', expected "+expected+" but got "+out, expected.equals(out));
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName()+ " '"+input_placement+ "'  Pass");
    }

    private String exchange(String[] order ){
        for(int index=order.length-1; index>=0; index--) {
            Random rand=new Random();
            int x=rand.nextInt(index+1);
            String temp = order[index];
            order[index] = order[x];
            order[x] = temp;
        }
        String re="";
        for (String each :order){
            re+=each;
        }
        return re;
    }


    @Test
    public void testNonePieces() {
        test("","");

        for (String each:GOOD_PLACEMENTS) {
            if (each.length()>32){
                test(each.substring(32,each.length()),"");
            }
        }

    }

    @Test
    public void testIncludePeg() {
        for (String each:GOOD_PLACEMENTS) {
            test(each,each.substring(0,32));
        }

    }

    @Test
    public void testSinglePieces() {
        for (String each:GOOD_PLACEMENTS) {
            test(each.substring(0,4),each.substring(0,4));
        }
    }

    @Test
    public void testOrderPieces() {
        for (String each:GOOD_PLACEMENTS) {
            test(each.substring(0,32),each.substring(0,32));
        }
    }

    @Test
    public void testRandomOrderPieces() {
        String[] order=new String[8];
        for (String each:GOOD_PLACEMENTS) {
            for (int i = 0; i <8; i++) {
                order[i]=each.substring(4*i,4*i+4);
            }
            String in=exchange(order);
            test(in,each.substring(0,32));
        }
    }


}
