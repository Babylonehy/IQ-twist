package comp1110.ass2;

import comp1110.ass2.Game.Pieces;

import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides the text interface for the Twist Game
 * <p>
 * The game is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 */
public class TwistGame {

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

      // FIXME Task 3: determine whether a placement is well-formed
      Vector position = new Vector();
      Vector positionpeg = new Vector();
      String temp = "";
      String single = "";
      int count=1;

      char before = (char) 30;

      if (placement.length() % 4 != 0 || placement.length() == 0) {
          return false;
      } else {
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
  }
  public static char [][] decodeTotype_position(String placement){
    assert isPlacementStringWellFormed(placement);
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

        // FIXME Task 5: determine whether a placement string is valid
        return false;
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
        // FIXME Task 6: determine the set of valid next piece placements
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
