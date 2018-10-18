package comp1110.ass2.Game;

/**
 * Create by Sean 2018-09-11
 * Color include all the possible color used in Pegs and Pieces.
 * <p>
 * Last modify: 2018-09-11 23:40:10 by Sean
 */
import java.util.HashMap;
import java.util.Map;

public class Constant {

   public static char[] pegs={'i','j','j','k','k','l','l'};
   public static char[] pieces={'a','b','c','d','e','f','g','h'};
   public static String[] StrictSymmetry={"c4","c5","c6","c7","h4","h5","h6","h7"};
   public static Map<String,String> WeakSymmetry=new HashMap<String, String>()
    {
        {
            put("b0","b2");
            put("b1","b3");
            put("b4","b6");
            put("b5","b7");

            put("e0","e7");
            put("e1","e4");
            put("e2","e5");
            put("e3","e6");

            put("f0","f6");
            put("f1","f7");
            put("f2","f4");
            put("f3","f5");

            put("c0","c2");
            put("c1","c3");
            put("h0","h2");
            put("h1","h2");
        }
    };

}
