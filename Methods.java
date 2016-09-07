package assignments.basics;

import static java.lang.System.*;

/*
 * Exercising methods with primitive types
 */
public class Methods {

    public static void main(String[] args) {
        new Methods().program();
    }


    void program() {
        // Testing the methods.
        // All output should print "true" (comment/uncomment as needed)
        out.println(sign(-100) == -1);
        out.println(sign(0) == 0);
        out.println(sign(14) == 1);

        out.println(factorial(0)== 1);
        out.println(factorial(1)== 1);
        out.println(factorial(2)== 2);
        out.println(factorial(3)== 6);

        out.println(gcd(24, 8) == 8);
        out.println(gcd(7, 2) == 1);
    }

    // ------------- Write your methods below this line --------------------

    // sign, factorial and gcd
    int sign(int n){
        if ( n == 0){
            return 0;
        }
        else if (n < 0){
            return -1;
        }
        return 1;
    }
    int factorial(int n){
        if (n == 0 || n == 1){
            return 1;
        }
        return n*factorial(n-1);
    }
    double gcd(double a, double b){
        if (b == 0){
            return a;
        }
        double c = a % b;
        a = b;
        b = c;
        return gcd(a,b);
    }


}
