package gui.control;

import game.pieces.Square;
import gui.model.GameModel;
import gui.view.BoardPanel;
import java.util.ArrayList;

/**
 *
 * @author givanse
 */
public class BoardController {
    public BoardController(final BoardPanel boardPanel, 
                          final GameModel gameModel) {
        
        GameListener gameListener = new GameAdapter() {
            @Override
            public void squaresChanged(Square boardSquares[][], 
                                       ArrayList<Square> fallingSquares){
                boardPanel.drawObjects(boardSquares, fallingSquares);
            }
        };
        
        gameModel.addListener(gameListener);
    }    
}
