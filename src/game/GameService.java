package game;

import game.pieces.Board;
import game.pieces.Square;
import game.pieces.Square.SquareColour;
import gui.view.BoardPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

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
                int xPixels = (s.getX() * Square.SIZE) - 
                              BoardPanel.SPACE_FILLED_BY_BORDERS;
                int yPixels = (s.getY() * Square.SIZE) - 
                              BoardPanel.SPACE_FILLED_BY_BORDERS;
                graphics.drawImage(s.getSquareColour().getImage(), 
                                   xPixels, yPixels, this.boardPanel);
            }
        }
        
        /* Draw falling squares. */
        for(Square s : this.board.getFallingSquares()) {
            graphics.drawImage(s.getSquareColour().getImage(), 
                               s.getX(), s.getY(), this.boardPanel);
        }
    }
}
