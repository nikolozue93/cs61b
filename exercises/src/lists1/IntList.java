package lists1;

public class IntList {
    int first;
    IntList rest;
    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size(){
        if(rest == null){
            return 1;
        }

        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int size = 0;

        while(p != null){
            size++;
            p = p.rest;
        }

        return size;
    }

    /** Return the i-th element of the IntList using... recursion! */
    public int get(int i){
        if(i == 0){
            return first;
        }

        return rest.get(i - 1);
    }
}
