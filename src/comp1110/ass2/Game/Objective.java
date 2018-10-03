package comp1110.ass2.Game;


/**
 * Create by Sean 2018-08-21
 * This class will collect the challenge level of the game by input number and set up this game.
 * Last modify: 2018-08-23 11:29:06 by Stella
 * All Comment by Stella
 * ALL Code by Sean
 */

public class Objective {

    private int ChallengeNumber; // the number from the original game (1....100)
    private String Startstate; // peg & pieces should be set into BoardNode

    /**
     * This method offer the challenge level which is input and match the suitable pieces and map for this game.
     *Choose a new objective, given a challenge level.  The method should select a randomized
     *objective from the 100 pre-defined solutions, being sure to select an objective with the correct
     *level of difficulty.
     * @param Startstate Give the suitable information to BoardNode.
     * @param ChallengeNumber The challenge level which is excepted.
     */

    public Objective(String Startstate, int ChallengeNumber){
        assert ChallengeNumber >= 1 && ChallengeNumber <= 100;
        this.Startstate = Startstate;
        this.ChallengeNumber = ChallengeNumber;
    }


    /**
     * Initialize the Challenge level with Start state as Challenge Number equal to 1.
     */

    static Objective[] OBJECTIVE={
            new Objective("",1),

    };
}