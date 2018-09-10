package comp1110.ass2.Game;


import comp1110.ass2.Elements.Color;
import comp1110.ass2.Elements.Direction;

import java.util.Arrays;

import static comp1110.ass2.Elements.Color.*;
import static comp1110.ass2.Elements.PiecesType.getTypeset;


/**
 * Create by Sean 2018-08-21
 *This class will produce all the 64 pieces used.
 * All the change based on PiecesType.
 *
 * Last modify: 2018-08-22 02:54:19 by Stella
 */

public class Pieces {

    private  String piecesId; //length 2 chars
    private  int[][] pieces_matrix;
    private  char type;
    private  int rotate;
    Color color;


    public Pieces(String piecesId){
        this.piecesId=piecesId;
        char [] Id=piecesId.toCharArray();
        this.type=Id[0];
        this.rotate=Id[1]-'0';
        if (type>='a'&&type<='h'&&rotate<=7){
            this.pieces_matrix=getTypeset(type,rotate);
            WriteColor(type);
            System.out.println("Create "+piecesId+" successful.");
        }
        else {
            System.out.println("Wrong PiecesId!");
        }

    }

    /**
     * Write Color for pieces
     *
     */
    private void WriteColor(char Type){
        switch (type){
            case 'a':
               this.color=Red;
               break;
            case 'b':
               this.color=Red;
                break;
            case 'c':
                this.color=Blue;
                break;
            case 'd':
                this.color=Blue;
                break;
            case 'e':
                this.color=Green;
                break;
            case 'f':
                this.color=Green;
                break;
            case 'g':
                this.color=Yellow;
                break;
            case 'h':
                this.color=Yellow;
                break;
            default:
                System.out.println("Bad color write");

        }
    }
    /**
     * The method decodes pieces to 4*4.
     * @return A set new rotated and fliped Pieces.
     */
    private static int[][] DecodetoBoard(int position){

        return null;
    }


    /**
     * Get the left_top_position in 4*4
     * 0,1,2,3
     * 4,5,6,7
     * 8,9,10,11
     * @param m 4*4 after decode
     * @return position 0-11
     */
    public static int left_top_position(int[][] m){

        return 1;
    }

    public static void main(String[] args) {
        Pieces x=new Pieces("a6");
    }

}
