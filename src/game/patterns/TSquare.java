package game.patterns;

import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
public class TSquare extends Tetromino {
    
    /**
     * 
     * @param x
     * @param y
     * @param squares
     * @return 
     */
    @Override
    protected boolean findPatternMatch(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        /**
         *  _ _
         * |  _|
         * |_|_|
         */
        boolean match = true;
        match = match && super.isMatchingSquare(x-1, y, squareType, squares);
        match = match && super.isMatchingSquare(x, y-1, squareType, squares);
        match = match && super.isMatchingSquare(x-1, y-1, squareType, squares);
        if(match) return true;
        
        /**
         *  _ _
         * |_  |
         * |_|_|
         */
        match = true;
        match = match && super.isMatchingSquare(x+1, y, squareType, squares);
        match = match && super.isMatchingSquare(x, y-1, squareType, squares);
        match = match && super.isMatchingSquare(x+1, y-1, squareType, squares);
        if(match) return true;
        
        /**
         *  _ _
         * | |_|
         * |_ _|
         */
        match = true;
        match = match && super.isMatchingSquare(x-1, y, squareType, squares);
        match = match && super.isMatchingSquare(x, y+1, squareType, squares);
        match = match && super.isMatchingSquare(x-1, y+1, squareType, squares);
        if(match) return true;

        /**
         *  _ _
         * |_| |
         * |_ _|
         */
        match = true;
        match = match && super.isMatchingSquare(x+1, y, squareType, squares);
        match = match && super.isMatchingSquare(x, y+1, squareType, squares);
        match = match && super.isMatchingSquare(x+1, y+1, squareType, squares);
        return match;
    }
}