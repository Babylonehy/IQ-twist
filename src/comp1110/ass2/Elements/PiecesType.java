package comp1110.ass2.Elements;

/**
 * Create by Sean 2018-08-21
 * We use enum type to represent 8 different shapes of pieces, which are a,b,c,d,e,f,g,h;
 * Then create empty int[][] for each from a0 to h0;
 * Last modify: 2018-09-10 16:30:36 by Sean
  */

public class PiecesType {

/**
 * Doing assignment to prototype of 8 different shapes, which combines a 4*4 matrix
 *  0 for null;
 *  1 for covered;
 *  -1 for empty;
*/
    static final int[][] a0 = {
        {-1, 1,-1, 0},
        { 0, 0, 1, 0},
        { 0, 0, 0, 0},
        { 0, 0, 0, 0}
    };


    static final int[][] b0 = {
            { 1, 1, 0, 0},
            { 0,-1, 1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };


    static final int[][] c0 = {
            { 1,-1, 1, 1},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };


    static final int[][] d0 = {
            { 1, 1, 1, 0},
            { 0,-1,-1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };



    static final int[][] e0 = {
            { 1,-1, 0, 0},
            { 0,-1, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };


    static final int[][] f0 = {
            { 1, 1,-1, 0},
            { 0,-1, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] g0 = {
            {-1, 0, 0, 0},
            {-1, 1, 1, 0},
            { 0,-1, 0, 0},
            { 0, 0, 0, 0}
    };

    static final int[][] h0 = {
            {-1, 1, 1, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0},
            { 0, 0, 0, 0}
    };

    /**
     * The method mirror-flips pieces vertically by 180°.
     * @returnA set new rotated and fliped Pieces.
     */
    static int[][] Flip(int[][] op) {
      int[][] flip = new int[4][4];

       for(int i = 0; i<flip.length; i++){
           flip[0][i] = op[3][i];
           flip[1][i] = op[2][i];
           flip[2][i] = op[1][i];
           flip[3][i] = op[0][i];
       }
        return flip;
    }
    /**
     * This method rotate piece by 90,180,270,360 degree in right direction
     * @return A set new rotated Pieces.
     */
    private static int[][] RotateRight(int[][]matrix){

      int[][] rotate = new int[4][4];
      int n = matrix[0].length;
      for (int i = 0; i < n; i++)
          for (int j = 0; j < n; j++) {
              rotate[j][n - 1 - i] = matrix[i][j];
          }
       return rotate;

    }

    private static int[][] Flip_Rotate(int[][]matrix,int z){

        int[][] result = matrix;
        if (z>=4){
            matrix=Flip(result);
            result=matrix;
        }
        for (int i = 0; i < z%4; i++) {
            result=RotateRight(matrix);
            matrix=result;
        }
        return result;
    }

    public static void PrintMatrix(int[][]matrix){

        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }


    public  static int[][] getTypeset(char type,int rotation){
        switch (type){
            case 'a':
                return Flip_Rotate(a0,rotation);
            case 'b':
                return Flip_Rotate(b0,rotation);
            case 'c':
                return Flip_Rotate(c0,rotation);
            case 'd':
                return Flip_Rotate(d0,rotation);
            case 'e':
                return Flip_Rotate(e0,rotation);
            case 'f':
                return Flip_Rotate(f0,rotation);
            case 'g':
                return Flip_Rotate(g0,rotation);
            case 'h':
                return Flip_Rotate(h0,rotation);
            default:
                System.out.println("Wrong Type Input!");

        }
        return null;
    }

}
