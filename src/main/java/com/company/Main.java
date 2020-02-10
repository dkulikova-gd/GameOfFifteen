package com.company;

import com.company.exceptions.EmptyFileException;
import com.company.exceptions.InvalidValueInFileException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    private static int SIZE =4;

    public static void main(String[] args) {
        Path inputFilePath = Paths.get("./src/main/resources/input.txt");
        Path outputFilePath = Paths.get("./src/main/resources/output.txt");
        int[][] blocks = new int[SIZE][SIZE];

        try {
            blocks = readBlockFromFile(inputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("fghjkl;");
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InvalidValueInFileException e) {
            System.out.println(e.getMessage());
            return;
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return;
        }
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);

        try {
            writeResultToFile(outputFilePath, solver);
        } catch (IOException e) {
            System.out.println("Error of writing to file\n"+e.getMessage());
            return;
        }
    }

    private static void writeResultToFile(Path outputFilePath, Solver solver) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath.toString()))) {
            if(solver.solution()==null){
                bw.write("-1");
            }
            else{
                ArrayList<Board> result = solver.solution();
                String s="Initial configuration:\n"+ result.get(0).toString()+
                        "\nNumber of tiles movements:"+(result.size()-1);
                for (int i=1; i<result.size();i++){
                    switch (solver.isMovement(result.get(i-1),result.get(i)))
                    {
                        case LEFT:
                            s+="\nLeft\n"; break;
                        case RIGHT:
                            s+="\nRight\n"; break;
                        case DOWN:
                            s+="\nDown\n"; break;
                        case UP:
                            s+="\nUp\n"; break;
                    }
                    s += result.get(i).toString();
                }
                bw.write(s);
            }

        }
    }
    public static int[][] readBlockFromFile(Path inputFilePath) throws IOException, EmptyFileException, InvalidValueInFileException {
        int[][] blocks = new int[SIZE][SIZE];
        String strFromFile = readFromFile(inputFilePath);
        blocks=parseStringFromFile(strFromFile);
        return blocks;
    }

    public static int[][] parseStringFromFile(String str) throws InvalidValueInFileException {
        int[][] blocks = new int[SIZE][SIZE];
        String[] rows = str.split("\n");
        if (rows.length!= SIZE){
            throw new InvalidValueInFileException("Count of rows doesn't equal "+SIZE);
        };
        for (int i =0; i<SIZE;i++) {
            String[] row = rows[i].split(" ");
            if (row.length!=SIZE)
                throw new InvalidValueInFileException("Count of value in row doesn't equal "+SIZE);
            for (int j = 0; j < SIZE; j++)
                try {
                    blocks[i][j] = Integer.parseInt(row[j]);
                }
            catch (NumberFormatException e){
                    throw new InvalidValueInFileException("Value must be digit");
            }
        }
        checkMatrixNumbers(blocks);
        return blocks;
    }

    private static void checkMatrixNumbers(int[][] board) throws InvalidValueInFileException {
        int ArrayLength = SIZE*SIZE;
        int[] numbers = new int[ArrayLength];
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if (board[i][j]<0 || board[i][j]>ArrayLength)
                    throw new InvalidValueInFileException("The numbers must be between 0 and 15");
                numbers[board[i][j]]++;
            }
        }
        for (int k=0; k<ArrayLength;k++){
            if (numbers[k]!=1)
                throw new InvalidValueInFileException("Numbers must not be repeated");
        }
    }


    public static String readFromFile(Path inputFilePath) throws IOException, EmptyFileException {
        StringBuffer sb = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath.toString()))) {
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
            if (sb.toString().equals("")){
                throw new EmptyFileException();
            }
        }
        return sb.toString();
    }
}
