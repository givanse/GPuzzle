package gui.control;

import gui.model.GameModel;
import javax.swing.JTextField;

/**
 *
 * @author givanse
 */
public class ScoreController {
    
    public ScoreController(final JTextField txtFieldScore, 
                           final GameModel gameModel) {
        
        GameListener gameListener = new GameAdapter() {
            @Override
            public void scoreChanged(int score){
                txtFieldScore.setText(Integer.toString(score));
            }
        };
        
        gameModel.addListener(gameListener);
    }
    
}
