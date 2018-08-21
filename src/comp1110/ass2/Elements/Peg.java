package comp1110.ass2.Elements;

import java.lang.reflect.Type;

/**
 * Create by Sean 2018-08-21
 *
 *
 * Last modify: 2018-08-21 16:11:05 by Sean
 */


public class Peg {

    private  char type;
    private  int postion;


    Peg(char type,int position){
        this.type=type;
        this.postion=position;

        //TODO initialize for a certain BoardPosition.
    }


    public  char getType(){
        return type;
    }

}
