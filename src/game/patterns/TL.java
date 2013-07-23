package game.patterns;

import game.patterns.Tetromino;
import game.pieces.SquaresMatrix;

/**
 *
 * @author givanse
 */
public class TL extends Tetromino {

    private final Direction direction;
    private final PointingDirection pointingDirection;
    private final LPosition lPosition;
    
    public TL(PointingDirection pointingDirection, Direction direction, 
                                                   LPosition lPosition) {
        this.pointingDirection = pointingDirection;
        this.direction = direction;
        this.lPosition = lPosition;
    }
    
    @Override
    protected boolean findPatternMatch(int x, int y, SquaresMatrix squares) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
