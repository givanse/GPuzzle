package gui.control;

import game.Utility;
import game.pieces.Square;
import gui.model.GameModel;
import gui.view.BoardView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

/**
 *
 * @author givanse
 */
public class BoardViewMouseListener extends MouseAdapter {

    private BoardView boardView;
    private JTextArea logTextArea;
    private GameModel gameModel;
    public enum CursorState {HOLDING, NOT_HOLDING};
    private CursorState IS_CURSOR_HOLDING_A_SQUARE = CursorState.NOT_HOLDING;
    private int xHeldByCursor;
    private int yHeldByCursor;
    
    public BoardViewMouseListener(BoardView boardPanel, JTextArea logTextArea, 
                                  GameModel gameModel) {
        this.boardView = boardPanel;
        this.logTextArea = logTextArea;
        this.gameModel = gameModel;
    }
    
    /**
     * 
     * @param x coordinate of the selected square
     * @param y coordinate of the selected square
     */
    private void holdSquare(int x, int y) {
        this.xHeldByCursor = x;
        this.yHeldByCursor = y;
        Square square = this.gameModel.getSquare(x, y);
        this.boardView.changeCursorToSelectedSquare(square);
    }
    
    /**
     * Swap the square, located by xHeldByCursor and yHeldByCursor, with the
     * selected square.
     * 
     * @param x coordinate of the selected square
     * @param y coordinate of the selected square
     */
    private void swapSquare(int x, int y) {
        boolean swapResult = this.gameModel.swapSquares(
                                  this.xHeldByCursor, this.yHeldByCursor, x, y);
        int delPatternCount1 = 
               this.gameModel.checkAndDeleteCompletedTetrisShape(
                                        this.xHeldByCursor, this.yHeldByCursor);
        int delPatternCount2 = 
                        this.gameModel.checkAndDeleteCompletedTetrisShape(x, y);
        
        this.gameModel.incrementScore(delPatternCount1);
        this.gameModel.incrementScore(delPatternCount2);
     
        this.logTextArea.append("swap completed: " + swapResult + "\n");
        
        /* Either the swap is successful or not, the cursor is changed back. */
        this.xHeldByCursor = -1;
        this.yHeldByCursor = -1;
        this.boardView.changeCursorToSightIcon();
    }
    
    private void handleSelectedBoardViewPosition(int x, int y) {
        /* Is it an empty space? */
        if(this.gameModel.isPositionAvailable(x, y)) {
            this.logTextArea.append("empty position\n");
            return;
        }
        
        /**
         * Is it a falling square? We don't need to validate that because
         * those squares are invisible from here. They are stored in different 
         * data structure (Board.getFallingSquares()).
         */
        
        /* Warning: hardcoded switch. */
        boolean swapSquares = false;
        if(swapSquares)
            this.handleAsASwap(x, y);
        else
            this.handleAsADelete(x, y);
    }
    
    private void handleAsASwap(int x, int y) {
        switch(this.IS_CURSOR_HOLDING_A_SQUARE) {
            case HOLDING:
                this.logTextArea.append("swap square\n");
                this.swapSquare(x, y);
                this.IS_CURSOR_HOLDING_A_SQUARE = CursorState.NOT_HOLDING;
                break;
            case NOT_HOLDING:
                this.logTextArea.append("hold square\n");
                this.holdSquare(x, y);
                this.IS_CURSOR_HOLDING_A_SQUARE = CursorState.HOLDING;
                break;
            default:
                throw new Error("Invalid cursor state.");
        }
    }
    
        
    private void handleAsADelete(int x, int y) {
        this.gameModel.deleteSquare(x, y);
        
        int delPatternCount = 
                        this.gameModel.checkAndDeleteCompletedTetrisShape(x, y);
        
        this.gameModel.incrementScore(delPatternCount);
     
        this.logTextArea.append("delete completed: (" + x + ", " + y + ") \n");
    }
    
    /* Public Methods */
    
    /**
     * This is the method that handles the event 'mouse click' over 
     * the BoardView.
     * 
     * @param evt 
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
        if(this.gameModel.getGameState() == GameModel.GameState.PAUSED)
            return;
        
        int pixelsX = evt.getX();
        int pixelsY = evt.getY();
        String str = "(" + pixelsX + ", " + pixelsY + ") px\n";
        this.logTextArea.append(str);
        int squareCoords[] = 
                     Utility.convertPixelCoordsToSquareCoords(pixelsX, pixelsY);
        int x = squareCoords[0];
        int y = squareCoords[1];
        str = "[" + x + ", " + y + "] sqr\n";
        this.logTextArea.append(str);
        
        this.handleSelectedBoardViewPosition(x, y);    
    }

}
