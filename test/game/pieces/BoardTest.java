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
            .insertSquare(0, 1, SquareColour.BLUE)
            .insertSquare(1, 0, SquareColour.BLUE)
            .insertSquare(1, 1, SquareColour.BLUE)
            .insertSquare(1, 2, SquareColour.BLUE)
            .insertSquare(2, 1, SquareColour.BLUE)
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
    public void deleteCompletedTetrisShapesTest() {
        /**
         *   0  1
         * 0[+][+]
         * 1[+][+]
         */
        Board board = new Board(new SquaresMatrix(2, 2)
                .insertSquare(0, 0, SquareColour.PINK)
                .insertSquare(1, 0, SquareColour.PINK)
                .insertSquare(0, 1, SquareColour.PINK)
                .insertSquare(1, 1, SquareColour.PINK));
        board.deleteCompletedTetrisShapes();
        SquaresMatrix actuals = board.getSquares();
        /**
         * [ ][ ]
         * [ ][ ]
         */
        SquaresMatrix expecteds = new SquaresMatrix(2, 2);
        assertEquals(expecteds, actuals);
        
        /**
         * Although we have a valid shape, nothing will be removed because 
         * we have different colors within that shape.
         */
        
        /**
         *   0  1
         * 0[+][+]
         * 1[+][+]
         */
        board = new Board(new SquaresMatrix(2, 2)
                .insertSquare(0, 0, SquareColour.YELLOW)
                .insertSquare(1, 0, SquareColour.YELLOW)
                .insertSquare(0, 1, SquareColour.YELLOW)
                .insertSquare(1, 1, SquareColour.RED));
        board.deleteCompletedTetrisShapes();
        actuals = board.getSquares();
        expecteds = new SquaresMatrix(2, 2)
                .insertSquare(0, 0, SquareColour.YELLOW)
                .insertSquare(1, 0, SquareColour.YELLOW)
                .insertSquare(0, 1, SquareColour.YELLOW)
                .insertSquare(1, 1, SquareColour.RED);
        assertEquals(expecteds, actuals);
    }
    
}