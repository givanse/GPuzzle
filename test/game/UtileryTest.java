package game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class UtileryTest {
    
    @Test
    public void shuffleArrayTest() {
        /* empty array */
        int arr[] = new int[0];
        int actuals[] = Utilery.shuffleArray(arr);
        int expecteds[] = new int[0];
        assertArrayEquals(expecteds, actuals);
        
        /* size 1 */
        arr = new int[]{86};
        actuals = Utilery.shuffleArray(arr);
        expecteds = new int[]{86};
        assertEquals(1, actuals.length);
        assertEquals(86, actuals[0]);
        assertArrayEquals(expecteds, actuals);
        
        /* size 2 */
        arr = new int[]{77, 88};
        for(int i = 0; i < 100; i++) {
            actuals = Utilery.shuffleArray(arr);
            assertEquals(2, actuals.length);
            if(actuals[0] == 88)
                assertEquals(77, actuals[1]);
            if(actuals[0] == 77)
                assertEquals(88, actuals[1]);
            if(actuals[1] == 88)
                assertEquals(77, actuals[0]);
            if(actuals[1] == 77)
                assertEquals(88, actuals[0]);
        }
        
        /* size 5 */
        arr = new int[]{55, 66, 77, 88, 99};
        for(int i = 0; i < 100; i++) {
            actuals = Utilery.shuffleArray(arr);
            assertEquals(5, actuals.length);
            /* verify that all the numbers are present */
            for(Integer initNum : arr) {
                boolean isInitNumInShuffledArr = false;
                shuffledArrayLoop:
                for(Integer shuffledNum : actuals) {
                    if(shuffledNum == initNum) {
                        isInitNumInShuffledArr = true;
                        break shuffledArrayLoop;
                    }
                }
                if(!isInitNumInShuffledArr) {
                    fail("Number" + initNum + 
                         " not found in the shuffled array.");
                }
            }
        }
    }
   
    @Test
    public void getTwoConsecutiveNumbers() {
        int shuffledArray[] = new int[0];
        try {
            Utilery.getTwoConsecutiveNumbers(shuffledArray);
            fail("Should throw IndexOutOfBoundsException.");
        } catch(IndexOutOfBoundsException ex) {}
        
        shuffledArray = new int[]{1000};
        try {
            Utilery.getTwoConsecutiveNumbers(shuffledArray);
            fail("Should throw Error.");
        } catch(Error err) {}
        
        shuffledArray = new int[]{0, 1};
        int actuals[] = Utilery.getTwoConsecutiveNumbers(shuffledArray);
        int expectedC1 = 0;
        int expectedC2 = 1;
        assertEquals(expectedC1, actuals[0]);
        assertEquals(expectedC2, actuals[1]);
        
        shuffledArray = new int[]{1, 0};
        actuals = Utilery.getTwoConsecutiveNumbers(shuffledArray);
        expectedC1 = 1;
        expectedC2 = 0;
        assertEquals(expectedC1, actuals[0]);
        assertEquals(expectedC2, actuals[1]);
        
        shuffledArray = new int[]{4, 5, 6};
        actuals = Utilery.getTwoConsecutiveNumbers(shuffledArray);
        expectedC1 = 4;
        expectedC2 = 5;
        assertEquals(expectedC1, actuals[0]);
        assertEquals(expectedC2, actuals[1]);
        
        shuffledArray = new int[]{6, 4, 5};
        actuals = Utilery.getTwoConsecutiveNumbers(shuffledArray);
        expectedC1 = 6;
        expectedC2 = 5;
        assertEquals(expectedC1, actuals[0]);
        assertEquals(expectedC2, actuals[1]);
    }
    
    @Test
    public void convertPixelCoordsToSquareCoordsTest() {
        int actuals[] = Utilery.convertPixelCoordsToSquareCoords(23, 22);
        int expecteds[] = new int[]{0, 0};
        assertArrayEquals(expecteds, actuals);
        
        actuals = Utilery.convertPixelCoordsToSquareCoords(20, 50);
        expecteds = new int[]{0, 1};
        assertArrayEquals(expecteds, actuals);
        
        actuals = Utilery.convertPixelCoordsToSquareCoords(50, 18);
        expecteds = new int[]{1, 0};
        assertArrayEquals(expecteds, actuals);
        
        actuals = Utilery.convertPixelCoordsToSquareCoords(52, 49);
        expecteds = new int[]{1, 1};
        assertArrayEquals(expecteds, actuals);
        
        actuals = Utilery.convertPixelCoordsToSquareCoords(85, 179);
        expecteds = new int[]{2, 5};
        assertArrayEquals(expecteds, actuals);
        
    }
}