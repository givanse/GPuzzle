package game.pieces;

import game.pieces.Board.SwapDirection;
import game.pieces.Square.SquareColour;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class BoardTest {
    
    private static final Board boardDefault = new Board();
    private static final Board board3x3Empty = new Board(3, 3);
    /**
     * [   ][0,1][   ]
     * [1,0][1,1][1,2]
     * [   ][2,1][   ]
     */
    private static final Board boardCross = new Board(new SquaresMatrix(3, 3)
            .setSquare(0, 1, SquareColour.BLUE)
            .setSquare(1, 0, SquareColour.BLUE)
            .setSquare(1, 1, SquareColour.BLUE)
            .setSquare(1, 2, SquareColour.BLUE)
            .setSquare(2, 1, SquareColour.BLUE)
        );
    
    @Test
    public void getFallingSquaresTest() {
        int expected = 2;
        int actual = boardDefault.getFallingSquares().size();
        assertEquals(expected, actual);
        
        expected = 2;
        actual = board3x3Empty.getFallingSquares().size();
        assertEquals(expected, actual);
        
        expected = 2;
        actual = boardCross.getFallingSquares().size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void isPositionAvailableTest() {
        boolean expected = true;
        boolean actual;
        for(int x = 0; x < 3 ; x++)
            for(int y = 0; y < 3; y++) {
                actual = board3x3Empty.isPositionAvailable(x, y);
                assertEquals(expected, actual);
            }
        
        expected = false;
        actual = boardCross.isPositionAvailable(1, 1);
        assertEquals(expected, actual);
    }
    
    @Test
    public void isValidSwapTest() {
        /**
         * < >[+][ ]
         * [+][+][+]
         * [ ][+][ ]
         */
        boolean expected = false;
        boolean actual = boardCross.isValidSwap(0, 0, SwapDirection.DOWN);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(0, 0, SwapDirection.UP);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(0, 0, SwapDirection.LEFT);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(0, 0, SwapDirection.RIGHT);
        assertEquals(expected, actual);
        /**
         * [ ]<+>[ ]
         * [+][+][+]
         * [ ][+][ ]
         */
        expected = true;
        actual = boardCross.isValidSwap(1, 0, SwapDirection.DOWN);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(1, 0, SwapDirection.UP);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(1, 0, SwapDirection.LEFT);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(1, 0, SwapDirection.RIGHT);
        assertEquals(expected, actual);
        /**
         * [ ][+][ ]
         * [+]<+>[+]
         * [ ][+][ ]
         */
        expected = true;
        actual = boardCross.isValidSwap(1, 1, SwapDirection.DOWN);
        assertEquals(expected, actual);
        expected = true;
        actual = boardCross.isValidSwap(1, 1, SwapDirection.UP);
        assertEquals(expected, actual);
        expected = true;
        actual = boardCross.isValidSwap(1, 1, SwapDirection.LEFT);
        assertEquals(expected, actual);
        expected = true;
        actual = boardCross.isValidSwap(1, 1, SwapDirection.RIGHT);
        assertEquals(expected, actual);
        /**
         * [ ][+][ ]
         * [+][+][+]
         * [ ][+]< >
         */
        expected = false;
        actual = boardCross.isValidSwap(2, 2, SwapDirection.DOWN);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(2, 2, SwapDirection.UP);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(2, 2, SwapDirection.LEFT);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(2, 2, SwapDirection.RIGHT);
        assertEquals(expected, actual);
    }
    
    @Test 
    public void moveDownLooseRowsTest() {
        /**
         * [+][+]
         * [ ][ ]
         */
        SquaresMatrix squares = new SquaresMatrix(2, 2);
        squares.setSquare(0, 0, SquareColour.PINK);
        squares.setSquare(0, 1, SquareColour.PINK);
        Board board = new Board(squares);
        /**
         * [ ][ ]
         * [+][+]
         */
        SquaresMatrix expecteds = new SquaresMatrix(2, 2);
        squares.setSquare(1, 0, SquareColour.PINK);
        squares.setSquare(1, 1, SquareColour.PINK);
        board.moveDownLooseRows();
        SquaresMatrix actuals = board.getSquares();
        assertEquals(expecteds, actuals);
        
        /**
         * [   ][0,1]
         * [1,0][   ]
         */
        board = new Board(new SquaresMatrix(2, 2)
                .setSquare(0, 1, SquareColour.PINK)
                .setSquare(1, 0, SquareColour.PINK)
            );
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertEquals(expecteds, actuals);
        
        /**
         * [0,0][0,1]
         * [   ][1,1]
         */
        board = new Board(new SquaresMatrix(2, 2)
                .setSquare(0, 0, SquareColour.PINK)
                .setSquare(0, 1, SquareColour.PINK)
                .setSquare(1, 1, SquareColour.PINK)
            );
        /**
         * [ ][+]
         * [+][+]
         */
        expecteds.setSquare(0, 1, SquareColour.PINK);
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertEquals(expecteds, actuals);
        
        /**
         * [+][+]
         * [ ][ ]
         * [+][+]
         * [ ][ ]
         */
        board = new Board(new SquaresMatrix(2, 4)
                .setSquare(0, 0, SquareColour.PINK)
                .setSquare(0, 1, SquareColour.PINK)
                .setSquare(2, 0, SquareColour.PINK)
                .setSquare(2, 1, SquareColour.PINK)
            );
        /**
         * [ ][ ]
         * [ ][ ]
         * [+][+]
         * [+][+]
         */
        expecteds = new SquaresMatrix(2, 4)
                .setSquare(2, 0, SquareColour.PINK)
                .setSquare(2, 1, SquareColour.PINK)
                .setSquare(3, 0, SquareColour.PINK)
                .setSquare(3, 1, SquareColour.PINK);
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertEquals(expecteds, actuals);
    }
    
    @Test
    public void deleteCompletedTetrisShapesTest() {
        /**
         * [+][+]
         * [+][+]
         */
        Board board = new Board(new SquaresMatrix(2, 2)
                .setSquare(0, 0, SquareColour.PINK)
                .setSquare(0, 1, SquareColour.PINK)
                .setSquare(1, 0, SquareColour.PINK)
                .setSquare(1, 1, SquareColour.PINK)
            );
        /**
         * [ ][ ]
         * [ ][ ]
         */
        SquaresMatrix expecteds = new SquaresMatrix(2, 2);
        board.deleteCompletedTetrisShapes();
        SquaresMatrix actuals = board.getSquares();
        assertEquals(expecteds, actuals);
        
        /**
         * Nothing will be removed because we have different colors, although
         * we have a valid shape.
         */
        /**
         * [+][+]
         * [+][+]
         */
        board = new Board(new SquaresMatrix(2, 2)
                .setSquare(0, 0, SquareColour.YELLOW)
                .setSquare(0, 1, SquareColour.YELLOW)
                .setSquare(1, 0, SquareColour.YELLOW)
                .setSquare(1, 1, SquareColour.RED)
            );
        /**
         * [+][+]
         * [+][+]
         */
        expecteds = new SquaresMatrix(2, 2)
                .setSquare(0, 0, SquareColour.YELLOW)
                .setSquare(0, 1, SquareColour.YELLOW)
                .setSquare(1, 0, SquareColour.YELLOW)
                .setSquare(1, 1, SquareColour.RED);
        board.deleteCompletedTetrisShapes();
        actuals = board.getSquares();
        assertEquals(expecteds, actuals);
    }
    
}