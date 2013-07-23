package game.patterns;

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
        boolean isMatch = true;
        upwardLeft:
        for(int i = x; i > x-2; i--)
            for(int j = y; j > y-2; j--)
                if(squares.isPositionAvailable(i, j) == false) {
                    isMatch =  false;
                    break upwardLeft;
                }
        if(isMatch) return true; else isMatch = true;

        upwardRight:
        for(int i = x; i < x+2; i++)
            for(int j = y; j > y-2; j--)
                if(squares.isPositionAvailable(i, j) == false) {
                    isMatch = false;
                    break upwardRight;
                }
        if(isMatch) return true; else isMatch = true;

        downwardLeft:
        for(int i = x; i > x-2; i--)
            for(int j = y; j < y+2; j++)
                if(squares.isPositionAvailable(i, j) == false) {
                    isMatch = false;
                    break downwardLeft;
                }
        if(isMatch) return true;

        /*  last chance */
        for(int i = x; i < x+2; i++)
            for(int j = y; j < y+2; j++)
                if(squares.isPositionAvailable(i, j) == false)
                    return false;
        return true;
    }
}