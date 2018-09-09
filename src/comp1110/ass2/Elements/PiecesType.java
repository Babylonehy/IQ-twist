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


    public  static Integer getTypeset(char type,int rotation){
        return null;
    }

    /**
     *
     * @param id a character type id to identify pieces
     * @return pieces 7*7
     */
    static int[][] makePieces(char id){
        return null;
    }
}
