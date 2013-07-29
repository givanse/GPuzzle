package gui.control;

import gui.model.GameModel;
import javax.swing.JTextField;

/**
 *
 * @author givanse
 */
public class SpeedController {
    
    public SpeedController(final JTextField txtFieldSpeed, 
                           final GameModel gameModel) {
        
        GameListener gameListener = new GameAdapter() {
            @Override
            public void speedChanged(int score){
                txtFieldSpeed.setText(Integer.toString(score));
            }
        };
        
        gameModel.addListener(gameListener);
    }
    
}
