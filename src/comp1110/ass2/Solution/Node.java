package comp1110.ass2.Solution;

public class Node {
    /**
     * Node class.
     */

        int size = 0;// the number of 1 in eac col
        int row, col;
        Node up, down, left, right, header;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        Node(Node header, int row, int col) {
            this(row, col);
            this.header = header;
        }


}
