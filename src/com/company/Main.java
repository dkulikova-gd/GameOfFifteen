package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path inputFilePath = Paths.get("./input.txt");
        Path outputFilePath = Paths.get("./output.txt");
        int size =4;
        int[][] blocks = new int[size][size];
        try {
            blocks = readBlockFromFile(size, inputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Error, file not found\n"+e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Error of reading from file\n"+e.getMessage());
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
                bw.write(-1);
            }
            else{
                for (Board board : solver.solution())
                    bw.write(board.toString());
            }

        }
    }
    private static int[][] readBlockFromFile(int size, Path inputFilePath) throws IOException, FileNotFoundException{
        int[][] blocks = new int[size][size];
        int i=0;
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath.toString()))){
            String inValue;
            while ((inValue=br.readLine()) != null && i<4){
                String[] row = inValue.split(" ");
                if (row.length!= size){
                    throw new IOException("Length of line is more then 4");
                };
                for(int j=0; j< size; j++){
                    try
                    {
                        blocks[i][j] = Integer.parseInt(row[j]);
                    }
                    catch(NumberFormatException nfe)
                    {
                        throw new IOException("Value in file isn't digit");
                    }
                }
                i++;
            }
        }
        return blocks;
    }
}