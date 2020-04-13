package com.company;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private int[][] blocks;
    private int zeroX;
    private int zeroY;
    private int countNumbersStandingOutOfPlace;

    public Board(int[][] blocks) {
        this.blocks = blocks;

        countNumbersStandingOutOfPlace = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] == 0) {
                    zeroX =  i;
                    zeroY =  j;
                }
                else if (blocks[i][j] != (i* getBlockLength() + j + 1)) {
                    countNumbersStandingOutOfPlace += 1;
                }

            }
        }
    }


    public int getBlockLength() {
        return blocks.length;
    }

    public int getCountNumbersStandingOutOfPlace() {
        return countNumbersStandingOutOfPlace;
    }

    public boolean isGoal() {
        return countNumbersStandingOutOfPlace == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (board.getBlockLength() != getBlockLength()) return false;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != board.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        Set<Board> boardList = new HashSet<Board>();
        boardList.add(changePosition(getNewBlock(), zeroX, zeroY, zeroX, zeroY + 1));
        boardList.add(changePosition(getNewBlock(), zeroX, zeroY, zeroX, zeroY - 1));
        boardList.add(changePosition(getNewBlock(), zeroX, zeroY, zeroX - 1, zeroY));
        boardList.add(changePosition(getNewBlock(), zeroX, zeroY, zeroX + 1, zeroY));

        return boardList;
    }

    public int[][] getNewBlock() {
        return copy(blocks);
    }

    public Board changePosition(int[][] blocks2, int x1, int y1, int x2, int y2) {
        if (x2 > -1 && x2 < getBlockLength() && y2 > -1 && y2 < getBlockLength()) {
            int t = blocks2[x2][y2];
            blocks2[x2][y2] = blocks2[x1][y1];
            blocks2[x1][y1] = t;
            return new Board(blocks2);
        } else
            return null;

    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private static int[][] copy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = new int[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                result[i][j] = original[i][j];
            }
        }
        return result;
    }

    public int getZeroX() {
        return zeroX;
    }

    public int getZeroY() {
        return zeroY;
    }

    public int[][] getBlocks() {
        return blocks;
    }
}
