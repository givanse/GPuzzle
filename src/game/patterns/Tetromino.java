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
    
    protected abstract 
              boolean findPatternMatch(int x, int y, SquaresMatrix squares);
    
    /**
     * 
     * @param x
     * @param y
     * @param squareType
     * @param squares
     * @return True  if the position holds a Square that has the same SquareType
     *         False otherwise
     */
    protected boolean isMatchingSquare(int x, int y, SquareType squareType, 
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
        return this.findPatternMatch(x, y, squares);
    }

}