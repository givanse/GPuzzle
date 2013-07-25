package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class SquareMatrixTest {
    
    @Test 
    public void insertSquareTest() {
        /* 1 x 1 */
        SquaresMatrix squaresMatrix = new SquaresMatrix(1, 1)
                .insertSquare(0, 0, Square.SquareColour.BLUE);
        
        Square expected = new Square(0, 0, Square.SquareColour.BLUE);
        Square actual = squaresMatrix.getSquare(0, 0);
        assertEquals(expected, actual);
        
        try {
            squaresMatrix.insertSquare(100, 100, Square.SquareColour.BLUE);
            fail("A Square can't be added in that position.");
        } catch(IndexOutOfBoundsException ex) {}
                
        /* 2 x 2 */
        squaresMatrix = new SquaresMatrix(2, 2)
                .insertSquare(0, 0, Square.SquareColour.GREEN)
                .insertSquare(1, 1, Square.SquareColour.PINK);
        
        expected = new Square(0, 0, Square.SquareColour.GREEN);
        actual = squaresMatrix.getSquare(0, 0);
        assertEquals(expected, actual);
        
        expected = new Square(1, 1, Square.SquareColour.PINK);
        actual = squaresMatrix.getSquare(1, 1);
        assertEquals(expected, actual);
        
        assertNull(squaresMatrix.getSquare(0, 1));
        assertNull(squaresMatrix.getSquare(1, 0));
    }
    
    @Test 
    public void getWidthAndHeightTest() {
        /* 2 x 2 */
        SquaresMatrix squaresMatrix = new SquaresMatrix(2, 2);
        int expected = 2;
        int actual = squaresMatrix.getWidth();
        assertEquals(expected, actual);
        expected = 2;
        actual = squaresMatrix.getHeight();
        assertEquals(expected, actual);
        /* 3 x 7 */
        squaresMatrix = new SquaresMatrix(3, 7);
        expected = 3;
        actual = squaresMatrix.getWidth();
        assertEquals(expected, actual);
        expected = 7;
        actual = squaresMatrix.getHeight();
        assertEquals(expected, actual);
        /* 4 x 3 */
        squaresMatrix = new SquaresMatrix(4, 3);
        expected = 4;
        actual = squaresMatrix.getWidth();
        assertEquals(expected, actual);
        expected = 3;
        actual = squaresMatrix.getHeight();
        assertEquals(expected, actual);
    }

    @Test 
    public void packColumnTest() {
        /**
         * [+][+]
         * [ ][ ]
         */
        SquaresMatrix actual = new SquaresMatrix(2, 2)
                .insertSquare(0, 0, Square.SquareColour.YELLOW)
                .insertSquare(1, 0, Square.SquareColour.PINK);
        /**
         * [+][ ]
         * [ ][+]
         */
        actual.packColumn(1, 1);
        
        SquaresMatrix expected = new SquaresMatrix(2, 2)
                .insertSquare(0, 0, Square.SquareColour.YELLOW)
                .insertSquare(1, 1, Square.SquareColour.PINK);
        assertEquals(expected, actual);
        /**
         * [ ][ ]
         * [+][+]
         */
        actual.packColumn(0, 1);
        expected = new SquaresMatrix(2, 2)
                .insertSquare(0, 1, Square.SquareColour.YELLOW)
                .insertSquare(1, 1, Square.SquareColour.PINK);
        assertEquals(expected, actual);
        
        /**
         * [+]
         * [ ]
         * [+]
         * [ ]
         */
        actual = new SquaresMatrix(1, 4)
                .insertSquare(0, 0, Square.SquareColour.BLUE)
                .insertSquare(0, 2, Square.SquareColour.RED);
        actual.packColumn(0, 3);
        /**
         * [ ]
         * [ ]
         * [+]
         * [+]
         */
        expected = new SquaresMatrix(1, 4)
                .insertSquare(0, 2, Square.SquareColour.BLUE)
                .insertSquare(0, 3, Square.SquareColour.RED);
        assertEquals(expected, actual);
        
        /**
         *   0  1
         * 0[ ][y]
         * 1[p][ ]
         */
        actual = new SquaresMatrix(2, 2)
                .insertSquare(1, 0, Square.SquareColour.YELLOW)
                .insertSquare(0, 1, Square.SquareColour.PINK);
        actual.packColumn(1, 1);
        /**
         *   0  1
         * 0[ ][ ]
         * 1[p][y]
         */
        expected = new SquaresMatrix(2, 2)
                .insertSquare(1, 1, Square.SquareColour.YELLOW)
                .insertSquare(0, 1, Square.SquareColour.PINK);
        assertEquals(expected, actual);
    }
    
    @Test
    public void moveDownFlyingSquaresTest() {
        /**
         * [+][+]
         * [ ][ ]
         */
        SquaresMatrix actual = new SquaresMatrix(2, 2)
               .insertSquare(0, 0, Square.SquareColour.BLUE)
               .insertSquare(1, 0, Square.SquareColour.RED);
        actual.moveDownFlyingSquares();
        /**
         * [ ][ ]
         * [+][+]
         */
        SquaresMatrix expected = new SquaresMatrix(2, 2)
                .insertSquare(0, 1, Square.SquareColour.BLUE)
                .insertSquare(1, 1, Square.SquareColour.RED);
        assertEquals(expected, actual);
        
        /**
         *   0  1
         * 0[ ][y]
         * 1[p][ ]
         */
        actual = new SquaresMatrix(2, 2)
                .insertSquare(1, 0, Square.SquareColour.YELLOW)
                .insertSquare(0, 1, Square.SquareColour.PINK);
        actual.moveDownFlyingSquares();
        /**
         *   0  1
         * 0[ ][ ]
         * 1[p][y]
         */
        expected = new SquaresMatrix(2, 2)
                .insertSquare(1, 1, Square.SquareColour.YELLOW)
                .insertSquare(0, 1, Square.SquareColour.PINK);
        assertEquals(expected, actual);
        
        /**
         * [+][+]
         * [ ][+]
         */
        actual = new SquaresMatrix(2, 2)
                .insertSquare(0, 0, Square.SquareColour.YELLOW)
                .insertSquare(1, 0, Square.SquareColour.GREEN)
                /*            0, 1, null */
                .insertSquare(1, 1, Square.SquareColour.BLUE);
        actual.moveDownFlyingSquares();
        /**
         * [ ][+]
         * [+][+]
         */
        expected = new SquaresMatrix(2, 2)
                /*            0, 0, null */
                .insertSquare(1, 0, Square.SquareColour.GREEN)
                .insertSquare(0, 1, Square.SquareColour.YELLOW)
                .insertSquare(1, 1, Square.SquareColour.BLUE);
        assertEquals(expected, actual);
        
        /**
         * [+][+]
         * [ ][ ]
         * [+][+]
         * [ ][ ]
         */
        actual = new SquaresMatrix(2, 4)
                .insertSquare(0, 0, Square.SquareColour.BLUE)
                .insertSquare(1, 0, Square.SquareColour.GREEN)
                .insertSquare(0, 2, Square.SquareColour.PINK)
                .insertSquare(1, 2, Square.SquareColour.RED);
        actual.moveDownFlyingSquares();
        /**
         * [ ][ ]
         * [ ][ ]
         * [+][+]
         * [+][+]
         */
        expected = new SquaresMatrix(2, 4)
                .insertSquare(0, 2, Square.SquareColour.BLUE)
                .insertSquare(1, 2, Square.SquareColour.GREEN)
                .insertSquare(0, 3, Square.SquareColour.PINK)
                .insertSquare(1, 3, Square.SquareColour.RED);
        assertEquals(expected, actual);
    }
}