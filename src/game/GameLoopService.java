package game;

import gui.model.GameModel;
import gui.model.GameModel.GameState;

/**
 *
 * @author givanse
 */
public class GameLoopService implements Runnable {
    
    private Thread gameThread;
    private GameModel gameModel;
        
    public GameLoopService(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    
    /* Public methods */
    
    @Override
    public void run() {
        while(true) {
            if(this.gameModel.getGameState() == GameState.RUNNING) {
                /*TODO: redesign the way the timers are used. */
                this.gameModel.updateGameState();
                try {
                    Thread.sleep(this.gameModel.getSpeed());
                } catch (InterruptedException ex) { }
                System.err.println("game service running");
            } else {
                System.err.println("game service not running");
            }
        }
    }
    
    public void start() {
        if (this.gameThread == null) {
            this.gameModel.setGameState(GameState.RUNNING);
            this.gameThread = new Thread(this, "GameServiceLoop");
            this.gameThread.start();
        }
    }
    
}
