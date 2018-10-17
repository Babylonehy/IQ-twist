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
    private List<Vector<int[]>> ans;


    /**
     * construct
     */
    public Dancinglinks() {
        this.maxAnsNumber = 1; //find a solution is default
        this.nodes = new ArrayList<Node>();
        this.ans = new ArrayList<Vector<int[]>>();
    }

    /**
     * Exact cover,Dancing links
     */

    public void getsolution(int maxAnsNumber) {
        this.maxAnsNumber = maxAnsNumber;
        getsolution();
    }

    public void getsolution() {
        ans.clear();
        createDancingLinks(sparseMatrix);
        search(0);
    }

    private void search(int i) {
    }

    private void createDancingLinks(Vector<int[]> sparseMatrix) {
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




    }


    public static void main(String[] args) {
        Dancinglinks dlx=new Dancinglinks();
        dlx.createDancingLinks(getsparseMatrix());
    }

}
