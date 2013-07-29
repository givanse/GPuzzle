package game.patterns;

import game.pieces.Square.SquareColour;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
class TSShape extends Tetromino {
    
    private int[][] fpmRightHorizontal(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        /**
         *  _ _ _
         * |   |_|
         * |_ _ _|
         */
        if(Tetromino.isSquareMatch(x-1, y, squareType, squares))
          if(Tetromino.isSquareMatch(x-1, y+1, squareType, squares))
            if(Tetromino.isSquareMatch(x-2, y+1, squareType, squares))
              return new int[][]{{x, y}, {x-1, y}, {x-1, y+1}, {x-2, y+1}};
        /**
         *  _ _ _
         * | |_| |
         * |_ _ _|
         */
        if(Tetromino.isSquareMatch(x+1, y, squareType, squares))
          if(Tetromino.isSquareMatch(x, y+1, squareType, squares))
            if(Tetromino.isSquareMatch(x-1, y+1, squareType, squares))
              return new int[][]{{x, y}, {x+1, y}, {x, y+1}, {x-1, y+1}};
        /**
         *  _ _ _
         * |  _  |
         * |_|_|_|
         */
        if(Tetromino.isSquareMatch(x-1, y, squareType, squares))
          if(Tetromino.isSquareMatch(x, y-1, squareType, squares))
            if(Tetromino.isSquareMatch(x+1, y-1, squareType, squares))
              return new int[][]{{x, y}, {x-1, y}, {x, y-1}, {x+1, y-1}};
        /**
         *  _ _ _
         * |_    |
         * |_|_ _|
         */
        if(Tetromino.isSquareMatch(x+1, y, squareType, squares))
          if(Tetromino.isSquareMatch(x+1, y-1, squareType, squares))
            if(Tetromino.isSquareMatch(x+2, y-1, squareType, squares))
              return new int[][]{{x, y}, {x+1, y}, {x+1, y-1}, {x+2, y-1}};
        
        return new int[0][0];
    }
    
    private int[][] fpmRightVertical(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        return new int[0][0];
    }
    
    private int[][] fpmLeftHorizontal(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        return new int[0][0];
    }
    
    private int[][] fpmLeftVertical(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        return new int[0][0];
    }
    
    /* Public methods */
    
    @Override
    protected int[][] findPatternMatch(int x, int y, SquareColour squareType,
                                                     SquaresMatrix squares) {
        int result[][];
        
        result = this.fpmRightHorizontal(x, y, squareType, squares);
        if(result.length > 0) 
            return result;
        
        result = this.fpmRightVertical(x, y, squareType, squares);
        if(result.length > 0) 
            return result;
        
        result = this.fpmLeftHorizontal(x, y, squareType, squares);
        if(result.length > 0) 
            return result;
        
        result = this.fpmLeftVertical(x, y, squareType, squares);
        if(result.length > 0) 
            return result;
        
        return result;
    }
    
}
