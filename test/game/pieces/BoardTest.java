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
    
    @Test
    public void getFallingSquaresTest() {
        Board boardDefault = new Board();
        int actual = boardDefault.getFallingSquares().size();
        int expected = 2;
        assertEquals(expected, actual);
        
        Board board3x3Empty = new Board(3, 3);
        actual = board3x3Empty.getFallingSquares().size();
        expected = 2;
        assertEquals(expected, actual);
        
        /**
         * [   ][0,1][   ]
         * [1,0][1,1][1,2]
         * [   ][2,1][   ]
         */
        Board boardCross = new Board(new SquaresMatrix(3, 3)
            .insertSquare(0, 1, SquareColour.BLUE)
            .insertSquare(1, 0, SquareColour.BLUE)
            .insertSquare(1, 1, SquareColour.BLUE)
            .insertSquare(1, 2, SquareColour.BLUE)
            .insertSquare(2, 1, SquareColour.BLUE)
        );
        expected = 2;
        actual = boardCross.getFallingSquares().size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void isPositionAvailableTest() {
        Board board3x3Empty = new Board(3, 3);
        boolean expected = true;
        boolean actual;
        for(int x = 0; x < 3 ; x++)
            for(int y = 0; y < 3; y++) {
                actual = board3x3Empty.isPositionAvailable(x, y);
                assertEquals(expected, actual);
            }
    }
    
    @Test
    public void isValidSwapTest() {
        /**
         * [   ][0,1][   ]
         * [1,0][1,1][1,2]
         * [   ][2,1][   ]
         */
        Board boardCross = new Board(new SquaresMatrix(3, 3)
            .insertSquare(0, 1, SquareColour.BLUE)
            .insertSquare(1, 0, SquareColour.BLUE)
            .insertSquare(1, 1, SquareColour.BLUE)
            .insertSquare(1, 2, SquareColour.BLUE)
            .insertSquare(2, 1, SquareColour.BLUE)
        );
        
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
        Board actual = new Board(new SquaresMatrix(2, 2)
                .insertSquare(0, 0, SquareColour.PINK)
                .insertSquare(1, 0, SquareColour.PINK)
                .insertSquare(0, 1, SquareColour.PINK)
                .insertSquare(1, 1, SquareColour.PINK));
        actual.deleteCompletedTetrisShapes();
        /**
         * [ ][ ]
         * [ ][ ]
         */
        Board expected = new Board(2, 2);
        assertEquals(expected, actual);
        
        /**
         * Although we have a valid shape, nothing will be removed because 
         * we have different colors within that shape.
         */
        
        /**
         *   0  1
         * 0[+][+]
         * 1[+][+]
         */
        actual = new Board(new SquaresMatrix(2, 2)
                .insertSquare(0, 0, SquareColour.YELLOW)
                .insertSquare(1, 0, SquareColour.YELLOW)
                .insertSquare(0, 1, SquareColour.YELLOW)
                .insertSquare(1, 1, SquareColour.RED));
        actual.deleteCompletedTetrisShapes();
        expected = new Board(new SquaresMatrix(2, 2)
                .insertSquare(0, 0, SquareColour.YELLOW)
                .insertSquare(1, 0, SquareColour.YELLOW)
                .insertSquare(0, 1, SquareColour.YELLOW)
                .insertSquare(1, 1, SquareColour.RED));
        assertEquals(expected, actual);
    }
    
}