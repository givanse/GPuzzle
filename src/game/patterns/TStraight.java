package game.patterns;

import game.pieces.Square.SquareColour;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
class TStraight extends Tetromino {

    private int[][] findPatternMatchH(int x, int y, SquareColour squareType, 
                                                   SquaresMatrix squares) {
        int result[][] = new int[0][0];
        for(int i = 0; i <= 3; i++)
            for(int j = 3; j >= 0; j--) {
                result = Tetromino.isHorizontalMatch(i, j, x, y, 
                                                     squareType, squares);
                if(result.length == 4)
                    return result;
            }
        return result;
    }
    
    private int[][] findPatternMatchV(int x, int y, SquareColour squareType,
                                                   SquaresMatrix squares) {
        int result[][] = new int[0][0];
        for(int i = 0; i <= 3; i++)
            for(int j = 3; j >= 0; j--) {
                result = Tetromino.isVerticalMatch(i, j, x, y, 
                                                   squareType, squares);
                if(result.length == 4)
                    return result;
            }
        return result;
    }
    
    /* Public methods */
    
    @Override
    protected int[][] findPatternMatch(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        int result[][];
        
        result = this.findPatternMatchH(x, y, squareType, squares);
        if(result.length > 0) 
            return result;
        
        result = this.findPatternMatchV(x, y, squareType, squares);
        if(result.length > 0) 
            return result;
        
        return result;
    }
    
}
