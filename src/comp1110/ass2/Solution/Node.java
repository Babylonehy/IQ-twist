package comp1110.ass2.Solution;

/**
 * Node class for dlx.
 */
public class Node {


        int size = 0;// the number of 1 in each col
        int row, col;
        Node up, down, left, right, header;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        Node(Node col_header, int row, int col) {
            this(row, col);
            this.header = col_header;
        }


}
