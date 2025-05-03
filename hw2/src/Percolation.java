import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.sql.Array;


public class Percolation {
    int[][] A;
    boolean[][] B;
    int openSite;
    int N;
    WeightedQuickUnionUF wqUnion;

    public Percolation(int N) {
        // I think we need to add upper and lower lvls for bounds
        // to have easy check on isFull or not
        // maybe N + 1 and N + 1
        this.N = N;
        A = new int[N][N];
        B = new boolean[N][N];  // to keep up with open sites
        openSite = 0;
        wqUnion = new WeightedQuickUnionUF(N * N);
    }

    public void open(int row, int col) {
        if(!isOpen(row, col)) {
            validate(row, col);

            B[row][col] = true;
            openSite++;

            // i think we have to check here up down left right sites
            // if they are open and then make a decision with union
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return false;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return false;
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        // if upper and lower bounds are connected (in union) return true
        return false;
    }

    public int xyTo1d(int r, int c){
        validate(r, c);

        return r * N + c; // number of row multiplied by the grid size N and added column number
    }

    public void validate(int r, int c){
        if(0 > r || 0 > c || r >= N || c >= N){
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

}
