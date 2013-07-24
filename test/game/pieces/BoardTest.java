package game.pieces;

import game.pieces.Board.SwapDirection;
import game.pieces.Square.SquareType;
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
            .setSquare(0, 1, SquareType.BLUE)
            .setSquare(1, 0, SquareType.BLUE)
            .setSquare(1, 1, SquareType.BLUE)
            .setSquare(1, 2, SquareType.BLUE)
            .setSquare(2, 1, SquareType.BLUE)
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
        for(int x = 0; x < 3 ; x++)
            for(int y = 0; y < 3; y++) {
                boolean actual = board3x3Empty.isPositionAvailable(x, y);
                assertEquals(expected, actual);
            }
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
        SquaresMatrix squares = new SquaresMatrix(2, 2);
        squares.setSquare(0, 0, SquareType.PINK);
        squares.setSquare(0, 1, SquareType.PINK);
        Board board = new Board(squares);
        /**
         * [ ][ ]
         * [+][+]
         */
        SquaresMatrix expecteds = new SquaresMatrix(2, 2);
        squares.setSquare(1, 0, SquareType.PINK);
        squares.setSquare(1, 1, SquareType.PINK);
        board.moveDownLooseRows();
        SquaresMatrix actuals = board.getSquares();
        assertEquals(expecteds, actuals);
        
        /**
         * [   ][0,1]
         * [1,0][   ]
         */
        board = new Board(new SquaresMatrix(2, 2)
                .setSquare(0, 1, SquareType.PINK)
                .setSquare(1, 0, SquareType.PINK)
            );
        board.moveDownLooseRows();
        actuals = board.getSquares();
        assertEquals(expecteds, actuals);
        
        /**
         * [0,0][0,1]
         * [   ][1,1]
         */
        board = new Board(new SquaresMatrix(2, 2)
                .setSquare(0, 0, SquareType.PINK)
                .setSquare(0, 1, SquareType.PINK)
                .setSquare(1, 1, SquareType.PINK)
            );
        /**
         * [ ][+]
         * [+][+]
         */
        expecteds.setSquare(0, 1, SquareType.PINK);
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
                .setSquare(0, 0, SquareType.PINK)
                .setSquare(0, 1, SquareType.PINK)
                .setSquare(2, 0, SquareType.PINK)
                .setSquare(2, 1, SquareType.PINK)
            );
        /**
         * [ ][ ]
         * [ ][ ]
         * [+][+]
         * [+][+]
         */
        expecteds = new SquaresMatrix(2, 4)
                .setSquare(2, 0, SquareType.PINK)
                .setSquare(2, 1, SquareType.PINK)
                .setSquare(3, 0, SquareType.PINK)
                .setSquare(3, 1, SquareType.PINK);
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
                .setSquare(0, 0, SquareType.PINK)
                .setSquare(0, 1, SquareType.PINK)
                .setSquare(1, 0, SquareType.PINK)
                .setSquare(1, 1, SquareType.PINK)
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
                .setSquare(0, 0, SquareType.YELLOW)
                .setSquare(0, 1, SquareType.YELLOW)
                .setSquare(1, 0, SquareType.YELLOW)
                .setSquare(1, 1, SquareType.RED)
            );
        /**
         * [+][+]
         * [+][+]
         */
        expecteds = new SquaresMatrix(2, 2)
                .setSquare(0, 0, SquareType.YELLOW)
                .setSquare(0, 1, SquareType.YELLOW)
                .setSquare(1, 0, SquareType.YELLOW)
                .setSquare(1, 1, SquareType.RED);
        board.deleteCompletedTetrisShapes();
        actuals = board.getSquares();
        assertEquals(expecteds, actuals);
    }
    
}