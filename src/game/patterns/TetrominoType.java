package game.patterns;

import game.pieces.SquaresMatrix;

/**
 * 
 * @author givanse
 */
public enum TetrominoType { 
    SQUARE(new TSquare()),
    STRAIGHT(new TStraight()), 
    S(new TS()), 
    L(new TL());
    
    private Tetromino tetromino;

    TetrominoType(Tetromino tetromino) {
        this.tetromino = tetromino;
    }
    
    protected int[][] isPatternFound(int x, int y, SquaresMatrix squares) {
        return this.tetromino.isPatternFound(x, y, squares);
    }
}
