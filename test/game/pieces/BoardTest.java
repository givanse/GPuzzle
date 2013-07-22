package game.pieces;

import game.pieces.Board.SwapDirection;
import game.pieces.Square.SquareType;
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
        int actual = Tetris.TetrisShape.values().length;
        assertEquals(expected, actual);
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
    public void moveDownLooseRowsTest() {
        /**
         * [+][+]
         * [ ][ ]
         */
        Board board = new Board(new Square[][]{
                {new Square(SquareType.RED), new Square(SquareType.RED)},
                {null, null}});
        /**
         * [ ][ ]
         * [+][+]
         */
        Square expecteds[][] = new Square[][]{
                {null, null},
                {new Square(SquareType.RED), new Square(SquareType.RED)}};
        board.moveDownLooseRows();
        Square actuals[][] = board.getSquares();
        assertArrayEquals(expecteds, actuals);
        
        /**
         * [ ][+]
         * [+][ ]
         */
        board = new Board(new Square[][]{
                {null, new Square(SquareType.RED)},
                {new Square(SquareType.RED), null}});
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertArrayEquals(expecteds, actuals);
        
        /**
         * [+][+]
         * [ ][+]
         */
        board = new Board(new Square[][]{
                {new Square(SquareType.RED), new Square(SquareType.RED)},
                {null, new Square(SquareType.RED)}});
        /**
         * [ ][+]
         * [+][+]
         */
        expecteds = new Square[][]{
                {null, new Square(SquareType.RED)},
                {new Square(SquareType.RED), new Square(SquareType.RED)}};
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertArrayEquals(expecteds, actuals);
        
        /**
         * [+][+]
         * [ ][ ]
         * [+][+]
         * [ ][ ]
         */
        board = new Board(new Square[][]{
                {new Square(SquareType.BLUE), new Square(SquareType.BLUE)},
                {null, null},
                {new Square(SquareType.BLUE), new Square(SquareType.BLUE)},
                {null, null}});
        /**
         * [ ][ ]
         * [ ][ ]
         * [+][+]
         * [+][+]
         */
        expecteds = new Square[][]{
                {null, null},
                {null, null},
                {new Square(SquareType.BLUE), new Square(SquareType.BLUE)},
                {new Square(SquareType.BLUE), new Square(SquareType.BLUE)}};
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertArrayEquals(expecteds, actuals);
    }
    
    @Test
    public void deleteCompletedTetrisShapesTest() {
        /**
         * [+][+]
         * [+][+]
         */
        Board board = new Board(new Square[][]{
            {new Square(SquareType.YELLOW), new Square(SquareType.YELLOW)},
            {new Square(SquareType.YELLOW), new Square(SquareType.YELLOW)}});
        /**
         * [ ][ ]
         * [ ][ ]
         */
        Square expecteds[][] = new Square[][]{{null, null},
                                              {null, null}};
        board.deleteCompletedTetrisShapes();
        Square actuals[][] = board.getSquares();
        assertArrayEquals(expecteds, actuals);
        
        /**
         * Nothing will be removed because we have different colors, although
         * we have a valid shape.
         */
        /**
         * [+][+]
         * [+][+]
         */
        board = new Board(new Square[][]{
            {new Square(SquareType.YELLOW), new Square(SquareType.YELLOW)},
            {new Square(SquareType.YELLOW), new Square(SquareType.RED)}});
        /**
         * [+][+]
         * [+][+]
         */
        expecteds = new Square[][]{
            {new Square(SquareType.YELLOW), new Square(SquareType.YELLOW)},
            {new Square(SquareType.YELLOW), new Square(SquareType.RED)}};
        board.deleteCompletedTetrisShapes();
        actuals = board.getSquares();
        assertArrayEquals(expecteds, actuals);
    }
    
}