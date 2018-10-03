Reviewer: Xiang Li (u6716878)  
Component: <comp1110.ass2.TwistGame.isPlacementWellFormed, comp1110.ass2.gui.Viewer.Nodes>  
Author: Zhi Wang (u6171870)

###Review Comments:  
***<comp1110.ass2.TwistGame.isPlacementWellFormed>***
```java
public static boolean isPlacementWellFormed(String piecePlacement) {
        int l = piecePlacement.length();
        int a = 0;

        if (l % 4 != 0) {
            return false;
        } else {
            a = l / 4;
        }

        if (a == 0) {
            return false;
        }
        if (piecePlacement == null) {
            return false;
        }

        int[] char1, char2, char3, char4;
        char1 = new int[8];
        char2 = new int[8];
        char3 = new int[8];
        char4 = new int[8];

        for (int i = 0; i < a; i++) {
            char1[i] = piecePlacement.charAt(4 * i);
            char2[i] = piecePlacement.charAt(4 * i + 1);
            char3[i] = piecePlacement.charAt(4 * i + 2);
            char4[i] = piecePlacement.charAt(4 * i + 3);
        }
        for (int i = 0; i < a; i++) {

            if (char1[i] > 'h' && char1[i] <= 'l') {
                if (char4[i] != '0') {
                    return false;
                }
            }
            if (char1[i] < 'a' || char1[i] > 'l') {
                return false;
            }
            if (char2[i] < '1' || char2[i] > '8') {
                return false;
            }
            if (char3[i] < 'A' || char3[i] > 'D') {
                return false;
            }
            if (char4[i] < '0' || char4[i] > '7') {
                return false;
            }

        }
        return true;
}
```

1. Clear logic: It is easy to follow the code to understand the Method.
2. Inappropriate variable name: "a" refers to the number of pieces and peg of piecePlacement, But "a" can't demonstrate the meaning of this variable. 
Same with the array, "char1"···.  
3. Code redundancy: The number of loops can be reduced  

```java
            if ((char1[i] < 'a' || char1[i] > 'l')&&(char2[i] < '1' || char2[i] > '8') &&(char3[i] < 'A' || char3[i] > 'D')
             &&(char4[i] < '0' || char4[i] > '7')) {
                return false;
            }
```
4 . A simpler approach: We can use Regex to match text.
```java
  Pattern p=Pattern.compile("[a-h][1-8][A-D][0-7]$|[i-l][1-8][A-D]0$");
  Matcher m=p.matcher(piecePlacement);
  while (m.find()){
      return true;
  }
  return false;
```
If efficiency is important when dealing with problems, we should avoid using Regex expressions   
because Regex expressions are not efficient.
5 . Comments: Comments should be added to make it easier for others to read.

***<comp1110.ass2.gui.Viewer.Nodes>***  
1 .Good variable name: These variables'name refers to their meaning. Users friendly.
```java
        int rotation = 0;
        int peg_index = -1;
        char type = 'z';
        double mouse_x, mouse_y;
        int width, height;
        ·······
```
2 .Bugs and improve:  
```java
if (this.type == 'a' || this.type == 'b' || this.type == 'c' || this.type == 'd' || this.type == 'f') {
                            if (rotation == 1 || rotation == 3) {
                                setLayoutX(BOARD_X + 28 + SQUARE_SIZE * (int) coloumn);
                                setLayoutY(BOARD_X / 2 + 60 + SQUARE_SIZE * (int) raw);
                            }
                        }
```
• The decision condition can be more concise. "this.type" repeat too much.  
• "if (rotation == 1 || rotation == 3)" without thinking about flipping.  
• "setLayoutX(BOARD_X + 28 + SQUARE_SIZE * (int) coloumn);
   setLayoutY(BOARD_X / 2 + 60 + SQUARE_SIZE * (int) raw);"
   "raw" is typo. and "28" & "60" are not suited if  SQUARE_SIZE change.
   This is also not a valid expression, for some pieces cannot be snapped appropriately.
```java
 if (type >= 'i') {
     setLayoutY(0);
     setLayoutX(PEG_X + peg_index * SQUARE_SIZE);
 }
```
• Pegs should not be allowed to moved. So these code are not necessary.  
improve:   
• Find more general ways to achieve snapToGrid. Without the type of pieces, snap should work well.  
• The design of UI parameters can be more reasonable.

###Conclusion:
Zhi can always find a solution to the problem. But when the problem is more complicated, he is accustomed to using multiple conditions to solve the problem. 
This is a good idea, but he tends to ignore a more succinct approach and a more general approach. Therefore, he can try to figure  the problem pattern out rather than solve the problem step by step. This is useful to improve the quality of the code. 
In addition, With the necessary comments and proper naming, code will be better.
