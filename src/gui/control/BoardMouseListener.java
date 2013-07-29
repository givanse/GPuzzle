package gui.control;

import game.Utility;
import game.pieces.Square;
import gui.model.GameModel;
import gui.view.BoardPanel;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

/**
 *
 * @author givanse
 */
public class BoardMouseListener extends MouseAdapter {

    private BoardPanel boardPanel;
    private JTextArea logTextArea;
    private GameModel gameModel;
    private enum CursorState {HOLDING, NOT_HOLDING};
    private CursorState IS_CURSOR_HOLDING_A_SQUARE = CursorState.NOT_HOLDING;
    private int xBeingHold;
    private int yBeingHold;
    
    public BoardMouseListener(BoardPanel boardPanel, JTextArea logTextArea, 
                              GameModel gameModel) {
        this.boardPanel = boardPanel;
        this.logTextArea = logTextArea;
        this.gameModel = gameModel;
    }
    
    private void changeCursorToPickedSquare(int x, int y) {
        Square s = this.gameModel.getSquare(x, y);
        Image squareImage = s.getSquareColour().getImage();
        this.boardPanel.changeCursor(squareImage);
    }
    
    private void pickUpSquare(int x, int y) {
        this.xBeingHold = x;
        this.yBeingHold = y;
        this.changeCursorToPickedSquare(x, y);
    }
    
    private void swapSquare(int x, int y) {
        boolean swapResult = this.gameModel.swapSquares(
                                        this.xBeingHold, this.yBeingHold, x, y);
        this.logTextArea.append("swap completed: " + swapResult + "\n");
        
        /* Either the swap is successful or not, the cursor is changed back. */
        this.xBeingHold = -1;
        this.yBeingHold = -1;
        this.boardPanel.changeCursorToDefault();
    }
    
    /* Public Methods */
    
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
        
        this.handlePositionSelected(x, y);
    }
    
    public void handlePositionSelected(int x, int y) {
        /* Is it an empty space? */
        if(this.gameModel.isPositionAvailable(x, y)) {
            this.logTextArea.append("empty position\n");
            return;
        }
        /**
         * Is it a falling square? We don't need to validate that because
         * those squares are stored in different data structure (ArrayList).
         */
        
        switch(this.IS_CURSOR_HOLDING_A_SQUARE) {
            case HOLDING:
                this.logTextArea.append("swap square\n");
                this.swapSquare(x, y);
                this.IS_CURSOR_HOLDING_A_SQUARE = CursorState.NOT_HOLDING;
                break;
            case NOT_HOLDING:
                this.logTextArea.append("hold square\n");
                this.pickUpSquare(x, y);
                this.IS_CURSOR_HOLDING_A_SQUARE = CursorState.HOLDING;
                break;
        }
    }
    
}
