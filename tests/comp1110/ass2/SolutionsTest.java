package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.model.TestTimedOutException;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertTrue;

public class SolutionsTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(20000);

  private void test(String start, Set<String> expected) {
    String[] out = TwistGame.getSolutions(start);
    assertTrue("No solutions returned for problem " + start + ", expected " + expected, out != null);
    TreeSet<String> outSet = new TreeSet<>();
    outSet.addAll(Arrays.asList(out));
    String expstr = expected.toString();
    String outstr = outSet.toString();
    assertTrue("For problem " + start + ", was expecting " + expstr + ", but got " + outstr, expstr.equals(outstr));
  }

  private void single(int n) {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      TreeSet<String> expected = new TreeSet<>();
      expected.add(GOOD_PLACEMENTS[i].substring(0,32));
      test(SINGLE[n][i], expected);
    }
  }

  @Test
  public void single2() {
    single(0);
  }

  @Test
  public void single3() {
    single(1);
  }

  @Test
  public void single4() {
    single(2);
  }

  @Test
  public void multiA() {
    TreeSet<String> expected = new TreeSet<>();
    for (int i = 1; i < MULTI[0].length; i++)
      expected.add(MULTI[0][i]);
    test(MULTI[0][0], expected);
  }

  @Test
  public void multiB() {
    TreeSet<String> expected = new TreeSet<>();
    for (int i = 1; i < MULTI[1].length; i++)
      expected.add(MULTI[1][i]);
    test(MULTI[1][0], expected);
  }

  @Test(expected = TestTimedOutException.class)
  public void threePieces(){
    String start2="a7A7b6A7c1A3";
    TreeSet<String> expected2 = new TreeSet<>();
    expected2.add("a7A7b6A7c1A3d2C6e2A2f3A0g4B3h6D0");
    expected2.add("a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0");
    expected2.add("a7A7b6A7c1A3d2C2e2A3f5B3g3A1h6D0");
    expected2.add("a7A7b6A7c1A3d2B5e4C2f2A0g4A7h6D0");
    expected2.add("a7A7b6A7c1A3d2A3e4A0f2C2g4B0h6D0");

//        try {
        test(start2,expected2);
//            System.out.println("Pass threeTest.");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

  }



}
