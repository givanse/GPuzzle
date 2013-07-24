package game.patterns;

import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
class TS extends Tetromino {
    
    private int[][] fpmRightHorizontal(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        /**
         *  _ _ _
         * | |_| |
         * |_ _ _|
         */
        return new int[0][0];
    }
    
    private int[][] fpmRightVertical(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        return new int[0][0];
    }
    
    private int[][] fpmLeftHorizontal(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        return new int[0][0];
    }
    
    private int[][] fpmLeftVertical(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        return new int[0][0];
    }
    
    /* Public methods */
    
    @Override
    protected int[][] findPatternMatch(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        int result[][];
        
        result = this.fpmRightHorizontal(x, y, squareType, squares);
        if(result.length > 0) return result;
        
        result = this.fpmRightVertical(x, y, squareType, squares);
        if(result.length > 0) return result;
        
        result = this.fpmLeftHorizontal(x, y, squareType, squares);
        if(result.length > 0) return result;
        
        result = this.fpmLeftVertical(x, y, squareType, squares);
        if(result.length > 0) return result;
        
        return new int[0][0];
    }
    
}
