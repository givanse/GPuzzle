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
        long currentTime = System.nanoTime();
        while(this.gameModel.getGameState() != GameState.OVER) {  
            System.out.println("running");
            if(this.gameModel.getGameState() == GameState.PAUSED) {
              System.out.println("paused");
              continue;
            }
            
            long initialTime = System.nanoTime();
            long elapsedTime = System.nanoTime() - currentTime;
            currentTime += elapsedTime;
            this.gameModel.updateSquares();
            int nano = 1000000;
            int totalElapsedTime = 
                                 (int) (System.nanoTime() - initialTime) / nano;
            /* Wait a little bit for the falling squares animation. */
            try {
                long sleepTime = 1000;
                Thread.sleep(totalElapsedTime > 0 ? 
                             sleepTime - totalElapsedTime :
                             sleepTime);
            } catch (InterruptedException ex) { }
        }
        System.exit(0);
    }
    
    public void start() {
        if (this.gameThread == null || 
            this.gameModel.getGameState() == GameState.OVER) {
            this.gameThread = new Thread(this);
            this.gameThread.start();
        }
    }
    
}
