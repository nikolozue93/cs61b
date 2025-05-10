import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.sql.Array;


public class Percolation {
    private boolean[][] opened;
    private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int openSite;
    private final int N;
    private final int top, bottom;

    private final WeightedQuickUnionUF percolationUF, fullnessUF;

    public Percolation(int N) {
        this.N = N;
        opened = new boolean[N][N];
        openSite = 0;

        percolationUF = new WeightedQuickUnionUF(N * N + 2); // union with top and bottom
        fullnessUF = new WeightedQuickUnionUF(N * N + 1); // union with only top

        top = N * N;
        bottom = N * N + 1;
    }

    public void open(int row, int col){
        if(!isOpen(row, col)) {
            opened[row][col] = true;
            openSite++;

            int p = xyTo1d(row, col);
            if (row == 0) {
                percolationUF.union(top, p);
                fullnessUF.union(top, p);
            }
            if (row == N - 1) {
                percolationUF.union(bottom, p);
            }

            // iterating through directions, previous approach got
            // crammed and made easier, rather than checking manually
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
                    if (isOpen(newRow, newCol)) {
                        int q = xyTo1d(newRow, newCol);
                        percolationUF.union(p, q);
                        fullnessUF.union(p, q);
                    }
                }
            }
        }
    }


    public boolean isOpen(int row, int col) {
        validate(row, col);

        return opened[row][col];
    }

    public boolean isFull(int row, int col){
        // checking for connection with virtual top, index N * N
        return fullnessUF.connected(top, xyTo1d(row, col));
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates(){
        return percolationUF.connected(top, bottom);
    }

    // transforming 2d array to 1d, by giving it the corresponding number
    public int xyTo1d(int r, int c) {
        validate(r, c);

        return r * N + c; // number of row multiplied by the grid size N and added column number
    }

    public void validate(int r, int c) {
        if (0 > r || 0 > c || r >= N || c >= N) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    // old-new approach with only WQs
    //    public void open(int row, int col){
//        if(!isOpen(row, col)){
//            opened[row][col] = true;
//            openSite++;
//
//            if(row == 0){
//                int q = xyTo1d(row, col);
//                fullnessUF.union(top, q);
//                percolationUF.union(top, q);
//            }
//
//            if(row == N - 1){
//                int q = xyTo1d(row, col);
//                percolationUF.union(bottom, q);
//            } else {
//                int p, q; // for 1d corresponding number
//
//                if (row + 1 < N) {
//                    if (isOpen(row + 1, col)) {
//                        p = xyTo1d(row, col);  // 1d value
//                        q = xyTo1d(row + 1, col);
//
//                        percolationUF.union(p, q);
//                        fullnessUF.union(p,q);
//                    }
//                }
//
//                if (row - 1 >= 0) {
//                    if (isOpen(row - 1, col)) {
//                        p = xyTo1d(row, col);
//                        q = xyTo1d(row - 1, col);
//
//                        percolationUF.union(p, q);
//                        fullnessUF.union(p,q);
//                    }
//                }
//
//                if (col + 1 < N) {
//                    if (isOpen(row, col + 1)) {
//                        p = xyTo1d(row, col);
//                        q = xyTo1d(row, col + 1);
//
//                        percolationUF.union(p, q);
//                        fullnessUF.union(p,q);
//                    }
//                }
//
//                if (col - 1 >= 0) {
//                    if (isOpen(row, col - 1)) {
//                        p = xyTo1d(row, col);
//                        q = xyTo1d(row, col - 1);
//
//                        percolationUF.union(p, q);
//                        fullnessUF.union(p,q);
//                    }
//                }
//            }
//        }
//    }
}
