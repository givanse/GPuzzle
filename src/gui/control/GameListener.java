package gui.control;

import game.pieces.Square;
import gui.model.GameModel.GameState;
import java.util.ArrayList;

/**
 *
 * @author givanse
 */
public interface GameListener {
    
    public void scoreChanged(int score);
    
    public void speedChanged(int score);
    
    public void gameStateChanged(GameState gameState);
    
    public void squaresChanged(Square boardSquares[][], 
                               ArrayList<Square> fallingSquares);
        
}
