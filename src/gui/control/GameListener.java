package gui.control;

import game.pieces.Square;
import gui.model.GameModel.GameState;
import gui.view.SmartCursor;
import java.util.ArrayList;

/**
 *
 * @author givanse
 */
public interface GameListener {
    
    public void scoreChanged(int score);
    
    public void speedChanged(long speed);
    
    public void timeChanged(long time);
    
    public void gameStateChanged(GameState gameState);
    
    public void squaresChanged(Square boardSquares[][], 
                               ArrayList<Square> fallingSquares,
                               SmartCursor smartCursor);
        
}
