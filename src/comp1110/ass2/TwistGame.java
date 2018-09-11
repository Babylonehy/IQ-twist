package comp1110.ass2;

import comp1110.ass2.Elements.Color;
import comp1110.ass2.Elements.Peg;
import comp1110.ass2.Game.BoardNode;
import comp1110.ass2.Game.Pieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static comp1110.ass2.Elements.BoardStatus.*;

/**
 * This class provides the text interface for the Twist Game
 * <p>
 * The game is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 */

public class TwistGame {
    private static final int nodecount=32;
    static BoardNode [] node;
    static Vector<Pieces> piecesSet=new Vector<>();

    static char[] pieces={'a','b','c','d','e','f','g','h'};

    /**
     * Determine whether a piece or peg placement is well-formed according to the following:
     * - it consists of exactly four characters
     * - the first character is in the range a .. l (pieces and pegs)
     * - the second character is in the range 1 .. 8 (columns)
     * - the third character is in the range A .. D (rows)
     * - the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a peg)
     *
     * @param piecePlacement A string describing a single piece or peg placement
     * @return True if the placement is well-formed
     */
    // FIXME Task 2: determine whether a piece or peg placement is well-formed
    public static boolean isPlacementWellFormed(String piecePlacement) {

   /* int l = piecePlacement.length();
    int a = 0;
    if(l%4 !=0){
      return false;
    }else{
      a = l/4;
    }
    if(a==0){
      return false;
    }
    if(piecePlacement ==null){
      return false;
    }
    int[] char1,char2,char3,char4;
    char1 = new int[8];
    char2 = new int[8];
    char3 = new int[8];
    char4 = new int[8];

    for(int i = 0;i<a;i++){
      char1[i]= piecePlacement.charAt(4*i);
      char2[i]= piecePlacement.charAt(4*i+1);
      char3[i]= piecePlacement.charAt(4*i+2);
      char4[i]= piecePlacement.charAt(4*i+3);
    }
    for(int i = 0;i<a;i++){
      if(char1[i]<'a'|| char1[i]>'l'){
        return false;
      }
      if(char2[i] <'1' || char2[i]>'8' ){
        return false;
      }
      if(char3[i]<'A'|| char3[i]>'D'){
        return false;
      }
      if(char4[i]<'0'||char4[i] >'8'){
        return false;
      }
    }

    */

        Pattern p=Pattern.compile("[a-h][1-8][A-D][0-7]$|[i-l][1-8][A-D]0$");
        Matcher m=p.matcher(piecePlacement);
        while (m.find()){
            return true;
        }
        return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 15);
     * - each piece or peg placement is well-formed
     * - each piece or peg placement occurs in the correct alphabetical order (duplicate pegs can be in either order)
     * - no piece or red peg appears mor
     * e than once in the placement
     * - no green, blue or yellow peg appears more than twice in the placement
     *
     * @param placement A string describing a placement of one or more pieces and pegs
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementStringWellFormed(String placement) {

        Vector position = new Vector();
        String temp = "";
        String single = "";
        int count=1;

        char before = (char) 30;

        if (placement.length() % 4 != 0 || placement.length() == 0) {
            return false;
        }
        else {
            char[] placementChar = placement.toCharArray();
            for (int i = 0; i < placementChar.length; i = i + 4) {
                single = String.valueOf(placementChar[i]) + String.valueOf(placementChar[i + 1]) +
                        String.valueOf(placementChar[i + 2]) + String.valueOf(placementChar[i + 3]);
                if (isPlacementWellFormed(single)) {

                    if (before <= placementChar[i]) {
                        if (before==placementChar[i]){
                            count++;
                            before = placementChar[i];
                            if (count>=3){
                                return false;
                            }
                        }
                        else {
                            before = placementChar[i];
                            count=1;
                        }

                    } else {
                        return false;
                    }

                    temp = String.valueOf(placementChar[i]) + String.valueOf(placementChar[i + 1]) + String.valueOf(placementChar[i + 2]);

                    if (position.contains(temp)) {
                        return false;
                    } else {
                        position.add(temp);
                    }
                } else {
                    return false;
                }

            }
            return true;
        }

        // FIXME Task 3: determine whether a placement is well-formed
    }

