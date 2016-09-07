package assignments.basics;

import java.util.Arrays;

import static java.lang.System.*;

/**
 * Exercising methods with array parameters (using only integer arrays)
 */
public class MethodsWithArrays {

    public static void main(String[] args) {
        new MethodsWithArrays().program();
    }


    void program() {
        int[] arr = {1, 4, 3, 8, 4, 9, 2, -1};
        out.println(Arrays.toString(arr));  // Use Arrays.toString() to get a nice print out
        // Testing the methods.
        // All output should print "true" (uncomment as needed)
        out.println(sumArray(arr) == 30);
        out.println(maxIndex(arr) == 5);
        out.println(countN(arr, 4) == 2);
    }

    // ------------- Write methods below this line --------------------

    // sumArr, maxIndex and countN
    int sumArray(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }
        return sum;
    }
    int maxIndex(int[] array){
        int max = 0;
        for (int i = 1; i < array.length; i++){
            if (array[i] > array[max]){
                max = i;
            }
        }
        return max;
    }
    int countN(int[] array, int n){
        int count = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] == n){
                count++;
            }
        }
        return count;
    }


}
