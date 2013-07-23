package game.patterns;

import game.patterns.Tetromino;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
public class TS extends Tetromino {

    private final Direction direction;
    private final PointingDirection pointingDirection;
    
    public TS(PointingDirection pointingDirection, Direction direction) {
        this.pointingDirection = pointingDirection;
        this.direction = direction;
    }
    
    @Override
    protected boolean findPatternMatch(int x, int y, SquaresMatrix squares) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