    /**
     *
     * @param placement
     * @return
     */
    public static char [][] decodeTotype_position(String placement){

       // assert isPlacementStringWellFormed(placement);

        char[] placementChar = placement.toCharArray();
        int paircount=4;
        char [][] decode=new char[placement.length()/paircount][paircount];
        for (int i = 0; i < placementChar.length; i = i + paircount){

            for (int j = 0; j < paircount; j++) {
                decode[i/4][j]=placementChar[i+j];
            }
        }

        return decode;
    }
    public static boolean isPeg(char peg){
        if (peg>='i'&& peg <='l') {
            return true;
        }
        return false;
    }

    /**
     * Determine whether a placement string is valid.  To be valid, the placement
     * string must be well-formed and each piece placement must be a valid placement
     * according to the rules of the game.
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     * - pieces may only overlap pegs when the a) peg is of the same color and b) the
     * point of overlap in the piece is a hole.
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */
    public static boolean isPlacementStringValid(String placement) {


        System.out.println('\n'+"--"+placement+"--"+'\n');
        char [][] decode=decodeTotype_position(placement);

        //Fixme this satement should be deleted......

        node=new BoardNode[nodecount];
        piecesSet.clear();

        for (int i = 0; i <decode.length ; i++){
            char type=decode[i][0];
            int x=decode[i][1]-'1';
            char y=decode[i][2];

            int rotation= decode[i][3]-'0';
            int position=charPairToPosition(x,y);

            if (isPeg(type)){

                if (node[position]==null){
                    node[position]=new BoardNode(new Peg(type),position);
                }

                if (node[position].getStatus()==Full){
                    return false;
                }
                else {
                    BoardNode temp=new BoardNode(new Peg(type),position);
                    if (temp.getColor()!=node[position].getColor()){
                        return false;
                    }
                }
                System.out.println(type+" put successfully");
                System.out.println();
            }
            else {
                String piecesId=type+""+rotation+"";
                piecesSet.add(new Pieces(piecesId));

                if (PutonBoard(position,piecesSet.lastElement().getWidth(),piecesSet.lastElement().getHeight())){

                    if (updateBoard(piecesSet.lastElement().DecodetoBoardposition(position),piecesSet.lastElement().getColor())==false){
                        return false;
                    }
                    System.out.println(piecesSet.lastElement().getPiecesId()+" Good put.");
                }
                else {
                    System.out.println(piecesSet.lastElement().getPiecesId()+" Bad put!");
                    return false;
                }
            }


        }

        return true;
        // FIXME Task 5: determine whether a placement string is valid

    }

    /**
     *
     * @param x
     * @param y
     * @return Board position
     */
    public static int charPairToPosition(int x,char y){
        assert onBoard(x,y);
        System.out.println((x+1)+""+y+" position:"+((y-'A')*8+x));
        return (y-'A')*8+x;
    }

    /**
     *
     * @param position
     * @return
     */
    private static String positionToPlaceCode(int position){
        int x=position%8+1;
        char y=(char) (Integer.valueOf('A')+position/8);
        return x+""+y+"";
    }

    /**
     *
     * @param x
     * @param y
     * @return onBoard or not
     */

    public static boolean onBoard(int x,char y){
        if (y>='A'&&y<='D'&&x>=0&&x<=7){
            return true;
        }
        System.out.println(x+" "+y+" Off Board.");
        return false;
    }

    public static boolean PutonBoard(int left_top_postion,int x, int y){

        x=left_top_postion%8+x-1;
        y=(int)left_top_postion/8+y-1;

        if (x>7){
            System.out.println(left_top_postion+" "+x+" x Off Board.");
            return false;
        }
        if (y>3){
            System.out.println(left_top_postion+" "+y+" y Off Board.");
            return false;
        }

        return true;
    }

    private static boolean updateBoard(Map<Integer,Integer> m, Color color){

        for (int key:m.keySet()) {
            int status = m.get(key);
            if (status==0){
                continue;
            }
            if (node[key] == null) {
                node[key] = new BoardNode(key, status, color);
                System.out.println(key + " update successfully.");
            }
            else {
                if (node[key].getStatus() != null && node[key].getStatus()!=IamPeg ) {
                    System.out.println(key + " has been occupied.");
                    return false;
                }
                else {
                    if (node[key].getColor() != null && node[key].getColor() != color) {
                        System.out.println(key + " color wrong.");
                        return false;
                    }
                    else if (node[key].getStatus() == IamPeg && status == 1) {
                        System.out.println(key + " not hole but put on peg.");
                        return false;
                    }
                    else {
                        node[key] = new BoardNode(key, status, color);
                        System.out.println(key + " update successfully.");
                    }
                }

            }
        }
        return true;
    }

