package comp1110.ass2.Elements;

import java.lang.reflect.Type;

/**
 * Create by Sean 2018-08-21
 * Last modify: 2018-08-21 10:15:05 by Zhi Wang

 *This class represents pegs in the game.There is 7seven pegs in total.
 *Two of them are green, two are blue and two yellow pegs, but just one red peg.
 * The pegs are not placed by the player during the game, but rather, they one or more pegs is placed on
 * the board at the start of the game as part of the challenge.
 */
//TODO Finished this commit about this class
public class Peg {

    private  char type;
    private  int postion;

    /**
     * Constructor for Peg, which does not have a fixed position and certain type
     *
     * @param position one of the params to define peg
     * @param type  one of the params to define peg
     */
    Peg(char type,int position){
        this.type=type;
        this.postion=position;

        //TODO initialize for a certain BoardPosition.
    }

    /** @return the character type for peg*/

    public  char getType(){
        return type;
    }

}
