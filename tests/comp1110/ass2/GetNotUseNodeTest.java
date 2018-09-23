package comp1110.ass2;

import org.junit.Rule;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertTrue;

public class GetNotUseNodeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    private void test(String in, boolean expected) {
        boolean out = TwistGame.isPlacementWellFormed(in);
        assertTrue("Input was '"+in+"', expected "+expected+" but got "+out, out == expected);
    }

}
