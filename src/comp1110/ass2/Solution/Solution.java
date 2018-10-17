package comp1110.ass2.Solution;

import comp1110.ass2.Game.Constant;
import comp1110.ass2.Game.Pieces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static comp1110.ass2.Game.Constant.StrictSymmetry;
import static comp1110.ass2.Game.Constant.pieces;
import static comp1110.ass2.TwistGame.isPlacementStringValid;
import static comp1110.ass2.TwistGame.positionToPlaceCode;

/**
 * Create by Sean 2018-08-21
 * Possible solutions for a certain status game
 *
 * Last modify: 2018-08-21 by Sean
 */

public class Solution {

    private static List<String> StrictSymmetry=new ArrayList<String>();
    private static List<String> pieceset = new ArrayList<String>();
    private static Vector<int[]> sparseMatrix=new Vector<>();

    /**
     * A solver for the game(just support Task9&11)
     * Given a start point placement and return a set which contains all the possible solution
     * @param placement A certain status for the game
     * @return A set which contains （all） the possible solutions for the input placement
     */

    public  Set<String> Solutions(String placement){
        return null;  //TODO Solution
    }

    /**
     * Generate all  solutions without peg
     * Store in txt
     *
     */
    public  void AllSolutions(){
         //TODO ALLSolution
    }

    private static void Allpieces() {
        int count = 0;

        for (char piece : pieces) {
            for (int i = 0; i < 8; i++) {
                String pieceID = piece + "" + i;
                //remove StrictSymmetry
                if (!StrictSymmetry.contains(pieceID)) {
                    //position
                    for (int j = 0; j < 32; j++) {
                        String loaction = positionToPlaceCode(j);
                        String placecode = piece + "" + loaction + "" + i;
                        if (isPlacementStringValid(placecode)) {
                            int[] row = new int[32 + 8]; //0&1 encode
                            pieceset.add(placecode);
                            Pieces p = new Pieces(pieceID);
                            Map<Integer, Integer> position = p.DecodetoBoardposition(j);

                            for (Integer each : position.keySet()) {
                                //only set 1 and -1
                                if (position.get(each)!=0){
                                    row[each] = 1;
                                }
                            }
                            row[32 + (piece - 'a')] = 1;
                            sparseMatrix.add(row);
                            count++;
                        }
                    }
                }
            }

        }
//        PrintMatrix(sparseMatrix);

    }

    public static void PrintMatrix(Vector<int[]> sparseMatrix){

        for (int i = 0; i <sparseMatrix.size() ; i++) {
            for (int j = 0; j < sparseMatrix.get(i).length; j++) {
                System.out.print(sparseMatrix.get(i)[j]+"  ");
            }
            System.out.println();
        }
        System.out.println("Size:["+sparseMatrix.size()+" X "+sparseMatrix.get(0).length+"]");
    }

    private static void WriteToTxt(Vector<int[]> Matrix,String filename) throws IOException {
        try {
            File writename = new File("./src/comp1110/ass2/Solution/Output/"+filename);
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            for (int i = 0; i <sparseMatrix.size() ; i++) {
                out.write(pieceset.get(i)+"  ");
                for (int j = 0; j < sparseMatrix.get(i).length; j++) {
                    out.write(sparseMatrix.get(i)[j]+"  ");
                }
                out.write("\n");
            }
            out.flush();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static Vector<int[]>  getsparseMatrix(){
        for (String each: Constant.StrictSymmetry) {
            StrictSymmetry.add(each);
        }
        Allpieces();
        return sparseMatrix;
    }
    public static void main(String[] args) throws IOException {
        getsparseMatrix();
       WriteToTxt(sparseMatrix,"Initial.txt");
    }

}
