package game.patterns;

import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 * The parent/interface for all the different tetrominos, also known as
 * Tetris shapes.
 * 
 * @author givanse
 */
public abstract class Tetromino {
    
    protected enum Direction{HORIZONTAL, VERTICAL};
    
    protected enum PointingDirection {RIGHT, LEFT};
    
    /**
     * Regular Ls:
     *   []          []
     *   [][][]  [][][]
     * Flipped Ls:
     *   [][][]  [][][]
     *   []          []
     */
    protected enum LPosition {REGULAR, FLIPPED};
    
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
    protected abstract boolean findPatternMatch(int x, int y, 
                                  SquareType squareType, SquaresMatrix squares);
    
    protected final boolean isHorizontalMatch(
                                 int nBackward, int nForward, int x, int y,
                                 SquareType squareType, SquaresMatrix squares) {
        /* Backward */
        for(int i = x; i > x - nBackward; i--)
            if(!this.isSquareMatch(i, y, squareType, squares))
                return false;
        /* Forward */
        for(int i = x; i < x + nForward; i++)
            if(!this.isSquareMatch(i, y, squareType, squares))
                return false;
        return true;
    }
    
    protected final boolean isVerticalMatch(
                                 int nBackward, int nForward, int x, int y,
                                 SquareType squareType, SquaresMatrix squares) {
        /* Backward */
        for(int i = y; i > y - nBackward; i--)
            if(!this.isSquareMatch(x, i, squareType, squares))
                return false;
        /* Forward */
        for(int i = y; i < y + nForward; i++)
            if(!this.isSquareMatch(x, i, squareType, squares))
                return false;
        return true;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param squareType
     * @param squares
     * @return True  if the position holds a Square that has the same SquareType
     *         False otherwise
     */
    protected final boolean isSquareMatch(int x, int y, SquareType squareType, 
                                                     SquaresMatrix squares) {
        if(squares.isPositionAvailable(x, y) == false)
            return false;
        if(squares.getSquare(x, y).getSquareType() != squareType)
            return false;
        return true;
    }
    
    /* Public methods */
        
    /**
     * Tests if the pattern of this tetromino is found within the squares 
     * adjacent to the one indicated by the (x, y) coordinates.
     * 
     * @param x
     * @param y
     * @param squares
     * @return true if a match was found, false otherwise
     */
    public boolean isPatternFound(int x, int y, SquaresMatrix squares) {
        if(squares.isPositionAvailable(x, y) == false)
            return false;
        SquareType squareType = squares.getSquare(x, y).getSquareType();
        return this.findPatternMatch(x, y, squareType, squares);
    }

}