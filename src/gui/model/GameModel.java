package gui.model;

import game.pieces.Board;
import gui.control.GameListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author givanse
 */
public final class GameModel{
 
    public enum GameState {RUNNING, PAUSED, OVER};
    
    private GameState gameState;
    private int score = 0;
    private final Board board;
    private long speed;
    
    private ArrayList<GameListener> listeners;
    private Timer timer; 
    private static long INITIAL_SPEED = 1 * 1000; /* 1 second */
    private static long MAX_SPEED = 100; /* 100 milliseconds */
    private static long SPEED_INCREASE = 100; /* 100 milliseconds */
    /* Interval at which the SPAWN_TIME is decreases, 2 minutes. */
    private static long INCREASE_SPEED_INTERVAL = (2 * 60 * 1000); 
    
    /* Interval at wich new squares appear */
    public static long SPAWN_TIME = 10 * 1000; /* 10 seconds */
    
    public GameModel() {
        this.gameState = GameState.OVER;
        int rowsFilledWithSquares = 5;
        this.board = new Board(rowsFilledWithSquares);
        this.speed = GameModel.INITIAL_SPEED;
        
        this.listeners = new ArrayList();
        
         this.timer = new Timer();
         this.timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if(getSpeed() > GameModel.MAX_SPEED)
                    setSpeed(getSpeed() - GameModel.SPEED_INCREASE);
            }
         }, GameModel.INCREASE_SPEED_INTERVAL, GameModel.INCREASE_SPEED_INTERVAL);
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
    
    public void setSpeed(long speed){
        this.speed = speed;
        for(int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).speedChanged(this.speed);
        }
    }
    
    public long getSpeed() {
        return this.speed;
    }
    
    public void updateSquares() {
        this.board.updateSquares();
        for(int i = 0; i < listeners.size(); i++) {
            this.listeners.get(i).squaresChanged(
                    this.board.getSquares(), this.board.getFallingSquares());
        }
    }
    
}