package comp1110.ass2.Game;


import comp1110.ass2.Elements.Color;
import comp1110.ass2.Elements.Direction;
import comp1110.ass2.Elements.PiecesType;

/**
 * Create by Sean 2018-08-21
 *This class will produce all the 64 pieces used.
 * All the change based on PiecesType.
 *
 * Last modify: 2018-08-21 16:15:41 by Sean
 */

public class Pieces {

    private  String piecesId; //length 4 chars

    /**
     * This method rotate piece by 90,180,270,360 degree in right direction
     * @param dir Rotation Direction
     * @param pieces From PiecesType, original pieces, 7x7
     * @return A set new rotated Pieces.
     */

    private static int[][] rotateRight(Direction dir,int[][] pieces ){
        return null;
    }

    /**
     * This method flip piece to another side.
     * @param pieces From PiecesType, Must after Rotation  pieces, 7x7
     * @return A set new rotated and fliped Pieces.
     */
    private static int[][] flipPieces(int [][] pieces){
        return null;
    }


    //TODO add description about this method
    private static int[][] DecodePieces(String piecesId){
        return null;
    }

}
