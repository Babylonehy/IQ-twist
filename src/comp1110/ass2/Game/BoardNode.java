package comp1110.ass2.Game;

import comp1110.ass2.Elements.BoardStatus;
import comp1110.ass2.Elements.Color;
import comp1110.ass2.Elements.Peg;

import static comp1110.ass2.Elements.BoardStatus.*;


/**
 * Create by Sean 2018-08-21
 *This class represents a node board by double array with 8×4;
 * It should be like the array below:
 * a0  a1  a2  a3  a4  a5  a6  a7
 * b0  b1  b2  b3  b4  b5  b6  b7
 * c0  c1  c2  c3  c4  c5  c6  c7
 * d0  d1  d2  d3  d4  d5  d6  d7
 *
 * This array above should transfer to the position numbers below:
 *  0  1  2  3  4  5  6  7
 *  8  9 10 11 12 13 14 15
 * 16 17 18 19 20 21 22 23
 * 24 25 26 27 28 29 30 31
 *
 * Last modify: 2018-08-23 15:24:06 by Stella
 * All comment by Stella
 * All code by Sean
 */


public class BoardNode {
    private int[] useNode;
    Peg peg;
    BoardStatus Status=null;
    private int position;
    Color color=null;

    /**
     * This two method set up the link between node and Peg, Pieces.
      */


    public BoardNode(Peg peg, int position){
        this.peg=peg;
        this.position=position;
        this.color=peg.getColor();
        this.Status=IamPeg;

    }

    public BoardNode(int position,int status,Color color){
            WriteStatus(status);
            this.color=color;
            this.position=position;
    }

    /**
     * Write status to node.
     * 1 is FULL, -1 is Hole, 0 is null.
     * @param status
     */
    private void WriteStatus(int status){
        switch (status){
            case 1:
                this.Status=Full;
                break;
            case -1:
                this.Status=Hole;
                break;
            case 0:
                this.Status=null;
                break;
            default:
                System.out.println("Wrong status input!");
        }
    }

    public BoardStatus getStatus(){
        return Status;
    }

    public Color getColor() {
        return color;
    }
}
