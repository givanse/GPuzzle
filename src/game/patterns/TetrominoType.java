package game.patterns;

import game.patterns.Tetromino.Direction;
import game.patterns.Tetromino.LPosition;
import game.patterns.Tetromino.PointingDirection;
import game.pieces.SquaresMatrix;

/**
 * The last letter of each tetromino type, _H or _V, stands for 
 * Horizontal or Vertical.
 * 
 * The suffix _F for the L shapes stands for Flipped.
 * 
 * Note that the shape T, by game design, is not included.
 * 
 * @author givanse
 */
public enum TetrominoType {      
    SQUARE(new TSquare()),
    STRAIGHT_H(new TStraight(Direction.HORIZONTAL)), 
    STRAIGHT_V(new TStraight(Direction.HORIZONTAL)),
    /* S shapes */
    S_RIGHT_H(new TS(PointingDirection.RIGHT, Direction.HORIZONTAL)), 
    S_RIGHT_V(new TS(PointingDirection.RIGHT, Direction.HORIZONTAL)), 
    S_LEFT_H(new TS(PointingDirection.LEFT, Direction.VERTICAL)), 
    S_LEFT_V(new TS(PointingDirection.LEFT, Direction.VERTICAL)),
    /* L shapes */
    /* right */
    L_RIGHT_H(
     new TL(PointingDirection.RIGHT, Direction.HORIZONTAL, LPosition.REGULAR)), 
    L_RIGHT_V(
     new TL(PointingDirection.RIGHT, Direction.VERTICAL, LPosition.REGULAR)),
    L_RIGHT_H_F(
     new TL(PointingDirection.RIGHT, Direction.HORIZONTAL, LPosition.FLIPPED)),
    L_RIGHT_V_F(
     new TL(PointingDirection.RIGHT, Direction.VERTICAL, LPosition.FLIPPED)),
    /* left */
    L_LEFT_H(
     new TL(PointingDirection.LEFT, Direction.HORIZONTAL, LPosition.REGULAR)),
    L_LEFT_V(
     new TL(PointingDirection.LEFT, Direction.VERTICAL, LPosition.REGULAR)),
    L_LEFT_H_F(
     new TL(PointingDirection.LEFT, Direction.HORIZONTAL, LPosition.FLIPPED)),
    L_LEFT_V_F(
     new TL(PointingDirection.LEFT, Direction.VERTICAL, LPosition.FLIPPED));

    private Tetromino tetromino;

    TetrominoType(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    public boolean isPatternFound(int x, int y, SquaresMatrix squares) {
        return this.tetromino.isPatternFound(x, y, squares);
    }
}
