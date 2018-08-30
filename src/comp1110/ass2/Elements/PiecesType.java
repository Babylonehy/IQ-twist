package comp1110.ass2.Elements;

import comp1110.ass2.Game.Pieces;

import java.util.HashMap;
import java.util.Map;

import static comp1110.ass2.Elements.Color.*;

/**
 * Create by Sean 2018-08-21
 * We use enum type to represent 8 different shapes of pieces, which are a,b,c,d,e,f,g,h;
 * Then create empty int[][] for each from a0 to h0;
 * Last modify: 2018-08-21 10:25:00 by Zhi Wang
  */
//TODO Finished this commit about a0-h0 and create empty int[][] for each from a0-h0 totoal 8.

public class PiecesType {
/**
Doing assignment to prototype of 8 different shapes, which combines a 4*4 matrix
 0 for null;
 1 for covered;
 -1 for empty;
*/
    static Map<String,Integer> map=new HashMap<String, Integer>();


String[] PiecesType = new String[64];
public static Map all=new HashMap();



    static final int[][] a0 = {
        {-1, 1,-1, 0},
        { 0, 0, 1, 0},
        { 0, 0, 0, 0},
        { 0, 0, 0, 0}
    };
    //TODO add commit
    static final int[][] a1=RotateRight(a0);
    static final int[][] a2=RotateRight(a1);
    static final int[][] a3=RotateRight(a2);
    static final int[][] a4=RotateRight(a3);
    static final int[][] a5=Flip(a0);
    static final int[][] a6=RotateRight(a5);
    static final int[][] a7=RotateRight(a6);
    static final int[][] a8=RotateRight(a7);

