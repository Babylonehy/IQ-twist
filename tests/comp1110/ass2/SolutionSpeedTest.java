package comp1110.ass2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;

public class SolutionSpeedTest {

    private void test(String start, Set<String> expected) {
        String[] out = TwistGame.getSolutions(start);
        assertTrue("No solutions returned for problem " + start + ", expected " + expected, out != null);
        TreeSet<String> outSet = new TreeSet<>();
        outSet.addAll(Arrays.asList(out));
        String expstr = expected.toString();
        String outstr = outSet.toString();
        assertTrue("For problem " + start + ", was expecting " + expstr + ", but got " + outstr, expstr.equals(outstr));
    }

    @Test(timeout = 100L)
    public void threePieces(){
        String start2="a7A7b6A7c1A3";
        TreeSet<String> expected2 = new TreeSet<>();
        expected2.add("a7A7b6A7c1A3d2C6e2A2f3A0g4B3h6D0");
        expected2.add("a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0");
        expected2.add("a7A7b6A7c1A3d2C2e2A3f5B3g3A1h6D0");
        expected2.add("a7A7b6A7c1A3d2B5e4C2f2A0g4A7h6D0");
        expected2.add("a7A7b6A7c1A3d2A3e4A0f2C2g4B0h6D0");

        try {
            test(start2,expected2);
            System.out.println("Pass threeTest.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
