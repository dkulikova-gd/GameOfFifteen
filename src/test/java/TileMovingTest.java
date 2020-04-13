import com.company.Board;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TileMovingTest {

    private int[][] matrix;
    private Board board;
    private int zeroX;
    private int zeroY;

    @BeforeMethod
    public void init() {
        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 0, 12}, {13, 14, 11, 15}};
        board = new Board(matrix);
        zeroX = board.getZeroX();
        zeroY = board.getZeroY();
    }
    @Test
    public void tileMovesRight(){
        int[][] expectedMatrix = {{1,2,3,4},{5,6,7,8},{9,10,12,0},{13,14,11,15}};
        Board expectedBoard = new Board(expectedMatrix);

        Board result = board.changePosition(board.getNewBlock(), zeroX, zeroY,zeroX, zeroY+1);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void tileMovesLeft(){
        int[][] expectedMatrix = {{1,2,3,4},{5,6,7,8},{9,0,10,12},{13,14,11,15}};
        Board expectedBoard = new Board(expectedMatrix);

        Board result = board.changePosition(board.getNewBlock(), zeroX, zeroY,zeroX, zeroY-1);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void tileMovesUp(){
        int[][] expectedMatrix = {{1,2,3,4},{5,6,0,8},{9,10,7,12},{13,14,11,15}};
        Board expectedBoard = new Board(expectedMatrix);

        Board result = board.changePosition(board.getNewBlock(), zeroX, zeroY,zeroX-1, zeroY);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void tileMovesDown(){
        int[][] expectedMatrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,0,15}};
        Board expectedBoard = new Board(expectedMatrix);

        Board result = board.changePosition(board.getNewBlock(), zeroX, zeroY,zeroX+1, zeroY);

        Assert.assertEquals(expectedBoard, result);
    }
}
