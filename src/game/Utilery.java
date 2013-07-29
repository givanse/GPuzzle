package game;

import game.pieces.Square;
import java.util.Random;

/**
 *
 * @author givanse
 */
public class Utilery {

    private static Random RANDOM = new Random(System.currentTimeMillis());
    
    public static int[] shuffleArray(int arr[]) {
        if(arr == null || arr.length == 0)
            return arr;
        
        /**
         * Shuffle the available columns. 
         * Fisher-Yates shuffle a.k.a. the Knuth shuffle, modern version.
         */
        for(int i = arr.length - 1; i >= 1; i--) {
             /* +1 because the n argument is exclusive */
            int j = RANDOM.nextInt(i + 1);
            // swap a[j] and a[i]
            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
    
    public static int[] getTwoConsecutiveNumbers(int shuffledArr[]) {
        /* Use the first column. */
        int x1 = shuffledArr[0];
        int x2 = x1 + 1;
        for(Integer n : shuffledArr) {
            if(x2 == n)
                return new int[]{x1, x2};
        }
        x2 = x1 - 1;
        for(Integer n : shuffledArr) {
            if(x2 == n)
                return new int[]{x1, x2};
        }
        throw new Error("A valid adjacent column was not found.");
    }
    
    public static int[] convertPixelCoordsToSquareCoords(int x, int y) {
        int sqrX = x / Square.SIZE;
        int sqrY = y / Square.SIZE;
        return new int[]{sqrX, sqrY};
    }
}
