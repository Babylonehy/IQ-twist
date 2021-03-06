package comp1110.ass2;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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


        public void test(String start, Set<String> expected) {
            Set<String> outSet = TwistGame.getNotUseNode(start);
            assertTrue("Input was '" + start + "', expected " + expected + " but got " + outSet, expected.equals(outSet));


            //@Test
            //public void singleNodeTest () {
            //try {
            //    test()
            // } catch (InputMismatchException var2) {
            //     Assert.assertTrue("Unexpected output", false);
            // } catch (NoSuchElementException var3) {
            //    Assert.assertTrue("No output", false);
            // }
            //  }
        }

        @Test
        public void doubleNodeTest () {

        }
    }
}

