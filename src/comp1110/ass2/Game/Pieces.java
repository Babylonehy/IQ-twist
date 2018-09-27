package comp1110.ass2.Game;


import comp1110.ass2.Elements.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static comp1110.ass2.Elements.Color.*;
import static comp1110.ass2.Elements.PiecesType.getTypeset;


/**
 * Create by Sean 2018-08-21
 *This class will produce all the 64 pieces used.
 * All the change based on PiecesType.
 *
 * Last modify: 2018-09-13 00:42:53 by Sean
 */

public class Pieces {

    private  String piecesId; //length 2 chars
    private  int[][] pieces_matrix;
    private  char type;
    private  int rotate;
    Color color;
    private int Width;
    private int Height;
    Vector<Vector> piecesVec=new Vector<Vector>();

    public Pieces(String piecesId){
        this.piecesId=piecesId;
        char [] Id=piecesId.toCharArray();
        this.type=Id[0];
        this.rotate=Id[1]-'0';
        if (type>='a'&&type<='h'&&rotate<=7){
            this.pieces_matrix=getTypeset(type,rotate);
            WriteColor(type);
       //     System.out.println("Create "+piecesId+" successful.");
            compress(pieces_matrix);
        }
        else {
            System.out.println(piecesId+" Wrong PiecesId!");
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
     * Give a position on board, Return a map of [position: status]
     * @param position left_and_top_position.
     * @return A map of position and status(int)
     */
    public Map DecodetoBoardposition(int position){
        Map<Integer,Integer> BoardPosition=new HashMap<>();
        for (int i = 0; i < this.getHeight(); i++) {
            int index=position+i*8;
            for (int j = 0; j <this.getWidth() ; j++) {
                BoardPosition.put(index,(int)piecesVec.elementAt(i).get(j));
                index=index+1;
            }
        }
        return BoardPosition;
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

    /**
     * Compress 4*4 to a appropriate Vector just for fast put_on_board.
     * And set a certain pieces's height & wight.
     * @param m
     * @return
     */

    public  Vector<Vector> compress(int[][] m){
        Vector s1=new Vector();

        int startCol=-1;
        int endCol=-1;
        int startRow=-1;
        int endRow=-1;

        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[j][i]!=0 && startCol<0){
                    startCol=i;
                    break;
                }
                else if (m[j][i]!=0 && startCol>=0){
                    endCol=i;
                    break;
                }
            }
            if (endCol==-1){
                endCol=startCol;
            }
        }

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j]!=0&&startRow<0){
                    startRow=i;
                    break;
                }
                else if (m[i][j]!=0&&startRow>=0){
                    endRow=i;
                    break;
                }
            }
            if (endRow==-1){
                endRow=startRow;
            }
        }

        this.Height=endRow-startRow+1;
        this.Width=endCol-startCol+1;
      //  System.out.println(piecesId+" Height:"+Height+" Width:"+Width);

        for (int i = startRow; i <=endRow; i++) {
            for (int j = startCol; j <=endCol ; j++) {
                s1.add(m[i][j]);
            }
            piecesVec.add((Vector) s1.clone());
            s1.clear();
        }
      //  System.out.println(piecesId+" compressed successful.");

        return piecesVec;
    }


    public int[][] getPieces_matrix() {
        return pieces_matrix;
    }

    public String getPiecesId() {
        return piecesId;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }

    public char getType() {
        return type;
    }

    public int getRotate() {
        return rotate;
    }

    public Vector<Vector> getpiecesVec() {
        return piecesVec;
    }

    public int getFirstRowEmpty(){
        int count=0;
        for (int i = 0; i <piecesVec.firstElement().size() ; i++) {
            if ((Integer) piecesVec.firstElement().get(i)==0){
             count++;
            }
            else {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Pieces a=new Pieces("c3");
        System.out.println(a.getFirstRowEmpty());
    }
}
