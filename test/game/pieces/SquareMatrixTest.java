package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class SquareMatrixTest {

    @Test 
    public void shiftColumnTest() {
        /**
         * [+][+]
         * [ ][ ]
         */
        SquaresMatrix actual = new SquaresMatrix(2, 2);
        actual.setSquare(0, 0, Square.SquareColour.YELLOW);
        actual.setSquare(1, 0, Square.SquareColour.PINK);
        actual.shiftColumn(1, 1);
        /**
         * [+][ ]
         * [ ][+]
         */
        SquaresMatrix expected = new SquaresMatrix(2, 2);
        expected.setSquare(0, 0, Square.SquareColour.YELLOW);
        expected.setSquare(1, 1, Square.SquareColour.PINK);
        assertEquals(expected, actual);
    }
        
    @Test 
    public void moveDownFlyingSquaresTest() {
        /**
         * [+][+]
         * [ ][ ]
         */
        SquaresMatrix actuals = new SquaresMatrix(2, 2);
        actuals.setSquare(0, 0, Square.SquareColour.YELLOW);
        actuals.setSquare(1, 0, Square.SquareColour.PINK);
        actuals.moveDownFlyingSquares();
        /**
         * [ ][ ]
         * [+][+]
         */
        SquaresMatrix expecteds = new SquaresMatrix(2, 2);
        expecteds.setSquare(0, 1, Square.SquareColour.YELLOW);
        expecteds.setSquare(1, 1, Square.SquareColour.PINK);
        assertEquals(expecteds, actuals);
        
        /**
         * [   ][0,1]
         * [1,0][   ]
         */
        actuals = new SquaresMatrix(2, 2)
                .setSquare(0, 1, Square.SquareColour.YELLOW)
                .setSquare(1, 0, Square.SquareColour.PINK);
        actuals.moveDownFlyingSquares();
        assertEquals(expecteds, actuals);
 
        /**
         * [0,0][0,1]
         * [   ][1,1]
         */
        actuals = new SquaresMatrix(2, 2)
                .setSquare(0, 0, Square.SquareColour.YELLOW)
                .setSquare(0, 1, Square.SquareColour.PINK)
                .setSquare(1, 1, Square.SquareColour.PINK);
        actuals.moveDownFlyingSquares();
        /**
         * [ ][+]
         * [+][+]
         */
        expecteds.setSquare(0, 1, Square.SquareColour.PINK);
        assertEquals(expecteds, actuals);
        
        /**
         * [+][+]
         * [ ][ ]
         * [+][+]
         * [ ][ ]
         */
        actuals = new SquaresMatrix(2, 4)
                .setSquare(0, 0, Square.SquareColour.BLUE)
                .setSquare(0, 1, Square.SquareColour.GREEN)
                .setSquare(2, 0, Square.SquareColour.PINK)
                .setSquare(2, 1, Square.SquareColour.RED);
        actuals.moveDownFlyingSquares();
        /**
         * [ ][ ]
         * [ ][ ]
         * [+][+]
         * [+][+]
         */
        expecteds = new SquaresMatrix(2, 4)
                .setSquare(2, 0, Square.SquareColour.BLUE)
                .setSquare(2, 1, Square.SquareColour.GREEN)
                .setSquare(3, 0, Square.SquareColour.PINK)
                .setSquare(3, 1, Square.SquareColour.RED);
        assertEquals(expecteds, actuals);
    }
}