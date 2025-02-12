public class Dessert {
    private int flavor;
    private int price;
    public static int numDesserts = 0;
    public Dessert(int f, int p){
        this.flavor = f;
        this.price = p;
        numDesserts++;
    }

    public void printDessert(){
        System.out.println(this.flavor + " " + this.price + " " + Dessert.numDesserts);
    }

    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
}
