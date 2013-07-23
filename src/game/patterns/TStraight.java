package game.patterns;

import game.pieces.Square;
import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
public class TStraight extends Tetromino {

    private final Direction direction;
    
    public TStraight(Direction direction) {
        this.direction = direction;
    }
    
    private boolean findPatternMathH(int x, int y, SquareType squareType, 
                                                   SquaresMatrix squares) {
        
        
        return false;
    }
    
    private boolean findPatternMathV(int x, int y, SquareType squareType,
                                                   SquaresMatrix squares) {
        return true;
    }
    
    @Override
    protected boolean findPatternMatch(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        switch(this.direction) {
            case HORIZONTAL:
                return findPatternMathH(x, y, squareType, squares);
            case VERTICAL:
                return findPatternMathV(x, y, squareType, squares);
            default:
                throw new Error("An invalid Direction was used.");
        }
    }
    
}
