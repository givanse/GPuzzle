package game.pieces;

import java.awt.event.ActionListener;

/**
 *
 * @author givanse
 */
public class PairsTimer extends javax.swing.Timer {
    
    /**
     * The speed of the game is increased every two minutes.
     * 2 minutes = 2 * 60 * 1000 = 120,000 milliseconds
     */
    private static int INCREASE_SPEED_INTERVAL = 120000;
    
    /**
     * Speed measured in pairs per second. In this case we start at one pair 
     * every two seconds.
     * 2 seconds = 2 * 1000 = 2000 milliseconds 
     */
    private static int INITIAL_SPEED = 2000;
    
    /* 100 milliseconds */
    private static int SPEED_INCREMENT = 100;
    
    private int currentSpeed = PairsTimer.INITIAL_SPEED;
    
    public PairsTimer(int timer, ActionListener taskPerformer) {
        super(timer, taskPerformer);
    }
    
    private void increaseSpeed() {
    }
}
