Reviewer: Zhi Wang (u6171870)
Component: 
Author: Xiang Li (u6716878)

###Review Comments:
Vector position = new Vector();
        String temp = "";
        String single = "";
        int count = 1;

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
                        if (before == placementChar[i]) {
                            count++;
                            before = placementChar[i];
                            if (count >= 3) {
                                return false;
                            }
                        } else {
                            before = placementChar[i];
                            count = 1;
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
The logic is very clear and he used some if statement with exsiting if statement to clearly state the logic for the code.
MAybe we can make the code in an good order for others to see, forexample, we can use an some statement after former if statement to specify the logic ony by one.
This will makes the code more easier to go through.

###Conclusion:
Xiang Li is very hardworking and his code is very solid.Every time I went through his code, everything is in a good norm and it's easy to work on it and find some class or methods.