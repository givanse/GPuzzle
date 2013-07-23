package game.patterns;

import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 * The parent/interface for all the different Tetris shapes
 * 
 * @author givanse
 */
public abstract class Tetromino {
    
    protected enum Direction{HORIZONTAL, VERTICAL};
    
    protected enum PointingDirection {RIGHT, LEFT};
    
    protected enum LPosition {REGULAR, FLIPPED};
    
    protected abstract boolean findPatternMatch(int x, int y, 
                                  SquareType squareType, SquaresMatrix squares);
    
    protected final boolean testHorizontalLine(
                                 int nBackward, int nForward, int x, int y,
                                 SquareType squareType, SquaresMatrix squares) {
        /* Backward */
        for(int i = x; i > x - nBackward; i--)
            if(!this.isMatchingSquare(i, y, squareType, squares))
                return false;
        /* Forward */
        for(int i = x; i < x + nForward; i++)
            if(!this.isMatchingSquare(i, y, squareType, squares))
                return false;
        return true;
    }
    
    protected final boolean testVerticalLine(
                                 int nBackward, int nForward, int x, int y,
                                 SquareType squareType, SquaresMatrix squares) {
        /* Backward */
        for(int i = y; i > y - nBackward; i--)
            if(!this.isMatchingSquare(x, i, squareType, squares))
                return false;
        /* Forward */
        for(int i = y; i < y + nForward; i++)
            if(!this.isMatchingSquare(x, i, squareType, squares))
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
    protected final boolean isMatchingSquare(int x, int y, SquareType squareType, 
                                                     SquaresMatrix squares) {
        if(squares.isPositionAvailable(x, y) == false)
            return false;
        if(squares.getSquare(x, y).getSquareType() != squareType)
            return false;
        return true;
    }
    
    /* Public methods */
        
    /**
     *
     * @param x
     * @param y
     * @param squares
     * @return true if a match was found, false otherwise
     * @throws Exception if this method is not override.
     */
    public boolean isPatternFound(int x, int y, SquaresMatrix squares) {
        if(squares.isPositionAvailable(x, y) == false)
            return false;
        SquareType squareType = squares.getSquare(x, y).getSquareType();
        return this.findPatternMatch(x, y, squareType, squares);
    }

}