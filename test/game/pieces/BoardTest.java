package game.pieces;

import game.pieces.Board.SwapDirection;
import game.pieces.Square.SquareType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
     * [ ][+][ ]
     * [+][+][+]
     * [ ][+][ ]
     */
    private static final Board boardCross = new Board(new Square[][]{
            {null, new Square(SquareType.RED), null},
            {new Square(SquareType.RED), 
             new Square(SquareType.RED), new Square(SquareType.RED)},
            {null, new Square(SquareType.RED), null}
        });
            
    @BeforeClass
    public static void setUpClass() {
        int expected = 15;
        int actual = Tetris.Shape.values().length;
        assertEquals(expected, actual);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getSquaresTest() {
        Square expected[][] = {};
        Square actual[][] = board3x3Empty.getSquares();
        assertArrayEquals(expected, actual);
        
        actual = boardDefault.getSquares();
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void isPositionAvailableTest() {
        boolean expected = true;
        for(int x = 0; x < 3 ; x++)
            for(int y = 0; y < 3; y++) {
                boolean actual = board3x3Empty.isPositionAvailable(x, y);
                assertEquals(expected, actual);
            }
    }
    
    @Test
    public void updatePositionTest() {
        board3x3Empty.updatePosition(2, 2, false);
        boolean expected = false;
        boolean actual = board3x3Empty.isPositionAvailable(2, 2);
        assertEquals(expected, actual);
    }
    
    @Test
    public void isValidMoveTest() {
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
        actual = boardCross.isValidSwap(0, 1, SwapDirection.DOWN);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(0, 1, SwapDirection.UP);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(0, 1, SwapDirection.LEFT);
        assertEquals(expected, actual);
        expected = false;
        actual = boardCross.isValidSwap(0, 1, SwapDirection.RIGHT);
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
        board3x3Empty.deleteCompletedTetrisShapes();
    }
    
    @Test 
    public void moveDownLooseRowsTest() {
        board3x3Empty.moveDownLooseRows();
    }
}