public class UnionFind {
    private int[] union;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        union = new int[N];

        for(int i = 0; i < this.union.length; i++){
            union[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -1;
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return -1;
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */

    /* we should search for elements with whatever the value our v holds
    until we find sth less than 0.
     */
    public int find(int v) {
        if(v > this.union.length){ // dunno if its right
            throw new IllegalArgumentException();
        }

        if(union[v] < 0){
            return v;
        }

//        int parent = this.union[v]; // getting parent node
//        int root = -1;
//
//        if(v != 0 || v != this.union.length - 1){
//            if(this.union[v - 1] < parent){
//                for(int i = v; union[i] >= 0; i--){
//                    root = i;
//                }
//                return root;
//            }

//            if(this.union[v - 1] > parent){
//                for(int i = v; union[i] >= 0; i++){
//                    root = i;
//                }
//                return root;
//            }
//        }

        int parent = union[v];
        int id = v; // element index

        while(parent >= 0){
            parent = union[id];
            id--;
        }

        boolean flag = false;
        if(parent < 0){
            flag = true;
        }

        int i = id + 1;
        while(i < v && flag){
            union[i] = id;
            i++;
        }


        return id;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
    }

}
