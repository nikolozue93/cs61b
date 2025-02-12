public class Main {
    /* Exercise 2.5: starTriangle */
    public static void StarTriangle() {
        for(int i = 1; i <= 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    What is the output of the following loop?

    int total = 25;
    for (int number = 1; number <= (total / 2); number++) {
        total = total - number;
        System.out.println(total + " " + number);
    }

    24 1
    22 2
    19 3
    15 4
    10 5
*/

    /* STUTTER
    Write a method called stutter that accepts a parameter
    and returns the String with its characters returns repeated twice.
    For example, stutter("Hello!") returns "HHeelllloo!!"
    */
    public static String Stutter(String input){
        int length = input.length();

        if(length < 1){
            return "Error, input is empty";
        }

        String output = "";
        char letter;
        for(int i = 0; i < input.length(); i++){
            letter = input.charAt(i);
            for(int j = 0; j < 2; j++){
                output += letter;
            }

        }
        return output;
    }


    /*
    IF ELSE MYSTERY 4.5
    Consider the following method.

    public static void ifElseMystery1(int x, int y) {
    int z = 4;
    if (z <= x) {
        z = x + 1;
    } else {
        z = z + 9;
    }
    if (z <= y) {
        y++;
    }
    System.out.println(z + " " + y);
}
    For each call below, indicate what output is produced.

    ifElseMystery1(3, 20);  13 21
    ifElseMystery1(4, 5);	5 6
    ifElseMystery1(5, 5);	6 5
    ifElseMystery1(6, 10);  7 11
    */



    /*
    QUADRANT 4.19
    Write a static method called quadrant that takes as parameters a pair of real numbers representing an (x, y) point and that returns
    the quadrant number for that point. Recall that quadrants are numbered as integers from 1 to 4 with the upper-right quadrant numbered
    1 and the subsequent quadrants numbered in a counter-clockwise fashion:

    Notice that the quadrant is determined by whether the x and y coordinates are positive or negative numbers. If a point falls on
    the x-axis or the y-axis, then the method should return 0. Below are sample calls on the method.

        Call	                    Value Returned
    quadrant(12.4, 17.8)	               1
    quadrant(-2.3, 3.5)	                   2
    quadrant(-15.2, -3.1)              	   3
    quadrant(4.5, -42.0)	               4
    quadrant(0.0, 3.14)	                   0

*/

    public static int quadrant(double x, double y){
        if(x == 0.0 || y == 0.0){
            return 0;
        }

        if(x > 0 && y > 0){
            return 1;
        } else if(x < 0 && y > 0){
            return 2;
        } else if(x < 0 && y < 0){
            return 3;
        } else {
            return 4;
        }
    }

    public static void main(String[] args) {
        StarTriangle();
        System.out.println(Stutter("Hello!"));

        System.out.println(quadrant(12.4, 17.8));
        System.out.println(quadrant(-2.3, 3.5));
        System.out.println(quadrant(-15.2, -3.1));
        System.out.println(quadrant(4.5, -42.0));
        System.out.println(quadrant(0.0, 3.14));
    }
}