package gui.model;

import game.pieces.Board;
import gui.control.GameListener;
import java.util.ArrayList;

/**
 *
 * @author givanse
 */
public class GameModel{
 
    public enum GameState {RUNNING, PAUSED, OVER};
    
    private GameState gameState;
    private int score = 0;
    private final Board board;
    
    private ArrayList<GameListener> listeners;

    public GameModel() {
        this.gameState = GameState.RUNNING;
        int filledRows = 3;
        this.board = new Board(filledRows);
        this.listeners = new ArrayList();
    }
    
    public void removeListener(GameListener listener){
        listeners.remove(listener);
    }
    
    public void addListener(GameListener listener){
        listeners.add(listener);
    }
    
    public void setScore(int score){
        this.score = score;
        for(int i = 0; i < listeners.size(); i++){
            this.listeners.get(i).scoreChanged(this.score);
        }
    }
    
    public void setGameState(GameState gameState){
        this.gameState = gameState;
        for(int i = 0; i < listeners.size(); i++) {
            this.listeners.get(i).gameStateChanged(this.gameState);
        }
    }
    
    public GameState getGameState() {
        return this.gameState;
    }
    
    public void updateSquares() {
        this.board.updateSquares();
        for(int i = 0; i < listeners.size(); i++) {
            this.listeners.get(i).squaresChanged(
                    this.board.getSquares(), this.board.getFallingSquares());
        }
    }
    
}