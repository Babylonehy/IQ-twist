package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;
import java.util.Set;

import static comp1110.ass2.TestUtility.GOOD_PLACEMENTS;
import static comp1110.ass2.TestUtility.NO_VIABLE;
import static comp1110.ass2.TestUtility.SINGLE;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetNotUseNodeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    public class getNotUseNodeTest {

        private void test(String start, String expected) {
            Set<String> outSet = TwistGame.getNotUseNode(start);
            assertTrue("Input was '" + start + "', expected " + expected + " but got " + outSet, expected.equals(outSet));
        }
        //private String exchange(String[] order ){
        //for(int index=order.length-1; index>=0; index--) {
        //  Random rand=new Random();
        //int x=rand.nextInt(index+1);
        //String temp = order[index];
        //order[index] = order[x];
        //order[x] = temp;
        // }
        //String re="";
        //for (String each :order){
        //  re+=each;
        //}
        //return re;
        //}


        //public void nullNodeTest() {
            //for (String each:NO_VIABLE) {
            // if (each.length()>32){
            // test(each.substring(32,each.length()),"");
            // }}
        }
            @Test
            public void singleNodeTest () {

            }

            public void doubleNodeTest () {

            }
        }
