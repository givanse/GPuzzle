package game;

import gui.model.GameModel;
import gui.model.GameModel.GameState;

/**
 *
 * @author givanse
 */
public class GameService implements Runnable {
    
    private Thread gameThread;
    private GameModel gameModel;
        
    public GameService(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    
    /* Public methods */
    
    @Override
    public void run() {
        while(this.gameModel.getGameState() != GameState.OVER) {  
            System.out.println("running");
            if(this.gameModel.getGameState() == GameState.PAUSED) {
              System.out.println("paused");
              continue;
            }
            
            this.gameModel.updateSquares();
            
            try {
                long oneSec = 1000;
                Thread.sleep(this.gameModel.getSpeed());
            } catch (InterruptedException ex) { }
        }
        System.exit(0);
    }
    
    public void start() {
        if (this.gameThread == null || 
            this.gameModel.getGameState() == GameState.OVER) {
            this.gameModel.setGameState(GameState.RUNNING);
            this.gameThread = new Thread(this);
            this.gameThread.start();
        }
    }
    
}
