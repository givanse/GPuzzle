package game.pieces;

import game.pieces.Board.SwapDirection;
import game.pieces.Square.SquareColour;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class BoardTest {
    
    @Test
    public void getFallingSquaresTest() {
        /* 0x0 */
        Board boardDefault = new Board(0, 0);
        int actual = boardDefault.getFallingSquares().size();
        int expected = 0;
        assertEquals(expected, actual);
        
        /* 1x1 */
        boardDefault = new Board(1, 1);
        ArrayList<Square> fallingSquares = boardDefault.getFallingSquares();
        
        Square square1 = fallingSquares.get(0);
        assertEquals(0, square1.getX());
        assertEquals(0, square1.getY());
        
        Square square2 = fallingSquares.get(1);
        assertEquals(0, square2.getX());
        assertEquals(0, square2.getY());
        
        /* 2x1 */
        boardDefault = new Board(2, 1);
        fallingSquares = boardDefault.getFallingSquares();
        
        square1 = fallingSquares.get(0);
        square2 = fallingSquares.get(1);
        if(square1.getX() == 0) {
            assertEquals(1, square2.getX());
        } else if(square1.getX() == 1) {
            assertEquals(0, square2.getX());
        } else {
            fail("The generated Square has the wrong position.");
        }
        assertEquals(0, square1.getY());
        assertEquals(0, square2.getY());
        
        /**
         * 4x1
         * [red][ ][red][ ]
         */
        boardDefault = new Board(new SquaresMatrix(4, 1)
                .insertSquare(0, 0, SquareColour.RED)
                .insertSquare(2, 0, SquareColour.RED));
        fallingSquares = boardDefault.getFallingSquares();
        
        square1 = fallingSquares.get(0);
        square2 = fallingSquares.get(1);
        if(square1.getX() == 1) {
            assertEquals(3, square2.getX());
        } else if(square1.getX() == 3) {
            assertEquals(1, square2.getX());
        } else {
            fail("The generated Square has the wrong position.");
        }
        assertEquals(0, square1.getY());
        assertEquals(0, square2.getY());
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