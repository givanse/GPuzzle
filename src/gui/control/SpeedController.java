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
            public void speedChanged(long speed){
                txtFieldSpeed.setText(Long.toString(speed));
            }
        };
        gameModel.addListener(gameListener);
        gameModel.setSpeed(GameModel.FALLING_SPEED_INITIAL);
    }
    
}
