package game.patterns;

import game.pieces.Square;
import game.pieces.SquaresMatrix;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class TSTest {
    
    @Test
    public void findPatternMatchRightHorizontalTest() {
        /**
         * 01234
         * 1 ##1
         * 2## 2
         * 3   3
         */
        SquaresMatrix squares = new SquaresMatrix(5, 4)
                .setSquare(2, 1, Square.SquareType.BLUE)
                .setSquare(3, 1, Square.SquareType.BLUE)
                .setSquare(1, 2, Square.SquareType.BLUE)
                .setSquare(2, 2, Square.SquareType.BLUE);
        /* pattern match a S */
        String message;
        int expecteds[][] = new int[0][0];
        int actuals[][];
        int x, y;
        for(x = 0; x < squares.getWidth(); x++) {
            y = 0;
            actuals = TetrominoType.S.isPatternFound(x, y, squares);
            message = "S (" + x + ", " + y + ")";
            assertArrayEquals(message, expecteds, actuals);
            y = 3;
            actuals = TetrominoType.S.isPatternFound(x, y, squares);
            message = "S (" + x + ", " + y + ")";
            assertArrayEquals(message, expecteds, actuals);
        }
        
        x = 0; y = 1;
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        x = 0; y = 2;
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        x = 1; y = 1;
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 3; y = 2;
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        x = 4; y = 1;
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        x = 4; y = 2;
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        /**
         * 01234
         * 1 ##1
         * 2## 2
         * 3   3
         */
        x = 2; y = 1;
        expecteds = new int[][]{{2, 1}, {3, 1}, {2, 2}, {1, 2}};
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 3; y = 1;
        expecteds = new int[][]{{3, 1}, {2, 1}, {2, 2}, {1, 2}};
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 2; y = 2;
        expecteds = new int[][]{{2, 2}, {1, 2}, {2, 1}, {3, 1}};
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
        
        x = 1; y = 2;
        expecteds = new int[][]{{1, 2}, {2, 2}, {2, 1}, {3, 1}};
        actuals = TetrominoType.S.isPatternFound(x, y, squares);
        message = "S (" + x + ", " + y + ")";
        assertArrayEquals(message, expecteds, actuals);
    }
    
}