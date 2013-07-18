package game;

/**
 *
 * @author givanse
 */
public class GameService implements Runnable {

    private enum State {RUNNING, PAUSED, OVER};
    
    private State gameState = State.OVER;
    
    private Thread gameThread;
    
    private view.Board viewBoard;
    
    private game.pieces.Board gameBoard;
        
    public GameService(view.Board gameBoard) {
        this.viewBoard = gameBoard;
    }
    
    private void updateObjects() {
        this.gameBoard.update();
    }
        
    /* Public methods */
        
    public void start() {
        if (this.gameThread == null || this.gameState == State.OVER ) {
            this.gameThread = new Thread(this);
            this.gameThread.start();
        }
    }
    
    @Override
    public void run() {
        this.gameState = State.RUNNING;
        long currentTime = System.nanoTime();
        while( this.gameState == State.RUNNING ) {  
            long initialTime = System.nanoTime();
            long elapsedTime = System.nanoTime() - currentTime;
            currentTime += elapsedTime;
            this.updateObjects();
            if(false) // if(gamePaused || gameOver)
              continue;
            else
              this.viewBoard.drawObjects();
            int nano = 1000000;
            int totalElapsedTime = 
                                 (int) (System.nanoTime() - initialTime) / nano;
            /* wait a bit for the animation */
            try {
                Thread.sleep(totalElapsedTime > 0 ? 17 - totalElapsedTime : 4);
            } catch (Exception e) { }
        }
        System.exit(0);
    }
}
