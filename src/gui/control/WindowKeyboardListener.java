package gui.control;

import game.pieces.Square;
import gui.control.BoardViewMouseListener.CursorState;
import gui.model.GameModel;
import gui.view.BoardView;
import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author givanse
 */
public class WindowKeyboardListener extends KeyAdapter {
    
    private BoardView boardView;
    private JTextArea logTextArea;
    private GameModel gameModel;
    private CursorState IS_CURSOR_HOLDING_A_SQUARE = CursorState.NOT_HOLDING;
    private int xHeldByCursor;
    private int yHeldByCursor;
    
    public WindowKeyboardListener(
              BoardView boardView, JTextArea logTextArea, GameModel gameModel) {
        this.boardView = boardView;
        this.logTextArea = logTextArea;
        this.gameModel = gameModel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("pressed ");
        int keyCode = e.getKeyCode();
        this.handleKeyPressedEvent(keyCode);
    }
    
    private void handleKeyPressedEvent(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_SPACE:
                this.logTextArea.append("key space\n");
                this.handleAsASwap();
                break;
            case KeyEvent.VK_RIGHT:
                this.logTextArea.append("key right\n");
                this.gameModel.addCurrentX(1);
                break;
            case KeyEvent.VK_LEFT:
                this.logTextArea.append("key left\n");
                this.gameModel.addCurrentX(-1);
                break;
            case KeyEvent.VK_UP:
                this.logTextArea.append("key up\n");
                this.gameModel.addCurrentY(-1);
                break;
            case KeyEvent.VK_DOWN:
                this.logTextArea.append("key down\n");
                this.gameModel.addCurrentY(1);
                break;
        }
        this.updateCursorPosition();
    }
    
    private void updateCursorPosition() {
        Point boardViewPoint = this.boardView.getLocationOnScreen();
        int x = (int) (this.gameModel.getCurrKeyPosX() * Square.SIZE + 
                      boardViewPoint.getX() + Square.SIZE / 2);
        int y = (int) (this.gameModel.getCurrKeyPosY() * Square.SIZE + 
                       boardViewPoint.getY() + Square.SIZE / 2);
        this.moveMouse(x, y);
    }
    
    /* TODO: refactor */
    private void handleAsASwap() {
        int x = this.gameModel.getCurrKeyPosX();
        int y = this.gameModel.getCurrKeyPosY();
        
        switch(this.IS_CURSOR_HOLDING_A_SQUARE) {
            case HOLDING:
                this.logTextArea.append("swap square\n");
                this.swapSquare();
                this.IS_CURSOR_HOLDING_A_SQUARE = BoardViewMouseListener.CursorState.NOT_HOLDING;
                break;
            case NOT_HOLDING:
                this.logTextArea.append("hold square\n");
                this.holdSquare();
                this.IS_CURSOR_HOLDING_A_SQUARE = BoardViewMouseListener.CursorState.HOLDING;
                break;
            default:
                throw new Error("Invalid cursor state.");
        }
    }
    
    /** TODO: refactor
     * Swap the square, located by xHeldByCursor and yHeldByCursor, with the
     * selected square.
     * 
     * @param x coordinate of the selected square
     * @param y coordinate of the selected square
     */
    private void swapSquare() {
        int x = this.gameModel.getCurrKeyPosX();
        int y = this.gameModel.getCurrKeyPosY();
       
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
    
    /** TODO: refactor
     * 
     * @param x coordinate of the selected square
     * @param y coordinate of the selected square
     */
    private void holdSquare() {
        int x = this.gameModel.getCurrKeyPosX();
        int y = this.gameModel.getCurrKeyPosY();
        
        this.xHeldByCursor = x;
        this.yHeldByCursor = y;
        Square square = this.gameModel.getSquare(x, y);
        this.boardView.changeCursorToSelectedSquare(square);
    }
    
    public void moveMouse(int x, int y) {
        GraphicsDevice screen = this.boardView.getGraphicsConfiguration()
                                              .getDevice();
        try {
            Robot robot = new Robot(screen);
            robot.mouseMove(x, y);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
   }
}
