package game.patterns;

import game.pieces.Square;
import game.pieces.SquaresMatrix;

/**
 * The parent/interface for all the different Tetris shapes
 * 
 * @author givanse
 */
public abstract class Shape {
        
    abstract boolean findPatternMatch(int x, int y, SquaresMatrix squares);
    
    /* Public methods */
        
    /**
     *
     * @param x
     * @param y
     * @param squares
     * @return true if a match was found, false otherwise
     * @throws Exception if this method is not override.
     */
    public boolean isShapeFound(int x, int y, SquaresMatrix squares) {
        if(squares.isPositionAvailable(x, y) == false)
            return false;
        return this.findPatternMatch(x, y, squares);
    }

}