import com.company.Board;
import com.company.Solver;
import com.company.exceptions.EmptyFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.company.Main.readFromFile;

public class SolutionTest {

    @Test
    public void finalConfigurationOfSolvableBoardIsCorrect(){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,0,12},{13,14,11,15}};
        int[][] expectedMatrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        Board expectedBoard = new Board(expectedMatrix);
        Board board = new Board(matrix);
        Solver solver = new Solver(board);

        Board result = solver.solution().get(solver.solution().size()-1);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void finalConfigurationOfUnsolvableBoardIsCorrect(){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,15,14,0}};
        Board board = new Board(matrix);

        Solver solver = new Solver(board);

        Assert.assertEquals(null,solver.solution());
    }
}
