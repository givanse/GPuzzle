package gui.control;

import game.pieces.Square;
import gui.model.GameModel;
import gui.view.BoardView;
import gui.view.SmartCursor;
import java.util.ArrayList;

/**
 *
 * @author givanse
 */
public class BoardViewController {
    
    public BoardViewController(final BoardView boardView, 
                               final GameModel gameModel) {
        
        GameListener gameListener = new GameAdapter() {
            @Override
            public void squaresChanged(Square boardSquares[][], 
                                       ArrayList<Square> fallingSquares,
                                       SmartCursor smartCursor){
                boardView.drawObjects(
                                     boardSquares, fallingSquares, smartCursor);
            }
        };
        
        gameModel.addListener(gameListener);
    }    
}
