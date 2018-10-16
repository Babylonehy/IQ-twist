package comp1110.ass2.Solution;

public class DLX {

    class Node {
        int size = 0;
        int row, col; // col for debug
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
}