    //TODO delete
    public static void main(String[] args) {
       // getViablePiecePlacements("c1A3d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0");
       /*
        [a6A0, a6A2, a6A4, a6A7, a6B0, a7A1, a7A3, a7A5, a7A7, b6A0, b6A7, b7A1, b7A5]
        [a6A0, a6A2, a6A4, a6A7, a6B0, a7A1, a7A3, a7A5, a7A7, b6A0, b6A2, b6A7, b7A1, b7A3, b7A5, b7A7]
       */
        getViablePiecePlacements("a6B0b6C0d1B3e4A5f4C2g2B3h1A2i7D0j7A0k5B0k5C0l3A0l3D0"); //[c5A2]

        System.out.println();
    }

    /**
     * Nodes can be used
     * @return a set [] include nodes can be used
     */
    public static  Set<String> getNotUseNode() {
        Set<String> use=new HashSet<>();
        for (int i = 0; i < node.length; i++) {
            if (node[i]==null || node[i].getStatus()==IamPeg){
                System.out.println(positionToPlaceCode(i));
                use.add(positionToPlaceCode(i));
            }
        }

        return use;
    }


    /**
     * Pieces can be used
     * @return a char [] include pieces can be used
     */
    public static Character[] getNotUsePieces() {
        Vector<Character> use=new Vector<>();
        for (char each:pieces) {
            boolean flag=true;
            for (int i = 0; i < piecesSet.size(); i++) {
                if (piecesSet.elementAt(i).getType()==each){
                    flag=false;
                    break;
                }
            }

            if (flag){
                System.out.println("Not used pieces:"+each);
                use.add(each);
            }

        }
        Character[] result=use.toArray(new Character[use.size()]);
        return result;
    }


    /**
     * Given a string describing a placement of pieces and pegs, return a set
     * of all possible next viable piece placements.   To be viable, a piece
     * placement must be a valid placement of a single piece.  The piece must
     * not have already been placed (ie not already in the placement string),
     * and its placement must be valid.   If there are no valid piece placements
     * for the given placement string, return null.
     * <p>
     * When symmetric placements of the same piece are viable, only the placement
     * with the lowest rotation should be included in the set.
     *
     * @param placement A valid placement string (comprised of peg and piece placements)
     * @return An set of viable piece placements, or null if there are none.
     */


    public static Set<String> getViablePiecePlacements(String placement) {
        assert isPlacementStringValid(placement);
        Set<String> Allset=new HashSet();

        isPlacementStringValid(placement);
        Set<String> canUseNode=getNotUseNode();
        Character[] canUsePieces=getNotUsePieces();

        for (char type :canUsePieces){
            for (String position:canUseNode) {
                for (int i = 0; i < 8; i++) {
                    String tryPieces=type+""+position+""+i+"";
                    String tryPut=placement+tryPieces;
                    System.out.println("try:"+tryPieces);
                    if (isPlacementStringValid(tryPut)){
                        Allset.add(tryPieces);
                        System.out.println(tryPieces+" is OK try.");
                    }
                    else {
                        System.out.println(tryPieces+" is Bad try.");
                    }

                }
            }
        }

        System.out.println('\n'+"Next placement:");
        for (String each:Allset) {
            System.out.print(each+", ");
        }

        Allset=RemoveSymmetry(Allset);

        if (Allset.isEmpty()){
            return null;
        }

        return Allset;

        // FIXME Task 6: determine the set of valid next piece placements
    }

    public static Set<String> RemoveSymmetry(Set<String> set){

        return null;
    }
    /**
     * Return an array of all unique solutions for a given starting placement.
     * <p>
     * Each solution should be a 32-character string giving the placement sequence
     * of all eight pieces, given the starting placement.
     * <p>
     * The set of solutions should not include any symmetric piece placements.
     * <p>
     * In the IQ-Twist game, valid challenges can have only one solution, but
     * other starting placements that are not valid challenges may have more
     * than one solution.  The most obvious example is the unconstrained board,
     * which has very many solutions.
     *
     * @param placement A valid piece placement string.
     * @return An array of strings, each 32-characters long, describing a unique
     * unordered solution to the game given the starting point provided by placement.
     */
    public static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }
}
