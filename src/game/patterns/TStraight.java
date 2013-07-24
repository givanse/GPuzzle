package game.patterns;

import game.pieces.Square.SquareColour;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
class TStraight extends Tetromino {

    private boolean findPatternMatchH(int x, int y, SquareColour squareType, 
                                                   SquaresMatrix squares) {
        for(int i = 0; i <= 3; i++)
            for(int j = 3; j >= 0; j--)
                if(Tetromino.isHorizontalMatch(i, j, x, y, squareType, squares))
                    return true;
        return false;
    }
    
    private boolean findPatternMatchV(int x, int y, SquareColour squareType,
                                                   SquaresMatrix squares) {
        for(int i = 0; i <= 3; i++)
            for(int j = 3; j >= 0; j--)
                if(Tetromino.isVerticalMatch(i, j, x, y, squareType, squares))
                    return true;
        return false;
    }
    
    /* Public methods */
    
    @Override
    protected int[][] findPatternMatch(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        this.findPatternMatchH(x, y, squareType, squares);
        this.findPatternMatchV(x, y, squareType, squares);
        return new int[0][0];
    }
    
}
