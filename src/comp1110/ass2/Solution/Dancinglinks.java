package comp1110.ass2.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static comp1110.ass2.Solution.Solution.getsparseMatrix;

/**
 * https://en.wikipedia.org/wiki/Dancing_Links
 * https://arxiv.org/pdf/cs/0011047.pdf
 * https://github.com/xnnyygn/algorithm-learning
 * http://garethrees.org/2007/06/10/zendoku-generation/#section-4
 */
public class Dancinglinks {
    
    public int maxAnsNumber;
    public Vector<int[]> sparseMatrix;
    private Node root;
    private List<Node> nodes;
    static List<ArrayList<Integer>> ans;


    /**
     * construct
     */
    public Dancinglinks() {
        this.maxAnsNumber = 1; //find a solution is default
        this.nodes = new ArrayList<Node>();
        this.ans = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Exact cover,Dancing links
     */


    /**
     *
     * @param sparseMatrix
     */

    void createDancingLinks(Vector<int[]> sparseMatrix) {
        this.sparseMatrix=sparseMatrix;
        //Initial root and colhead
        root=new Node(0,0);

        Node col_header=root; //first

        //First row Initial
        for (int i = 0; i < sparseMatrix.get(0).length; i++) {
            col_header.right=new Node(0,i+1);
            col_header.right.left=col_header;
            col_header.header=col_header; //point to self
            col_header=col_header.right;//continue to next col_header
        }
        //a circle to first root node
        col_header.right=root;
        root.left=col_header;

//        Node n=root;
//        for (Node i = n.right; i !=root ; i=n.right) {
//            System.out.println(i.col);
//            n=n.right;
//        }

        //sparseMatrix
        for (int i = 0; i < sparseMatrix.size(); i++) {
            col_header=root.right;
            Node first_node=null; //col first
            Node last_node=null;  //col last

            for (int j = 0; j < sparseMatrix.get(i).length; j++) {
                // connection node
                if (sparseMatrix.get(i)[j]==1){
                    Node node=col_header; //col start
                    //down to the last row(have 1)
                    while (node.down!=null){
                        node=node.down;
                    }
                    node.down=new Node(col_header,i+1,j+1); //create new node
                    node.down.up=node; //circle to back V

                    if (first_node==null){
                        first_node=node.down;
                    }
                    node.down.left=last_node;

                    //circle to back
                    if (last_node!=null){
                        node.down.left.right=node.down;
                    }

                    last_node=node.down;
                    col_header.size++; // plus 1
                }

                col_header=col_header.right; //new col
            }

            if (last_node != null) {
                first_node.left = last_node;
                last_node.right = first_node;
            }
        }


        //last row
        col_header = root.right;
        for (int col = 0; col < sparseMatrix.get(0).length; col++) {
            Node node = col_header;
            while (node.down != null) {
                node = node.down;
            }
            node.down = col_header;
            node.down.up = node;
            col_header = col_header.right;
        }

    }

    /**
     * choose column with least 1
     * @return
     */

    private Node chooseCol() {
        Node Col = root.right;
        for (Node col_header = Col.right; col_header != root; col_header = col_header.right) {
            //
            if (col_header.size<Col.size) {
                Col = col_header;
            }
        }
        return Col;
    }

    /**
     * remove
     * @param removeNode
     */
    private void remove(Node removeNode) {
        removeNode.left.right = removeNode.right;
        removeNode.right.left = removeNode.left;
        for (Node i = removeNode.down; i != removeNode; i = i.down) {
            for (Node j = i.right; j != i; j = j.right) {
                j.up.down = j.down;
                j.down.up = j.up;
                j.header.size--;
            }
        }
    }

    /**
     * reverse
     * @param reverseNode
     */

    private void reverse(Node reverseNode) {
        reverseNode.left.right = reverseNode;
        reverseNode.right.left = reverseNode;
        for (Node i = reverseNode.up; i != reverseNode; i = i.up) {
            for (Node j = i.left; j != i; j = j.left) {
                j.up.down = j;
                j.down.up = j;
                j.header.size++;
            }
        }
    }

    private void solve(int step) {
        if (root.right == root) {
            ans.add(nodesToMatrix(nodes));
            return; //break
        }

        Node col = chooseCol();
        remove(col);

        for (Node i = col.down; i != col; i = i.down) {
            if (nodes.size() > step) {
                nodes.remove(step);
            }
            nodes.add(step, i);
            for (Node j = i.right; j != i; j = j.right) {
                remove(j.header);
            }

            solve(step + 1);

            //break
            if (ans.size() >= maxAnsNumber) return;

            // reverse
            Node ireverse = nodes.get(step);
            for (Node jreverse = ireverse.left; jreverse != ireverse; jreverse = jreverse.left) {
                reverse(jreverse.header);
            }
        }
        reverse(col);
    }

    private ArrayList<Integer> nodesToMatrix( List<Node> nodes) {

        ArrayList<Integer> solution = new ArrayList<Integer>();
        for (int i = 0; i < nodes.size(); i++) {
            int line = nodes.get(i).row - 1;
            solution.add(line);
        }
        return solution;
    }


    public void getsolution(int maxAnsNumber) {
        this.maxAnsNumber = maxAnsNumber;
        getsolution();
    }

    public void getsolution() {
        ans.clear();
        createDancingLinks(sparseMatrix);
        solve(0);
    }

    public static List<ArrayList<Integer>> getAns(){
        return ans;
    }
    public static void main(String[] args) {

    }

}
