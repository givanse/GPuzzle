package game.patterns;

import game.pieces.Square;
import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
public class SSquare extends Shape {
    
    /**
     * 
     * @param x
     * @param y
     * @param squares
     * @return 
     */
    @Override
    boolean findPatternMatch(int x, int y, SquaresMatrix squares) {
        SquareType squareType = squares.getSquare(x, y).getSquareType();
        boolean isMatch = true;
        upwardLeft:
        for(int i = x; i > x-2; i--)
            for(int j = y; j > y-2; j--)
                if(!super.isMatchingSquare(i, j, squareType, squares)) {
                    isMatch =  false;
                    break upwardLeft;
                }
        if(isMatch) return true; else isMatch = true;

        upwardRight:
        for(int i = x; i < x+2; i++)
            for(int j = y; j > y-2; j--)
                if(!super.isMatchingSquare(i, j, squareType, squares)) {
                    isMatch = false;
                    break upwardRight;
                }
        if(isMatch) return true; else isMatch = true;

        downwardLeft:
        for(int i = x; i > x-2; i--)
            for(int j = y; j < y+2; j++)
                if(!super.isMatchingSquare(i, j, squareType, squares)) {
                    isMatch = false;
                    break downwardLeft;
                }
        if(isMatch) return true;

        /*  last chance */
        for(int i = x; i < x+2; i++)
            for(int j = y; j < y+2; j++)
                if(!super.isMatchingSquare(i, j, squareType, squares))
                    return false;
        return true;
    }
}