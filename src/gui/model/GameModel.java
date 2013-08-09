package gui.model;

import game.pieces.Board;
import game.pieces.Square;
import gui.control.GameListener;
import gui.view.SmartCursor;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author givanse
 */
public final class GameModel{
 
    public enum GameState {RUNNING, PAUSED, OVER};
    
    /* properties */
    private GameState gameState;
    private int completedShapesCount = 0;
    private int multiplier = 1;
    private long speed;
    private long time;
    private Board board;
    private int currKeyPosX;
    private int currKeyPosY;
    /* properties */
    
    private static int MULTIPLIER_INCREMENTS = 1;
    private ArrayList<GameListener> listeners;
    
    public static long FALLING_SPEED_INITIAL = 1 * 1000;          /* 1 second */
    private static long FALLING_SPEED_MAX = 100;          /* 100 milliseconds */
    private static long FALLING_SPEED_INCREASE = 100;     /* 100 milliseconds */
    /* Interval at wich a new pair of squares will appear. */
    private static long SPAWN_TIME = 2 * 1000;                   /* 2 seconds */
    private static long SPAWN_TIME_MIN = 200;                  /* 0.1 seconds */
    private static long SPAWN_TIME_DECREASE = 200;             /* 0.5 seconds */
    /**
     * Interval at which:
     *   SPAWN_TIME is decreased
     *   speed      is increased
     * The interval lasts 2 minutes. 
     */
    private static long LEVEL_DURATION = (2 * 60 * 1000); /* Two minutes. */
    private SmartCursor smartCursor;
    
    public GameModel(SmartCursor smartCursor) {
        this.smartCursor =  smartCursor;
        this.listeners = new ArrayList();
        this.startNewGame();
    }
    
    /* Public Methods */
    
    public void removeListener(GameListener listener){
        listeners.remove(listener);
    }
    
    public void addListener(GameListener listener){
        listeners.add(listener);
    }
    
    public void setScore(int score){
        this.completedShapesCount = score;
        for(int i = 0; i < listeners.size(); i++){
            this.listeners.get(i).scoreChanged(this.completedShapesCount);
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

    public void setTime(long time){
        this.time = time;
        for(int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).timeChanged(this.time);
        }
    }
        
    public void updateGameState() {
        this.board.updateSquares();
        this.smartCursor.update();
        
        for(int i = 0; i < listeners.size(); i++) {
            this.listeners.get(i).squaresChanged(this.board.getSquares(), 
                                                 this.board.getFallingSquares(),
                                                 this.smartCursor);
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
        
        int rowsFilledWithSquares = 3;
        this.board = new Board(rowsFilledWithSquares);
        this.speed = GameModel.FALLING_SPEED_INITIAL;
        this.currKeyPosX = 0;
        this.currKeyPosY = 0;
        
        /* TODOD: Too many timers! Fix it. */
        
        /**
         * Level loop.
         */
        Timer levelTimer = new Timer("levelTimer");
        levelTimer.schedule(new TimerTask(){ 
            @Override
            public void run() {
                
                if(getSpeed() > GameModel.FALLING_SPEED_MAX)
                    setSpeed(getSpeed() - GameModel.FALLING_SPEED_INCREASE);
                if(GameModel.SPAWN_TIME > GameModel.SPAWN_TIME_MIN)
                    GameModel.SPAWN_TIME -= GameModel.SPAWN_TIME_DECREASE;
                multiplier += MULTIPLIER_INCREMENTS;
                
                /* Check if this timer should keep running. */
                if(gameState ==  GameState.OVER)
                    this.cancel();
                
            }
        }, 0, GameModel.LEVEL_DURATION);
        
        /**
         * Spawn loop.
         */
        Timer spawnTimer = new Timer("spawnTimer");
        spawnTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                long initialT = System.currentTimeMillis();
                
                if(!board.addRandomFallingPairOfSquares())
                    setGameState(GameState.OVER);
                
                /* Check if this timer should keep running. */
                if(gameState ==  GameState.OVER)
                    this.cancel();
                
                long finalT = System.currentTimeMillis();
                long elapsedT = finalT - initialT;
                long totalElapsedT = time + elapsedT + GameModel.SPAWN_TIME;
                setTime(totalElapsedT);
            }
        }, GameModel.SPAWN_TIME, GameModel.SPAWN_TIME);
        
        this.setGameState(GameState.RUNNING);
    }
    
    public Square getSquare(int x, int y) {
        return this.board.getSquares()[x][y];
    }
    
    public boolean isPositionAvailable(int x, int y) {
        return this.board.isPositionAvailable(x, y);
    }
    
    public boolean isFallingSquare(int x, int y) {
        for(Square col : this.board.getFallingSquares()) {
            if(x == col.getX() && y == col.getY())
                return true;
        }
        return false;
    }
    
    public boolean swapSquares(int x1, int y1, int x2, int y2) {
        return this.board.swapSquares(x1, y1, x2, y2);
    }
    
    public void deleteSquare(int x, int y) {
        this.board.deleteSquare(x, y);
    }
    
    public int checkAndDeleteCompletedTetrisShape(int x, int y) {
        return this.board.checkAndDeleteCompletedTetrisShape(x, y);
    }
    
    /**
     * The score increment is a combination of the number of tetrominos
     * deleted and the time the player has lasted.
     */
    public void incrementScore(int increment) {
        this.completedShapesCount += increment;
        this.setScore(this.completedShapesCount * this.multiplier);
    }
    
    public void addCurrentX(int increment) {
        int x = this.currKeyPosX + increment;
        if(x < 0 || x >= this.board.getWidth())
            return;
        
        this.currKeyPosX = x;
    }

    public void addCurrentY(int increment) {
        int y = this.currKeyPosY + increment;
        if(y < 0 || y >= this.board.getHeight())
            return;
        
        this.currKeyPosY = y;
    }

    public int getCurrKeyPosX() {
        return this.currKeyPosX;
    }

    public int getCurrKeyPosY() {
        return this.currKeyPosY;
    }
    
}