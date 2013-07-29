package game;

import game.pieces.Board;
import game.pieces.Square;
import gui.view.BoardPanel;
import java.awt.Graphics;

/**
 *
 * @author givanse
 */
public class GameService implements Runnable {

    private enum State {RUNNING, PAUSED, OVER};
    
    private State gameState = State.OVER;
    
    private Thread gameThread;
    
    private BoardPanel boardPanel;
    
    private Board board;
        
    public GameService(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.board = new Board();
    }
    
    private void updateObjects() {
        this.board.update();
    }
    
    private void drawSquare(Graphics graphics, Square square) {
        int xPixels = (square.getX() * Square.SIZE);
        int yPixels = (square.getY() * Square.SIZE);
        graphics.drawImage(square.getSquareColour().getImage(), 
                           xPixels, yPixels, this.boardPanel);
    }
    
    /* Public methods */
    
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
              this.boardPanel.drawObjects();
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
    
    public void start() {
        if (this.gameThread == null || this.gameState == State.OVER ) {
            this.gameThread = new Thread(this);
            this.gameThread.start();
        }
    }
    
    public void drawObjects(Graphics graphics) {
        /* Draw board squares. */
        for(Square[] row : this.board.getSquares()) {
            for(Square s : row) {
                if(s == null)
                    continue;
                else
                    this.drawSquare(graphics, s);
            }
        }
        
        /* Draw falling squares. */
        for(Square s : this.board.getFallingSquares()) {
            this.drawSquare(graphics, s);
        }
    }
}
