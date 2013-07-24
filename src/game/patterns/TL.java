package game.patterns;

import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
class TL extends Tetromino {
    
    private boolean fpmRightHorizontal(int x, int y,
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmRightVertical(int x, int y,
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmRightHorizontalFlipped(int x, int y, 
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmRightVerticalFlipped(int x, int y,
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmLeftHorizontal(int x, int y,
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmLeftVertical(int x, int y, 
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmLeftHorizontalFlipped(int x, int y,
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    private boolean fpmLeftVerticalFlipped(int x, int y,
                                SquareType squareType, SquaresMatrix squares) {
        return false;
    }
    
    /* Public methods */
    
    @Override
    protected int[][] findPatternMatch(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        this.fpmRightHorizontal(x, y, squareType, squares);
        this.fpmRightHorizontalFlipped(x, y, squareType, squares);
        this.fpmRightVertical(x, y, squareType, squares);
        this.fpmRightVerticalFlipped(x, y, squareType, squares);
        this.fpmLeftHorizontal(x, y, squareType, squares);
        this.fpmLeftHorizontalFlipped(x, y, squareType, squares);
        this.fpmLeftVertical(x, y, squareType, squares);
        this.fpmLeftVerticalFlipped(x, y, squareType, squares);
        return new int[0][0];
    }
}
