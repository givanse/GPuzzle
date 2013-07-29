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
        while(this.gameModel.getGameState() != GameState.OVER) {
            if(this.gameModel.getGameState() == GameState.PAUSED) {
              continue;
            }

            this.gameModel.updateSquares();

            try {
                Thread.sleep(this.gameModel.getSpeed());
            } catch (InterruptedException ex) { }
        }
    }
    
    public void start() {
        if (this.gameThread == null || 
            this.gameModel.getGameState() == GameState.OVER) {
            this.gameModel.setGameState(GameState.RUNNING);
            this.gameThread = new Thread(this, "GameServiceLoop");
            this.gameThread.start();
        }
    }
    
}
