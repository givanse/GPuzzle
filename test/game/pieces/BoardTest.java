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
        actual = boardDefault.getFallingSquares().size();
        expected = 0;
        assertEquals(expected, actual);
        
        /**
         * 2x1
         *  01
         * [  ]
         */
        for(int i = 0; i < 100; i++) {
            Board board = new Board(2, 1);
            ArrayList<Square> fallingSquares = board.getFallingSquares();
            assertEquals(2, fallingSquares.size());
            Square square1 = fallingSquares.get(0);
            Square square2 = fallingSquares.get(1);
            int col1 = 0, col2 = 1;
            if(square1.getX() == col1)
                assertEquals(col2, square2.getX());
            if(square1.getX() == col2)
                assertEquals(col1, square2.getX());
            if(square2.getX() == col1)
                assertEquals(col2, square1.getX());
            if(square2.getX() == col2)
                assertEquals(col1, square1.getX());
        }
        
        /**
         * 4x1
         *  0123
         * [r  r]
         */
        for(int i = 0; i < 100; i++) {
            boardDefault = new Board(new SquaresMatrix(4, 1)
                    .insertSquare(0, 0, SquareColour.RED)
                    .insertSquare(3, 0, SquareColour.RED));
            ArrayList<Square> fallingSquares = boardDefault.getFallingSquares();
            assertEquals(2, fallingSquares.size());

            Square square1 = fallingSquares.get(0);
            Square square2 = fallingSquares.get(1);
            int col1 = 1, col2 = 2;
            int expectedCol1 = square1.getX() == col1 ? col1 : col2;
            int expectedCol2 = expectedCol1 == col1 ? col2 : col1;
            assertEquals(expectedCol1, square1.getX());
            assertEquals(expectedCol2, square2.getX());
        }
        
        /**
         * 4x1
         *  0123
         * [r r ]
         */
        boardDefault = new Board(new SquaresMatrix(4, 1)
                .insertSquare(0, 0, SquareColour.RED)
                .insertSquare(2, 0, SquareColour.RED));
        actual = boardDefault.getFallingSquares().size();
        expected = 0;
        assertEquals(expected, actual);
        
        /* 10x1 */
        for(int i = 0; i < 100; i++) {
            boardDefault = new Board(10, 1);
            ArrayList<Square> fallingSquares = boardDefault.getFallingSquares();
            assertEquals(2, fallingSquares.size());
            
            int col1 = fallingSquares.get(0).getX();
            int col2 = fallingSquares.get(1).getX();
            if(col1 > col2) 
                assertEquals(col1 - 1, col2);
            if(col1 < col2) 
                assertEquals(col1 + 1, col2);
        }
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