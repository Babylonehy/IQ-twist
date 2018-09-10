package comp1110.ass2.Elements;


import static comp1110.ass2.Elements.BoardStatus.*;
import static comp1110.ass2.Elements.Color.*;


/**
 * Create by Sean 2018-08-21
 *This class represents pegs in the game.There is 7seven pegs in total.
 *Two of them are green, two are blue and two yellow pegs, but just one red peg.
 * The pegs are not placed by the player during the game, but rather, they one or more pegs is placed on
 * the board at the start of the game as part of the challenge.
 *
 * Last modify: 2018-08-21 10:15:05 by Zhi Wang
 */
//TODO Finished this commit about this class
public class Peg {

    private  char type;
    private Color color;

    /**
     * Constructor for Peg, which does not have a fixed position and certain type
     *
     * @param type  one of the params to define peg
     */
    public Peg(char type){
        //TODO initialize for a certain BoardPosition.
        this.type=type;
        insertColor(type);
    }

    /**
     * Write Type
     * @param type
     */
    private void insertColor(char type){
        switch (type){
            case 'i':
                this.color=Red;
                break;
            case 'j':
                this.color=Blue;
                break;
            case 'k':
                this.color=Green;
                break;
            case 'l':
                this.color=Yellow;
                default:
                    break;
        }
    }
    /** @return the character type for peg*/

    public  char getType(){
        return type;
    }

    public Color getColor(){
        return color;
    }


}
