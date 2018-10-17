package comp1110.ass2.Solution;

import comp1110.ass2.Game.Constant;
import comp1110.ass2.Game.Pieces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static comp1110.ass2.Game.Constant.StrictSymmetry;
import static comp1110.ass2.Game.Constant.pieces;
import static comp1110.ass2.TwistGame.*;

/**
 * Create by Sean 2018-08-21
 * Possible solutions for a certain status game
 *
 * Last modify: 2018-10-18 by Sean
 * All code by Sean
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

    private static void Allpieces(char[] piecesused) {
        int count = 0;

        for (char piece : piecesused) {
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
    private static void WriteAnsTotxt(HashSet<String> ans,String filename) throws IOException {
        try {
            File writename = new File("./src/comp1110/ass2/Solution/Output/"+filename);
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            for (String each : ans) {
                out.write(each);
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
        Allpieces(pieces);
        return sparseMatrix;
    }

    /**
     * placement should not contain any peg
     * @param placement
     * @return
     */
    private static Vector<int[]> transformTosparseMatrix(String placement){
        sparseMatrix.clear();
        pieceset.clear();
        int count=0;
        int lenght=placement.length()/4;
        HashSet<Character> notusedPieces=new HashSet<>();
        for (char piece : pieces){
            //piece has used
            if (placement.indexOf(piece)==-1){

                for (int i = 0; i < 8; i++) {
                    String pieceID = piece + "" + i;
                    //remove StrictSymmetry
                    if (!StrictSymmetry.contains(pieceID)) {
                        //position
                        for (int j = 0; j < 32; j++) {
                            String loaction = positionToPlaceCode(j);
                            String placecode = piece + "" + loaction + "" + i;
                            if (isPlacementStringValid(placecode)) {
                                int[] row = new int[lenght+32 + 8]; //0&1 encode
                                pieceset.add(placecode);
                                Pieces p = new Pieces(pieceID);
                                Map<Integer, Integer> position = p.DecodetoBoardposition(j);

                                for (Integer each : position.keySet()) {
                                    //only set 1 and -1
                                    if (position.get(each)!=0){
                                        row[lenght+each] = 1;
                                    }
                                }
                                row[lenght+32 + (piece - 'a')] = 1;
                                sparseMatrix.add(row);
                            }
                        }
                    }
                }
            }
            else {
                int index=placement.indexOf(piece);
                String pieceID=(placement.substring(index,index+1))+""+(placement.substring(index+3,index+4));
                String placecode=(placement.substring(index,index+4));
                int board=charPairToPosition(Integer.valueOf(placement.charAt(index+1)-'1'),placement.charAt(index+2));
                //System.out.println(pieceID+" "+board+" "+Integer.valueOf(placement.charAt(index+1)-'1')+" "+placement.charAt(index+2));
                int[] row = new int[lenght+32 + 8]; //0&1 encode
                pieceset.add(placecode);
                Pieces p = new Pieces(pieceID);
                Map<Integer, Integer> position = p.DecodetoBoardposition(board);

                for (Integer each : position.keySet()) {
                    //only set 1 and -1
                    if (position.get(each)!=0){
                        row[lenght+each] = 1;
                    }
                }
                row[lenght+32 + (piece - 'a')] = 1;
                row[count]=1;
                count++;
                sparseMatrix.add(row);
            }

        }

        //PrintMatrix(sparseMatrix);
        return sparseMatrix;
    }

    public static String[] getsolution(String placement){
        sparseMatrix=transformTosparseMatrix(clearpeg(placement));
        String peg=placement.replaceAll(clearpeg(placement),"");
        System.out.println(placement);
        Dancinglinks dlx=new Dancinglinks();
        dlx.createDancingLinks(getsparseMatrix());
        dlx.getsolution(100);
        List<ArrayList<Integer>> answer=dlx.getAns();
        HashSet<String> solution=new HashSet<>();
        for (List each: answer){
            String solution_placement="";
            for (int i = 0; i <each.size() ; i++) {
                solution_placement+=pieceset.get((Integer) each.get(i));
            }
            solution_placement=Reorder(solution_placement)+peg;
            if (isPlacementStringValid(solution_placement)){
                solution.add(clearpeg(solution_placement));
            }

        }
        System.out.println(placement+"/"+solution.size()+solution.toString());
        String[] rtn= new String[solution.size()];
        solution.toArray(rtn);
        return rtn;
    }
    public static String clearpeg(String placement){
        Pattern p = Pattern.compile("[a-h][1-8][A-D][0-7]");
        Matcher m = p.matcher(placement);
        String rtn="";
        while (m.find()) {
            rtn+=(m.group());
        }
        return rtn;
    }

    public static void main(String[] args) throws IOException {
        //a7A7b1A0c3A0d1B5e3C2f6C2g4B0h5B0
        //a7A7b1A0c3A0d1B5e3C2f6C2g4B0h5B0

        long start=System.currentTimeMillis();
        Dancinglinks dlx=new Dancinglinks();
        dlx.createDancingLinks(getsparseMatrix());
        dlx.getsolution(1000000000);
        List<ArrayList<Integer>> answer=dlx.getAns();
        HashSet<String> solution=new HashSet<>();
        for (List each: answer){
            String placement="";
            for (int i = 0; i <each.size() ; i++) {
                placement+=pieceset.get((Integer) each.get(i));
            }
            solution.add(Reorder(placement));
        }

        WriteAnsTotxt(solution,"Solution.txt");
        System.out.println(solution.size());
        System.out.println((System.currentTimeMillis()-start)/1000.0);

       // getsolution("g2A3h8B3");
    }


}
