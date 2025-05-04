import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.sql.Array;


public class Percolation {
    int[][] A;
    boolean[][] opened;
    boolean[][] filled;
    int openSite;
    int N;
    WeightedQuickUnionUF wqUnion;
    int virtualTop;
    int virtualBottom;

    public Percolation(int N) {
        // I think we need to add upper and lower lvls for bounds
        // to have easy check on isFull or not
        // maybe N + 1 and N + 1
        this.N = N;
        A = new int[N][N];
        opened = new boolean[N][N];  // to keep up with open sites
        filled = new boolean[N][N];
        openSite = 0;
        int size = (N * N) + 2; // plus two for virtuals
        wqUnion = new WeightedQuickUnionUF(size);

        // if N is 5, then top 25 and bottom 26
        virtualTop = size - 2; // index 25
        virtualBottom = size - 1; // 26

        // filling the first row
        for(int i = 0; i < N; i++){
            filled[0][i] = true;
        }
    }

    public void open(int row, int col) {
        if(!isOpen(row, col)) {
            opened[row][col] = true;
            openSite++;

            int p, q; // for 1d corresponding number
            // checking and connecting to virtual
            if(row == 0){
                p = xyTo1d(row, col);
                wqUnion.union(virtualTop, p);
            }

            if(row == N - 1){ // to avoid top and bottom and go directly to elements
                p = xyTo1d(row, col);
                wqUnion.union(virtualBottom, p);
            }


            // i think we have to check here up down left right sites
            // if they are open and then make a decision with union
            // and if they are connected with upper node, then we have
            // to make it full OR connect the whole set with it and make it full

            int x = row;
            int y = col;
                    if(x + 1 < N){
                        if(isOpen(x + 1, y)){
                            p = xyTo1d(x, y);  // 1d value
                            q = xyTo1d(x + 1, y);

                            wqUnion.union(p, q);

                            // first we check if they got connected
                            // then we check if either of those two are filled
                            // and then we fill them
                            if(wqUnion.connected(p, q)){
                                if(isFull(x, y) || isFull(x + 1, y)){
                                    filled[x][y] = true;
                                    filled[x + 1][y] = true;
                                }
                            }
                        }
                    }

                    if(x - 1 >= 0){
                        if(isOpen(x - 1, y)){
                            p = xyTo1d(x, y);
                            q = xyTo1d(x - 1, y);

                            wqUnion.union(p, q);

                            if(wqUnion.connected(p, q)){
                                if(isFull(x, y) || isFull(x - 1, y)){
                                    filled[x][y] = true;
                                    filled[x - 1][y] = true;
                                }
                            }
                        }
                    }

                    if(y + 1 < N){
                        if(isOpen(x, y + 1)){
                            p = xyTo1d(x, y);
                            q = xyTo1d(x, y + 1);

                            wqUnion.union(p, q);

                            if(wqUnion.connected(p, q)){
                                if(isFull(x, y) || isFull(x, y + 1)){
                                    filled[x][y] = true;
                                    filled[x][y + 1] = true;
                                }
                            }
                        }
                    }

                    if(y - 1 >= 0){
                        if(isOpen(x, y - 1)){
                            p = xyTo1d(x, y);
                            q = xyTo1d(x, y - 1);

                            wqUnion.union(p, q);

                            if(wqUnion.connected(p, q)){
                                if(isFull(x, y) || isFull(x, y - 1)){
                                    filled[x][y] = true;
                                    filled[x][y - 1] = true;
                                }
                            }
                        }
                    }
        }
    }

    public boolean isOpen(int row, int col) {
        // DONE
        validate(row, col);

        return opened[row][col];
    }

    public boolean isFull(int row, int col) {
//        if(isOpen(row, col)){
//            return filled[row][col];
//        }
//        int p = xyTo1d(row, col);
//
//        if(isOpen(row, col) && virtualTop == wqUnion.find(p)){
//            return filled[row][col];
//        }

        if(isOpen(row, col)){
            int p = xyTo1d(row, col);
            if(wqUnion.find(p) == virtualTop || filled[row][col]){
                filled[row][col] = true;
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        // DONE
        return openSite;
    }

    public boolean percolates() {
        return wqUnion.connected(virtualTop, virtualBottom);
    }

    // transforming 2d array to 1d, by giving it the corresponding number
    public int xyTo1d(int r, int c){
        // DONE
        validate(r, c);

        return r * N + c; // number of row multiplied by the grid size N and added column number
    }



    public void validate(int r, int c){
        // DONE
        if(0 > r || 0 > c || r >= N || c >= N){
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

}