    static final int[][] b0 = {
            { 1, 1, 0, 0},
            { 0,-1, 1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] b1=RotateRight(b0);
    static final int[][] b2=RotateRight(b1);
    static final int[][] b3=RotateRight(b2);
    static final int[][] b4=RotateRight(b3);
    static final int[][] b5=Flip(b0);
    static final int[][] b6=RotateRight(b5);
    static final int[][] b7=RotateRight(b6);
    static final int[][] b8=RotateRight(b7);

    static final int[][] c0 = {
            { 1,-1, 1, 1},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] c1=RotateRight(c0);
    static final int[][] c2=RotateRight(c1);
    static final int[][] c3=RotateRight(c2);
    static final int[][] c4=RotateRight(c3);
    static final int[][] c5=Flip(c0);
    static final int[][] c6=RotateRight(c5);
    static final int[][] c7=RotateRight(c6);
    static final int[][] c8=RotateRight(c7);

    static final int[][] d0 = {
            { 1, 1, 1, 0},
            { 0,-1,-1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] d1=RotateRight(d0);
    static final int[][] d2=RotateRight(d1);
    static final int[][] d3=RotateRight(d2);
    static final int[][] d4=Flip(d0);
    static final int[][] d5=RotateRight(d4);
    static final int[][] d6=RotateRight(d5);
    static final int[][] d7=RotateRight(d6);

    static final int[][] e0 = {
            { 1, 1,-1, 0},
            { 0,-1, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] e1=RotateRight(e0);
    static final int[][] e2=RotateRight(e1);
    static final int[][] e3=RotateRight(e2);
    static final int[][] e4=Flip(e0);
    static final int[][] e5=RotateRight(e4);
    static final int[][] e6=RotateRight(e5);
    static final int[][] e7=RotateRight(e6);

    static final int[][] f0 = {
            { 1, 1,-1, 0},
            { 0,-1, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] f1=RotateRight(f0);
    static final int[][] f2=RotateRight(f1);
    static final int[][] f3=RotateRight(f2);
    static final int[][] f4=Flip(f0);
    static final int[][] f5=RotateRight(f4);
    static final int[][] f6=RotateRight(f5);
    static final int[][] f7=RotateRight(f6);

    static final int[][] g0 = {
            {-1, 0, 0, 0},
            {-1, 1, 1, 0},
            { 0,-1, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] g1=RotateRight(g0);
    static final int[][] g2=RotateRight(g1);
    static final int[][] g3=RotateRight(g2);
    static final int[][] g4=Flip(g0);
    static final int[][] g5=RotateRight(g4);
    static final int[][] g6=RotateRight(g5);
    static final int[][] g7=RotateRight(g6);


    static final int[][] h0 = {
            {-1, 1, 1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] h1=RotateRight(h0);
    static final int[][] h2=RotateRight(h1);
    static final int[][] h3=RotateRight(h2);
    static final int[][] h4=Flip(h0);
    static final int[][] h5=RotateRight(h4);
    static final int[][] h6=RotateRight(h5);
    static final int[][] h7=RotateRight(h6);


    static final int[][][][] piececType = {
            {a0, a1, a2, a3, a4, a5, a6, a7},
            {b0, b1, b2, b3, b4, b5, b6, b7},
            {c0, c1, c2, c3, c4, c5, c6, c7},
            {d0, d1, d2, d3, d4, d5, d6, d7},
            {e0, e1, e2, e3, e4, e5, e6, e7},
            {f0, f1, f2, f3, f4, f5, f6, f7},
            {g0, g1, g2, g3, g4 ,g5, g6, g7},
            {h0, h1, h2, h3, h4, h5, h6, h7}
    };


//TODO add commit

    /**
     * create a getType method
     * @return type of Type
     */
    static int[][] Flip(int[][] op) {
      int[][] flip = new int[4][4];
       for(int i = 0; i<4; i++){
           flip[0][i] = op[3][i];
           flip[1][i] = op[2][i];
           flip[2][i] = op[1][i];
           flip[3][i] = op[0][i];
       }
        return flip;
    }


    private static int[][] RotateRight(int[][]op){
        int[][] rotate = new int[4][4];
        for(int i = 0; i<4/2; i++){
            int first = i;
            int last = 4 - 1 -i;
            for(int j = first; j< last;j++){
                int off = i - first;
                int top = op[first][i];
                op[first][j] = op[last-off][first];
                op[last-off][first] = op[last][last-off];
                op[last][last-off] = op[j][last];
                op[j][last] = top;
            }
        }



        return rotate;
    }

    public static void setMap( Map map ){
        map.put("a0",a0);
        map.put("a1",a1);
        map.put("a2",a2);
        map.put("a3",a3);
        map.put("a4",a4);
        map.put("a5",a5);
        map.put("a6",a6);
        map.put("a7",a7);
        map.put("b0",b0);
        map.put("b1",b1);
        map.put("b2",b2);
        map.put("b3",b3);
        map.put("b4",b4);
        map.put("b5",b5);
        map.put("b6",b6);
        map.put("b7",b7);
        map.put("c0",c0);
        map.put("c1",c1);
        map.put("c2",c2);
        map.put("c3",c3);
        map.put("c4",c4);
        map.put("c5",c5);
        map.put("c6",c6);
        map.put("c7",c7);
        map.put("d0",d0);
        map.put("d1",d1);
        map.put("d2",d2);
        map.put("d3",d3);
        map.put("d4",d4);
        map.put("d5",d5);
        map.put("d6",d6);
        map.put("d7",d7);
        map.put("e0",e0);
        map.put("e1",e1);
        map.put("e2",e2);
        map.put("e3",e3);
        map.put("e4",e4);
        map.put("e5",e5);
        map.put("e6",e6);
        map.put("e7",e7);
        map.put("f0",f0);
        map.put("f1",f0);
        map.put("f2",f2);
        map.put("f3",f3);
        map.put("f4",f4);
        map.put("f5",f5);
        map.put("f6",f6);
        map.put("f7",f7);
        map.put("g0",g0);
        map.put("g1",g1);
        map.put("g2",g2);
        map.put("g3",g3);
        map.put("g4",g4);
        map.put("g5",g5);
        map.put("g6",g6);
        map.put("g7",g7);
        map.put("h0",h0);
        map.put("h1",h1);
        map.put("h2",h2);
        map.put("h3",h3);
        map.put("h4",h4);
        map.put("h5",h5);
        map.put("h6",h6);
        map.put("h7",h7);
        //TODO 补充完
    }

    public  static Integer getTypeset(char type,int rotation){
        setMap(map);
        String key= String.valueOf(type)+String.valueOf(rotation);
        return map.get("key");
    }

    public String getType(){

        return null;
    }
//TODO add commit


    /**
     *
     * @param id a character type id to identify pieces
     * @return pieces 7*7
     */
    static int[][] makePieces(char id){
        return null;
    }
}
