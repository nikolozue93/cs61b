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

    private void auth(int v){
        if(v > this.union.length || v < 0){ // dunno if its right
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int size = union[find(v)];

        while(size > 0){
            size = union[find(v)];
        }

        return -size;
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        auth(v);

        return union[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        auth(v1); auth(v2);
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        auth(v);

        if(union[v] < 0){
            return v;
        }

        int root = v;
        while (parent(root) >= 0) {
            root = parent(root);
        }

        int currParent;
        while (v != root) {
            currParent = parent(v);
            union[v] = root;
            v = currParent;
        }

        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if(connected(v1, v2)){
            return;
        }

        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);

        int root1 = find(v1);
        int root2 = find(v2);

        if(size2 >= size1){
            union[root2] += union[root1];
            union[root1] = root2;
        }

        if(size1 > size2){
            union[root1] += union[root2];
            union[root2] = root1;
        }
    }
}
