package comp1110.ass2.Game;


import comp1110.ass2.Elements.Color;
import comp1110.ass2.Elements.Direction;
import comp1110.ass2.Elements.PiecesType;

/**
 * Create by Sean 2018-08-21
 *This class will produce all the 64 pieces used.
 * All the change based on PiecesType.
 *
 * Last modify: 2018-08-22 02:54:19 by Stella
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
     * The method mirror-flips pieces vertically by 180°.
     * @param pieces From PieceType, Must before Rotation pieces, 7×7;
     * @returnA set new rotated and fliped Pieces.
     */
    private static int[][] flipPieces(int [][] pieces){
        return null;
    }
    /**
     * The method decodes pieces into encode.
     * @param piecesId From PieceType; Offer to BoardNode.
     * @returnA set new rotated and fliped Pieces.
     */



    //TODO add description about this method
    private static int[][] DecodePieces(String piecesId){
        return null;
    }

}
