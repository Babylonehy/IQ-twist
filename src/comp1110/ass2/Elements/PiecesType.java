package comp1110.ass2.Elements;

import comp1110.ass2.Game.Pieces;

import static comp1110.ass2.Elements.Color.*;

/**
  * Create by Sean 2018-08-21
  *
  *
  * Last modify: 2018-08-21 10:25:00 by Zhi Wang
 * We use enum type to represent 8 different shapes of pieces, which are a,b,c,d,e,f,g,h;
 * Then create empty int[][] for each from a0 to h0;
  */
//TODO Finished this commit about a0-h0 and create empty int[][] for each from a0-h0 totoal 8.

public enum PiecesType {
    a,b,c,d,e,f,g,h;
/**
Doing assignment to prototype of 8 different shapes, which combines a 4*4 matrix
*/

    //TODO add commit (对每一个块类型的原型，赋值，这是一个4*4的矩阵)
    static final int[][] a0={};
    static final int[][] b0={};
    static final int[][] c0={};
    static final int[][] d0={};
    static final int[][] e0={};
    static final int[][] f0={};
    static final int[][] g0={};
    static final int[][] h0={};


//TODO add commit (取得Type的类型)

    /**
     * create a getType method
     * @return type of Type
     */
    public String getType(){

        return PiecesType.this.name();
    }
//TODO add commit (返回一个a0-h0，即生成了一个初始块， id：a-h)

    /**
     *
     * @param id a character type id to identify pieces
     * @return null
     */
    static int[][] makePieces(char id){
        return null;
    }
}
