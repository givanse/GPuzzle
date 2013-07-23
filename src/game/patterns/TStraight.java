package game.patterns;

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
    
    private boolean findPatternMatchH(int x, int y, SquareType squareType, 
                                                   SquaresMatrix squares) {
        for(int i = 0; i <= 3; i++)
            for(int j = 3; j >= 0; j--)
                if(this.testHorizontalLine(i, j, x, y, squareType, squares))
                    return true;
        return false;
    }
    
    private boolean findPatternMatchV(int x, int y, SquareType squareType,
                                                   SquaresMatrix squares) {
        for(int i = 0; i <= 3; i++)
            for(int j = 3; j >= 0; j--)
                if(this.testVerticalLine(i, j, x, y, squareType, squares))
                    return true;
        return false;
    }
    
    @Override
    protected boolean findPatternMatch(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        switch(this.direction) {
            case HORIZONTAL:
                return findPatternMatchH(x, y, squareType, squares);
            case VERTICAL:
                return findPatternMatchV(x, y, squareType, squares);
            default:
                throw new Error("An invalid Direction was used.");
        }
    }
    
}
