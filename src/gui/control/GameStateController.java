package gui.control;

import gui.model.GameModel;
import javax.swing.JTextArea;

/**
 *
 * @author givanse
 */
public class GameStateController {

    public GameStateController(final JTextArea textArea, GameModel gameModel) {
        GameListener gameListener = new GameAdapter() {
            @Override
            public void gameStateChanged(GameModel.GameState gameState) { 
                textArea.append(gameState + "\n");
            }
        };
        
        gameModel.addListener(gameListener);
    }
    
}
