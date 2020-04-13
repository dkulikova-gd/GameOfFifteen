import com.company.exceptions.EmptyFileException;
import com.company.exceptions.InvalidValueInFileException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.company.Main.*;


public class InputOutputTest {

    @Test
    public void fileExist() throws EmptyFileException, IOException {
        Path path = Paths.get("./src/main/resources/input.txt");
        String str = readFromFile(path);
        Assert.assertNotNull(str);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void fileNotExist() throws EmptyFileException, IOException {
        Path path = Paths.get("./src/test/resources/notExistFile.txt");
        String str = readFromFile(path);
    }

    @Test(expectedExceptions = EmptyFileException.class)
    public void fileIsEmpty() throws IOException, EmptyFileException {
        Path path = Paths.get("./src/test/resources/empty.txt");
        String str = readFromFile(path);
    }

    @Test
    public void fileIsNotEmpty() throws IOException, EmptyFileException {
        Path path = Paths.get("./src/main/resources/input.txt");
        String str = readFromFile(path);
        Assert.assertNotNull(str);
    }

    @Test(expectedExceptions = InvalidValueInFileException.class)
    public void invalidCountRows() throws InvalidValueInFileException {
        String str = "1 2 3 4\n5 6 7 8\n 9 10 11 12\n13 14 15 0\n1 2 3 4";
        parseStringFromFile(str);
    }

    @Test(expectedExceptions = InvalidValueInFileException.class)
    public void invalidCountOfValueInFile() throws InvalidValueInFileException {
        String str = "1 2 3 4\n5 6 7 8\n 9 10 11 12\n13 14 15 0 16";
        parseStringFromFile(str);
    }

    @Test(expectedExceptions = InvalidValueInFileException.class)
    public void isNotDigitInFile() throws InvalidValueInFileException {
        String str = "1 2 3 4\n5 6 7 8\n 9 10 11 12\n13 14 15 0 a";
        parseStringFromFile(str);
    }

    @Test(expectedExceptions = InvalidValueInFileException.class)
    public void numbersAreRepeated() throws InvalidValueInFileException {
        String str = "1 2 3 4\n5 6 7 8\n 9 10 11 12\n13 14 15 12";
        parseStringFromFile(str);
    }
}
