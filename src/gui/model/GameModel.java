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
    private Board board;
    private long speed;
    
    private ArrayList<GameListener> listeners;
    public static long FALLING_SPEED_INITIAL = 1 * 1000; /* 1 second */
    private static long FALLING_SPEED_MAX = 100; /* 100 milliseconds */
    private static long FALLING_SPEED_INCREASE = 100; /* 100 milliseconds */
    /* Interval at wich a new pair of squares will appear. */
    private static long SPAWN_TIME = 2 * 1000; /* 2 seconds */
    private static long SPAWN_TIME_MIN = 200; /* 0.1 seconds */
    private static long SPAWN_TIME_DECREASE = 200; /* 0.5 seconds */
    /**
     * Interval at which:
     *   SPAWN_TIME is decreased
     *   speed      is increased
     * The interval lasts 2 minutes. 
     */
    private static long LEVEL_DURATION = (2 * 60 * 1000); 
    
    public GameModel() {
        this.listeners = new ArrayList();
        this.startNewGame();
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
    
    public final void startNewGame() {
        if(this.board != null) {
            /**
             * Reaching this point means that we are resetting the game.
             * Its very important to change the state of the game to OVER, so
             * that the levelTimer and spawnTimer are terminated gracefully.
             */
            this.setGameState(GameState.OVER);
        }
        int rowsFilledWithSquares = 5;
        this.board = new Board(rowsFilledWithSquares);
        this.speed = GameModel.FALLING_SPEED_INITIAL;
        
        Timer levelTimer = new Timer();
        levelTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                if(getSpeed() > GameModel.FALLING_SPEED_MAX)
                    setSpeed(getSpeed() - GameModel.FALLING_SPEED_INCREASE);
                if(GameModel.SPAWN_TIME > GameModel.SPAWN_TIME_MIN)
                    GameModel.SPAWN_TIME -= GameModel.SPAWN_TIME_DECREASE;
                if(gameState ==  GameState.OVER)
                    this.cancel();
            }
        }, 0, GameModel.LEVEL_DURATION);
        
        Timer spawnTimer = new Timer();
        spawnTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                board.addRandomFallingPairOfSquares();
                if(gameState ==  GameState.OVER)
                    this.cancel();
            }
        }, GameModel.SPAWN_TIME, GameModel.SPAWN_TIME);
        
        this.gameState = GameState.RUNNING;
    }
    
}