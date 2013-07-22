package game.pieces;


import game.pieces.Square.SquareType;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class SquareTest {

    @BeforeClass
    public static void setUpClass() {
        int expected = 32;
        int actual = Square.SIZE;
        assertEquals(expected, actual);
        
        SquareType squareTypes[] = SquareType.values();
        expected = 5;
        actual = squareTypes.length;
        assertEquals(expected, actual);
    }
        
    @Test
    public void getXTest() {
        Square square = new Square(Square.SquareType.BLUE);
        int expected = 0;
        int actual = square.getX();
        assertEquals(expected, actual);
    }
    
    @Test
    public void getYTest() {
        Square square = new Square(Square.SquareType.GREEN);
        int expected = 0;
        int actual = square.getY();
        assertEquals(expected, actual);
    }
    
    @Test
    public void setXTest() {
        Square square = new Square(Square.SquareType.PINK);
        square.setX(10);
        int expected = 10;
        int actual = square.getX();
        assertEquals(expected, actual);
        // less than zero
        square.setX(-10);
        expected = 0;
        actual = square.getX();
        assertEquals(expected, actual);
    }
    
    @Test
    public void setYTest() {
        Square square = new Square(Square.SquareType.RED);
        square.setY(6);
        int expected = 6;
        int actual = square.getY();
        assertEquals(expected, actual);
        // less than zero
        square.setY(-6);
        expected = 0;
        actual = square.getY();
        assertEquals(expected, actual);
    }
    
    @Test
    public void isMovingTest() {
        Square square = new Square(Square.SquareType.YELLOW);
        boolean expected = true;
        boolean actual = square.isFalling();
        assertEquals(expected, actual);
    }
    
    @Test
    public void stopFallingTest() {
        Square square = new Square(Square.SquareType.YELLOW);
        square.stopFalling();
        boolean expected = false;
        boolean actual = square.isFalling();
        assertEquals(expected, actual);
    }
}