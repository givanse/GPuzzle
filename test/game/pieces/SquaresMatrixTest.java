package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class SquaresMatrixTest {
    
    @Test
    public void getSquareTest() {
        /**
         *  0123
         * 0    0
         * 1 xx 1
         * 2 xx 2
         * 3    3
         */
        SquaresMatrix squares = new SquaresMatrix(4, 4)
                .insertSquare(1, 1, Square.SquareColour.BLUE)
                .insertSquare(2, 1, Square.SquareColour.GREEN)
                .insertSquare(1, 2, Square.SquareColour.PINK)
                .insertSquare(2, 2, Square.SquareColour.RED);
        assertNull(squares.getSquare(0, 0));
        assertNull(squares.getSquare(1, 0));
        assertNull(squares.getSquare(2, 0));
        assertNull(squares.getSquare(3, 0));
        
        assertNull(squares.getSquare(0, 1));
        assertNull(squares.getSquare(3, 1));
        assertNull(squares.getSquare(0, 2));
        assertNull(squares.getSquare(3, 2));
        
        assertNull(squares.getSquare(0, 3));
        assertNull(squares.getSquare(1, 3));
        assertNull(squares.getSquare(2, 3));
        assertNull(squares.getSquare(3, 3));
        
        Square expected = new Square(1, 1, Square.SquareColour.BLUE);
        Square actual = squares.getSquare(1, 1);
        assertEquals(expected, actual);
        
        expected = new Square(2, 1, Square.SquareColour.GREEN);
        actual = squares.getSquare(2, 1);
        assertEquals(expected, actual);
        
        expected = new Square(1, 2, Square.SquareColour.PINK);
        actual = squares.getSquare(1, 2);
        assertEquals(expected, actual);
        
        expected = new Square(2, 2, Square.SquareColour.RED);
        actual = squares.getSquare(2, 2);
        assertEquals(expected, actual);
        
        try {
            squares.getSquare(3, 4);
            fail("This test should fail with IndexOutOfBoundsException.");
        } catch(IndexOutOfBoundsException ex) {}
        
        try {
            squares.getSquare(4, 3);
            fail("This test should fail with IndexOutOfBoundsException.");
        } catch(IndexOutOfBoundsException ex) {}
    }
    
    @Test
    public void isPositionAvailableTest() {
        SquaresMatrix squares = new SquaresMatrix(1, 1);
        assertTrue(squares.isPositionAvailable(0, 0));
        
        squares.insertSquare(0, 0, Square.SquareColour.BLUE);
        assertFalse(squares.isPositionAvailable(0, 0));
        
        /**
         *  0123
         * 0    0
         * 1 xx 1
         * 2 xx 2
         * 3    3
         */
        squares = new SquaresMatrix(4, 4)
                .insertSquare(1, 1, Square.SquareColour.BLUE)
                .insertSquare(2, 1, Square.SquareColour.BLUE)
                .insertSquare(1, 2, Square.SquareColour.BLUE)
                .insertSquare(2, 2, Square.SquareColour.BLUE);
        assertTrue(squares.isPositionAvailable(0, 0));
        assertTrue(squares.isPositionAvailable(1, 0));
        assertTrue(squares.isPositionAvailable(2, 0));
        assertTrue(squares.isPositionAvailable(3, 0));
        
        assertTrue(squares.isPositionAvailable(0, 1));
        assertFalse(squares.isPositionAvailable(1, 1));
        assertFalse(squares.isPositionAvailable(2, 1));
        assertTrue(squares.isPositionAvailable(3, 1));
        
        assertTrue(squares.isPositionAvailable(0, 2));
        assertFalse(squares.isPositionAvailable(1, 2));
        assertFalse(squares.isPositionAvailable(2, 2));
        assertTrue(squares.isPositionAvailable(3, 2));
        
        assertTrue(squares.isPositionAvailable(0, 3));
        assertTrue(squares.isPositionAvailable(1, 3));
        assertTrue(squares.isPositionAvailable(2, 3));
        assertTrue(squares.isPositionAvailable(3, 3));
    }
    
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
        /* 0 x 0 */
        squaresMatrix = new SquaresMatrix(0, 0);
        expected = 0;
        actual = squaresMatrix.getWidth();
        assertEquals(expected, actual);
        expected = 0;
        actual = squaresMatrix.getHeight();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteSquaresTest() {
        /* 0 x 0 */
        SquaresMatrix actual = new SquaresMatrix(0, 0);
        actual.deleteSquares(new int[0][0]);
        SquaresMatrix expected = new SquaresMatrix(0, 0);
        assertEquals(expected, actual);
        
        /* 1 x 1 empty */
        actual = new SquaresMatrix(1, 1);
        actual.deleteSquares(new int[][]{{0, 0}});
        expected = new SquaresMatrix(1, 1);
        assertEquals(expected, actual);
        
        /* 1 x 1 not empty */
        actual = new SquaresMatrix(1, 1)
                .insertSquare(0, 0, Square.SquareColour.BLUE);
        actual.deleteSquares(new int[][]{{0, 0}});
        expected = new SquaresMatrix(1, 1);
        assertEquals(expected, actual);
        
        /**
         *  012
         * 0b  0
         * 1 b 1
         * 2  b2
         */
        actual = new SquaresMatrix(3, 3)
                .insertSquare(0, 0, Square.SquareColour.BLUE)
                .insertSquare(1, 1, Square.SquareColour.BLUE)
                .insertSquare(2, 2, Square.SquareColour.BLUE);
        actual.deleteSquares(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        expected = new SquaresMatrix(3, 3);
        assertEquals(expected, actual);
        
        /**
         *  01234
         * 0     0
         * 1 r   1
         * 2  pgp2
         * 3  gyg3
         * 4  pgp4  
         */
        actual = new SquaresMatrix(5, 5)
                .insertSquare(1, 1, Square.SquareColour.RED)
                .insertSquare(2, 2, Square.SquareColour.PINK)
                .insertSquare(3, 2, Square.SquareColour.GREEN)
                .insertSquare(4, 2, Square.SquareColour.PINK)
                .insertSquare(2, 3, Square.SquareColour.GREEN)
                .insertSquare(3, 3, Square.SquareColour.YELLOW)
                .insertSquare(4, 3, Square.SquareColour.GREEN)
                .insertSquare(2, 4, Square.SquareColour.PINK)
                .insertSquare(3, 4, Square.SquareColour.GREEN)
                .insertSquare(4, 4, Square.SquareColour.PINK);
        actual.deleteSquares(new int[][]{{3, 3}, {1, 1}, {4, 3}, {2, 3}});
        /**
         *  01234
         * 0     0
         * 1     1
         * 2  pgp2
         * 3     3
         * 4  pgp4  
         */
        expected = new SquaresMatrix(5, 5)
                .insertSquare(2, 2, Square.SquareColour.PINK)
                .insertSquare(3, 2, Square.SquareColour.GREEN)
                .insertSquare(4, 2, Square.SquareColour.PINK)
                .insertSquare(2, 4, Square.SquareColour.PINK)
                .insertSquare(3, 4, Square.SquareColour.GREEN)
                .insertSquare(4, 4, Square.SquareColour.PINK);;
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
        
        /**
         * [ ][ ]
         * [ ][+]
         * [+][ ]
         * [+][ ]
         * [ ][+]
         */
        actual = new SquaresMatrix(2, 5)
                .insertSquare(1, 1, Square.SquareColour.RED)
                .insertSquare(0, 2, Square.SquareColour.RED)
                .insertSquare(0, 3, Square.SquareColour.RED)
                .insertSquare(1, 4, Square.SquareColour.RED);
        actual.moveDownFlyingSquares();
        /**
         * [ ][ ]
         * [ ][ ]
         * [ ][ ]
         * [+][+]
         * [+][+]
         */
        expected = new SquaresMatrix(2, 5)
                .insertSquare(0, 3, Square.SquareColour.RED)
                .insertSquare(1, 3, Square.SquareColour.RED)
                .insertSquare(0, 4, Square.SquareColour.RED)
                .insertSquare(1, 4, Square.SquareColour.RED);
        assertEquals(expected, actual);
    }
}