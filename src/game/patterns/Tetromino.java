package game.patterns;

import game.pieces.Square.SquareColour;
import game.pieces.SquaresMatrix;

/**
 * The parent/interface for all the different tetrominos, also known as
 * Tetris shapes.
 * 
 * @author givanse
 */
public abstract class Tetromino {
    
    /**
     * Each tetromino (TObject) overwrites this method and implements its own
     * rules for pattern matching.
     * 
     * @param x
     * @param y
     * @param squareType
     * @param squares
     * @return 
     */
    protected abstract int[][] findPatternMatch(int x, int y, 
                                SquareColour squareType, SquaresMatrix squares);
    
    protected static boolean isHorizontalMatch(
                               int nBackward, int nForward, int x, int y,
                               SquareColour squareType, SquaresMatrix squares) {
        /* Backward */
        for(int i = x; i > x - nBackward; i--)
            if(!Tetromino.isSquareMatch(i, y, squareType, squares))
                return false;
        /* Forward */
        for(int i = x; i < x + nForward; i++)
            if(!Tetromino.isSquareMatch(i, y, squareType, squares))
                return false;
        return true;
    }
    
    protected static boolean isVerticalMatch(
                               int nBackward, int nForward, int x, int y,
                               SquareColour squareType, SquaresMatrix squares) {
        /* Backward */
        for(int i = y; i > y - nBackward; i--)
            if(!Tetromino.isSquareMatch(x, i, squareType, squares))
                return false;
        /* Forward */
        for(int i = y; i < y + nForward; i++)
            if(!Tetromino.isSquareMatch(x, i, squareType, squares))
                return false;
        return true;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param squareType
     * @param squares
     * @return True if the position holds a Square that has the same 
     *         SquareColour, False otherwise.
     */
    protected static boolean isSquareMatch(int x, int y, 
                                           SquareColour squareType, 
                                           SquaresMatrix squares) {
        if(squares.isPositionAvailable(x, y))
            return false;
        if(squares.getSquare(x, y).getSquareColour() != squareType)
            return false;
        return true;
    }
    
    /**
     * Tests if the pattern of this tetromino is found within the squares 
     * adjacent to the one indicated by the (x, y) coordinates.
     * 
     * @param x
     * @param y
     * @param squares
     * @return true if a match was found, false otherwise
     */
    protected int[][] isPatternFound(int x, int y, SquaresMatrix squares) {
         /* Its empty, no pattern can be found. */
        if(squares.isPositionAvailable(x, y))
            return new int[0][0];
        SquareColour squareType = squares.getSquare(x, y).getSquareColour();
        return this.findPatternMatch(x, y, squareType, squares);
    }

    
    /* Public methods */

    public static int[][] performPatternMatching(int x, int y, 
                                                 SquaresMatrix squares) {
        return TetrominoType.SQUARE.isPatternFound(x, y, squares);
    }
}