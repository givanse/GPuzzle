package gui.control;

import gui.model.GameModel;
import javax.swing.JTextField;

/**
 *
 * @author givanse
 */
public class TimeController {
    
    public TimeController(final JTextField txtFieldTime, 
                           final GameModel gameModel) {
        
        GameListener gameListener = new GameAdapter() {
            @Override
            public void timeChanged(long time){
                long timeInSeconds = time / 1000;
                txtFieldTime.setText(Long.toString(timeInSeconds) + "s");
            }
        };
        
        gameModel.addListener(gameListener);
        gameModel.setTime(0);
    }
    
}
