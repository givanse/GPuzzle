package game.pieces;

import game.pieces.Square.SquareType;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author givanse
 */
public class Board {
    
    private final SquaresMatrix squares;
    private final ArrayList<Square> fallingSquares = new ArrayList<>(2);
    
    public enum SwapDirection {LEFT, RIGHT, UP, DOWN};
    public static int WIDTH_IN_SQUARES = 12;  // number of squares 
    public static int HEIGHT_IN_SQUARES = 18; // number of squares
    
    public Board() {
        this(Board.WIDTH_IN_SQUARES, Board.HEIGHT_IN_SQUARES);
    }
    
    public Board(SquaresMatrix squares) {
        this.squares = squares;
    }
    
    public Board(int widthInSquares, int heightInSquares) {
        this.squares = new SquaresMatrix(widthInSquares, heightInSquares);
        this.generateRandomFallingPair();
    }
    
    private void generateRandomFallingPair() {
        Random random = new Random(System.currentTimeMillis());
        int width = this.squares.getWidth();
        int randomX = random.nextInt(width);
        int constantY = 0;
        SquareType sqrTypes[] = SquareType.values();
        int randomType = random.nextInt(sqrTypes.length);
        Square square1 = new Square(randomX, constantY, sqrTypes[randomType]);
        this.fallingSquares.add(square1);
        randomX = (randomX == width - 1) ? randomX - 1 : randomX + 1;
        randomType = random.nextInt(sqrTypes.length);
        Square square2 = new Square(randomX, constantY, sqrTypes[randomType]);
        this.fallingSquares.add(square2);
    }
    
    /* Public methods */
    
    public void update() {
        
    }
    
    public ArrayList<Square> getFallingSquares() {
        return this.fallingSquares;
    }
    
    public final boolean isPositionAvailable(int x, int y) {
        return false;
    }
    
    public boolean isValidSwap(int x, int y, SwapDirection dir) {
        return true;
    }
    
    public void deleteCompletedTetrisShapes() {
        
    }
    
    public void moveDownLooseRows() {
        
    }
    
    public SquaresMatrix getSquares() {
        return this.squares;
    }
}
