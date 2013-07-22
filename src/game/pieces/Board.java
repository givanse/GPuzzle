package game.pieces;

import game.pieces.Square.SquareType;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author givanse
 */
public class Board {
    
    private final int width;
    private final int height;
    private final boolean availabilityMatrix[][];
    private final Square squares[][];
    private final ArrayList<Square> fallingSquares = new ArrayList<Square>(2);
    
    public enum SwapDirection {LEFT, RIGHT, UP, DOWN};
    public static int WIDTH_IN_SQUARES = 12;  // number of squares 
    public static int HEIGHT_IN_SQUARES = 18; // number of squares
    
    public Board() {
        this(Board.WIDTH_IN_SQUARES, Board.HEIGHT_IN_SQUARES);
    }
    
    public Board(Square[][] squares) {
        this(squares[0].length, squares.length); 
         for(int x = 0; x < this.width ; x++)
            for(int y = 0; y < this.height; y++) {
                /* Update all the positions of the availabilityMatrix. */
                this.updatePosition(x, y, this.isPositionAvailable(x, y));
                this.squares[x][y] = squares[x][y];
            }
    }
    
    public Board(int widthInSquares, int heightInSquares) {
        this.width = widthInSquares;
        this.height = heightInSquares;
        this.availabilityMatrix = new boolean[this.width][this.height];
        this.squares = new Square[this.width][this.height];
        this.generateRandomFallingPair();
    }
    
    private void generateRandomFallingPair() {
        Random random = new Random(System.currentTimeMillis());
        int randomX = random.nextInt(this.width);
        int constantY = 0;
        SquareType sqrTypes[] = SquareType.values();
        int randomType = random.nextInt(sqrTypes.length);
        Square square1 = new Square(randomX, constantY, sqrTypes[randomType]);
        this.fallingSquares.add(square1);
        randomX = (randomX == this.width-1) ? randomX - 1 : randomX + 1;
        randomType = random.nextInt(sqrTypes.length);
        Square square2 = new Square(randomX, constantY, sqrTypes[randomType]);
        this.fallingSquares.add(square2);
    }
    
    /* Public methods */
    
    public void update() {
        
    }
    
    public Square[][] getSquares() {
        return this.squares;
    }
    
    public ArrayList<Square> getFallingSquares() {
        return this.fallingSquares;
    }
    
    public final boolean isPositionAvailable(int x, int y) {
        return false;
    }
    
    public final void updatePosition(int x, int y, boolean isAvailable) {
        this.availabilityMatrix[x][y] =  isAvailable;
    }
    
    public boolean isValidSwap(int x, int y, SwapDirection dir) {
        return true;
    }
    
    public void deleteCompletedTetrisShapes() {
        
    }
    
    public void moveDownLooseRows() {
        
    }
}
