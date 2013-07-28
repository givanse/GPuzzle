package game.patterns;

import game.pieces.Square;
import game.pieces.SquaresMatrix;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class TSquareTest extends TSquare {
    
    @Test
    public void findPatternMatchTest() {
        /**
         *  0123
         * 0    0
         * 1 bb 1
         * 2 bb 2
         * 3    3
         */
        SquaresMatrix squares = new SquaresMatrix(4, 4)
                .insertSquare(1, 1, Square.SquareColour.BLUE)
                .insertSquare(2, 1, Square.SquareColour.BLUE)
                .insertSquare(1, 2, Square.SquareColour.BLUE)
                .insertSquare(2, 2, Square.SquareColour.BLUE);
        /* pattern match a square shape */
        int x, y;
        int expecteds[][] = new int[0][0];
        int actuals[][];
        for(x = 0; x < squares.getNumberOfColumns(); x++) {
            y = 0;
            actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
            assertArrayEquals(expecteds, actuals);
            y = squares.getNumberOfRows() - 1;
            actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
            assertArrayEquals(expecteds, actuals);
        }
        
        x = 0; y = 1;
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        x = 0; y = 2;
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        
        x = 3; y = 1;
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        x = 3; y = 2;
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        
        /* The next four tests will find a match. */
        
        /**
         *  0123
         * 0    0
         * 1 bb 1
         * 2 bb 2
         * 3    3
         */
        x = 1; y = 1;
        expecteds = new int[][]{{1, 1}, {2, 1}, {1, 2}, {2, 2}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        
        x = 2; y = 1;
        expecteds = new int[][]{{2, 1}, {1, 1}, {2, 2}, {1, 2}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        
        x = 1; y = 2;
        expecteds = new int[][]{{1, 2}, {2, 2}, {1, 1}, {2, 1}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        
        x = 2; y = 2;
        expecteds = new int[][]{{2, 2}, {1, 2}, {2, 1}, {1, 1}};
        actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
        assertArrayEquals(expecteds, actuals);
        
        /* The next tests fail because there is a color/SquareType mismatch */
        
        /**
         *  0123
         * 0    0
         * 1 bb 1
         * 2 br 2
         * 3    3
         */
        expecteds = new int[0][0];
        squares.insertSquare(2, 2, Square.SquareColour.RED);
        for(x = 1; x <= 2; x++)
            for(y = 1; y <= 2; y++) {
                actuals = TetrominoType.SQUARE.isPatternFound(x, y, squares);
                assertArrayEquals(expecteds, actuals);
            }
        
        /**
         *  0123456789
         * 0          0
         * 1          1
         * 2          2
         * 3        pp3
         * 4        pp4
         * 5          5
         */
        squares = new SquaresMatrix(10, 6)
                .insertSquare(8, 3, Square.SquareColour.PINK)
                .insertSquare(9, 3, Square.SquareColour.PINK)
                .insertSquare(8, 4, Square.SquareColour.PINK)
                .insertSquare(9, 4, Square.SquareColour.PINK);
        
        expecteds = new int[][]{{8, 3}, {9, 3}, {8, 4}, {9, 4}};
        actuals = TetrominoType.SQUARE.isPatternFound(8, 3, squares);
        assertArrayEquals(expecteds, actuals);
        
        /* 1x1 */
        squares = new SquaresMatrix(1, 1)
                .insertSquare(0, 0, Square.SquareColour.RED);
        expecteds = new int[0][0];
        actuals = TetrominoType.SQUARE.isPatternFound(0, 0, squares);
        assertArrayEquals(expecteds, actuals);
    }
   
}