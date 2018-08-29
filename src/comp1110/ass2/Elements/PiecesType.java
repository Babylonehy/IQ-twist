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
    static final int[][] a5=Flip(a1);
    static final int[][] a6=Flip(a5);
    static final int[][] a7=Flip(a6);
    static final int[][] a8=Flip(a7);

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
    static final int[][] b5=Flip(b1);
    static final int[][] b6=Flip(b5);
    static final int[][] b7=Flip(b6);
    static final int[][] b8=Flip(b7);

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
    static final int[][] c5=Flip(c1);
    static final int[][] c6=Flip(c5);
    static final int[][] c7=Flip(c6);
    static final int[][] c8=Flip(c7);

    static final int[][] d0 = {
            { 1, 1, 1, 0},
            { 0,-1,-1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] d1=RotateRight(d0);
    static final int[][] d2=RotateRight(d1);
    static final int[][] d3=RotateRight(d2);
    static final int[][] d4=Flip(d1);
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
    static final int[][] e4=Flip(e1);
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
    static final int[][] f4=Flip(f1);
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
    static final int[][] g4=Flip(g1);
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
    static final int[][] h4=Flip(h1);
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


    public  static int[][] getA0(char type,int rotation){
        String key= String.valueOf(type)+String.valueOf(rotation);
        return null;
        //return all.get("key");
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
