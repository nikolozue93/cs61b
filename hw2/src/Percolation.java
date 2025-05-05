import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.sql.Array;


public class Percolation {
    boolean[][] opened;
    boolean[][] filled;
    int openSite;
    int N;
    WeightedQuickUnionUF wqUnion;

    public Percolation(int N) {
        this.N = N;
        opened = new boolean[N][N];  // to keep up with open sites
        filled = new boolean[N][N];
        openSite = 0;
        wqUnion = new WeightedQuickUnionUF(N * N);

        // filling the first row
        for (int i = 0; i < N; i++) {
            filled[0][i] = true;
        }
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            opened[row][col] = true;
            openSite++;

            int p, q; // for 1d corresponding number

            int x = row;
            int y = col;
            if (x + 1 < N) {
                if (isOpen(x + 1, y)) {
                    p = xyTo1d(x, y);  // 1d value
                    q = xyTo1d(x + 1, y);

                    wqUnion.union(p, q);

                    // first we check if they got connected
                    // then we check if either of those two are filled
                    // and then we fill them
                    if (wqUnion.connected(p, q)) {
                        if (isFull(x, y) || isFull(x + 1, y)) {
                            filled[x][y] = true;
                            filled[x + 1][y] = true;
                        }
                    }
                }
            }

            if (x - 1 >= 0) {
                if (isOpen(x - 1, y)) {
                    p = xyTo1d(x, y);
                    q = xyTo1d(x - 1, y);

                    wqUnion.union(p, q);

                    if (wqUnion.connected(p, q)) {
                        if (isFull(x, y) || isFull(x - 1, y)) {
                            filled[x][y] = true;
                            filled[x - 1][y] = true;
                        }
                    }
                }
            }

            if (y + 1 < N) {
                if (isOpen(x, y + 1)) {
                    p = xyTo1d(x, y);
                    q = xyTo1d(x, y + 1);

                    wqUnion.union(p, q);

                    if (wqUnion.connected(p, q)) {
                        if (isFull(x, y) || isFull(x, y + 1)) {
                            filled[x][y] = true;
                            filled[x][y + 1] = true;
                        }
                    }
                }
            }

            if (y - 1 >= 0) {
                if (isOpen(x, y - 1)) {
                    p = xyTo1d(x, y);
                    q = xyTo1d(x, y - 1);

                    wqUnion.union(p, q);

                    if (wqUnion.connected(p, q)) {
                        if (isFull(x, y) || isFull(x, y - 1)) {
                            filled[x][y] = true;
                            filled[x][y - 1] = true;
                        }
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);

        return opened[row][col];
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            int p, q;
            q = xyTo1d(row, col);

            for (int i = 0; i < N; i++) {
                p = xyTo1d(0, i);
                if (wqUnion.connected(p, q)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        int p, q;
        for (int i = 0; i < N; i++) {
            p = xyTo1d(0, i);

            for (int j = 0; j < N; j++) {
                q = xyTo1d(N - 1, j);
                if (wqUnion.connected(p, q)) {
                    return true;
                }
            }
        }
        return false;
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

}
