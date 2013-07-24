package game.patterns;

import game.pieces.Square.SquareType;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
class TSquare extends Tetromino {
    
    /**
     * 
     * @param x
     * @param y
     * @param squares
     * @return 
     */
    @Override
    protected int[][] findPatternMatch(int x, int y, SquareType squareType,
                                                     SquaresMatrix squares) {
        /**
         *  _ _
         * |  _|
         * |_|_|
         */
        boolean match = true;
        match = match && Tetromino.isSquareMatch(x-1, y, squareType, squares);
        match = match && Tetromino.isSquareMatch(x, y-1, squareType, squares);
        match = match && Tetromino.isSquareMatch(x-1, y-1, squareType, squares);
        if(match)
            return new int[][]{{x, y}, {x, y-1}, {x-1, y}, {x-1, y-1}};
        
        /**
         *  _ _
         * |_  |
         * |_|_|
         */
        match = true;
        match = match && Tetromino.isSquareMatch(x, y+1, squareType, squares);
        match = match && Tetromino.isSquareMatch(x-1, y, squareType, squares);
        match = match && Tetromino.isSquareMatch(x-1, y+1, squareType, squares);
        if(match)
            return new int[][]{{x, y}, {x, y+1}, {x-1, y}, {x-1, y+1}};
        
        /**
         *  _ _
         * | |_|
         * |_ _|
         */
        match = true;
        match = match && Tetromino.isSquareMatch(x, y-1, squareType, squares);
        match = match && Tetromino.isSquareMatch(x+1, y, squareType, squares);
        match = match && Tetromino.isSquareMatch(x+1, y-1, squareType, squares);
        if(match) 
            return new int[][]{{x, y}, {x, y-1}, {x+1, y}, {x+1, y-1}};

        /**
         *  _ _
         * |_| |
         * |_ _|
         */
        match = true;
        match = match && Tetromino.isSquareMatch(x, y+1, squareType, squares);
        match = match && Tetromino.isSquareMatch(x+1, y, squareType, squares);
        match = match && Tetromino.isSquareMatch(x+1, y+1, squareType, squares);
        if(match) 
            return new int[][]{{x, y}, {x, y+1}, {x+1, y}, {x+1, y+1}};
        
        return new int[0][0];
    }
}