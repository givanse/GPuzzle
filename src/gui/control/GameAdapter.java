package gui.control;

import game.pieces.Square;
import gui.model.GameModel;
import java.util.ArrayList;

/**
 *
 * @author givanse
 */
public class GameAdapter implements GameListener {
    
    @Override
    public void scoreChanged(int score) { }
    
    @Override
    public void speedChanged(long speed) { }
    
    @Override
    public void gameStateChanged(GameModel.GameState gameState) { }
    
    @Override
    public void squaresChanged(Square boardSquares[][], 
                               ArrayList<Square> fallingSquares) { }
    
}
