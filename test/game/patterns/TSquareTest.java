package game.patterns;

import game.pieces.Square;
import game.pieces.SquaresMatrix;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class TSquareTest {
    
    @Test
    public void findPatternMatchTest() {
        /**
         * oooo
         * oxxo
         * oxxo
         * oooo
         */
        SquaresMatrix squares = new SquaresMatrix(4, 4)
                .setSquare(1, 1, Square.SquareType.BLUE)
                .setSquare(1, 2, Square.SquareType.BLUE)
                .setSquare(2, 1, Square.SquareType.BLUE)
                .setSquare(2, 2, Square.SquareType.BLUE);
        /* pattern match a square shape */
        int x = -1, y = -1;
        String message;
        int expecteds[][] = new int[0][0];
        int actuals[][];
        for(x = 0; x < 4; x++) {
            y = 0;
            message = "SQUARE (" + x + ", " + y + ")";
            actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
            assertArrayEquals(message, expecteds, actuals);
            message = "SQUARE (" + x + ", " + y + ")";
            y = 3;
            actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
            assertArrayEquals(message, expecteds, actuals);
        }
        
        x = 0; y = 1;
        message = "SQUARE (" + x + ", " + y + ")";
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(message, expecteds, actuals);
        x = 0; y = 2;
        message = "SQUARE (" + x + ", " + y + ")";
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(message, expecteds, actuals);
        
        x = 3; y = 1;
        message = "SQUARE (" + x + ", " + y + ")";
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(message, expecteds, actuals);
        x = 3; y = 2;
        message = "SQUARE (" + x + ", " + y + ")";
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(message, expecteds, actuals);
        
        /* The next four tests will find a match. */
        
        x = 1; y = 1;
        expecteds = new int[][]{{1, 1}, {1, 2}, {2, 1}, {2, 2}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        message = "SQUARE (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 1; y = 2;
        expecteds = new int[][]{{1, 2}, {1, 1}, {2, 2}, {2, 1}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        message = "SQUARE (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 2; y = 1;
        expecteds = new int[][]{{2, 1}, {2, 2}, {1, 1}, {1, 2}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        message = "SQUARE (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 2; y = 2;
        expecteds = new int[][]{{2, 2}, {2, 1}, {1, 2}, {1, 1}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        message = "SQUARE (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        /* The next tests fail because there is a color/SquareType mismatch */
        
        expecteds = new int[0][0];
        squares.setSquare(2, 2, Square.SquareType.RED);
        for(x = 1; x <= 2; x++)
            for(y = 1; y <= 2; y++) {
                actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
                message = "SQUARE (" + x + ", " + y + ")";
                assertArrayEquals(message, expecteds, actuals);
            }
    }
   
}