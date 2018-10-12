Reviewer: Ning Cai (u6456964)
Component: 
Author: Xiang Li (u6716878)

###Review Comments:

```java
public static String[] getSolutions(String placement) {
        HashSet<String> setnext = new HashSet<>();
        HashSet<String> settemp = new HashSet<>();
        HashSet<String> result = new HashSet<>();

        Vector<Vector> steps = new Vector<>();
        Vector<String> step = new Vector<>();
        HashSet<String> stepset = new HashSet<>();
        step.add(placement);
        steps.add((Vector) step.clone());
        step.clear();

        do {
            int count = 0;
            for (int i = 0; i < steps.lastElement().size(); i++) {
                String placementstr = (String) steps.lastElement().get(i);
                setnext = (HashSet<String>) getViablePiecePlacements(placementstr);

                for (String each : setnext) {
                    String temp = placementstr + each;
                    temp = Reorder(temp);
                    settemp = (HashSet<String>) getViablePiecePlacements(temp);
                    if (settemp != null) {
                        stepset.add(temp);
                        //System.out.println(each+" "+temp);
                    } else {
                        String retemp = temp;
                        if (retemp.length() >= 32 && !result.contains(retemp.substring(0, 32))) {
                            if (isPlacementStringValid(retemp) && checkString(retemp)) {
                                // System.out.println(each+" Done: "+temp);
                                result.add(retemp.substring(0, 32));
                            }
                        }
                    }
                }
            }

            for (String each : stepset) {
                step.add(each);
            }
            stepset.clear();
            steps.add((Vector) step.clone());
            step.clear();

        } while (checkStringFinal(steps));


        String[] s = (String[]) result.toArray(new String[0]);
        System.out.println(result.toString());
        return s;
```

The logic of this piece of code is very clean with reasonable use of looping statements, and the layout of the code is very clear which helps to read and understand his
intentions.
It is worth to learn, because such a clear format and logical structure may be convenient for us to understand the meaning after we finish the code for a period of time 
and to find bugs when the code is not working well.

###Conclusion:
Xiang Li is He is very rational, and at the same time has a good grasp of the structure of the entire program, sometimes helping us, and his code is as orderly as himself.