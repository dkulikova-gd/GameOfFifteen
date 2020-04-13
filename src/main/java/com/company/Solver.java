package com.company;

import java.util.*;

public class Solver {
    private Board initial;
    private ArrayList<Board> result = new ArrayList<Board>();


    private class ITEM{
        private ITEM prevBoard;
        private Board board;

        private ITEM(ITEM prevBoard, Board board) {
            this.prevBoard = prevBoard;
            this.board = board;
        }

        public Board getBoard() {
            return board;
        }
    }

    public Solver(Board initial) {
        this.initial = initial;

        if(!isSolvable()){
            result=null;
            return;
        }

        PriorityQueue<ITEM> priorityQueue = new PriorityQueue<ITEM>(10, new Comparator<ITEM>() {
            @Override
            public int compare(ITEM o1, ITEM o2) {
                return new Integer(measure(o1)).compareTo(new Integer(measure(o2)));
            }
        });

        priorityQueue.add(new ITEM(null, initial));

        while (true){
            ITEM board = priorityQueue.poll();

            if(board.board.isGoal()) {
                itemToList(new ITEM(board, board.board));
                return;
            }

            Iterator iterator = board.board.neighbors().iterator();
            while (iterator.hasNext()){
                Board board1 = (Board) iterator.next();

                if(board1!= null && !containsInPath(board, board1))
                    priorityQueue.add(new ITEM(board, board1));
            }

        }
    }

    public Movement isMovement(Board prevBoard, Board curBoard){
        if(prevBoard.getZeroY()+1==curBoard.getZeroY())
            return Movement.RIGHT;
        else if(prevBoard.getZeroY()-1==curBoard.getZeroY())
            return Movement.LEFT;
        else if(prevBoard.getZeroX()+1==curBoard.getZeroX())
            return Movement.DOWN;
        else return Movement.UP;
    }

    //f(x)
    private static int measure(ITEM item){
        ITEM item2 = item;
        int c= 0;   // g(x)
        int measure = item.getBoard().getCountNumbersStandingOutOfPlace();  // h(x)
        while (true){
            c++;
            item2 = item2.prevBoard;
            if(item2 == null) {
                // g(x) + h(x)
                return measure + c;
            }
        }
    }

    private void itemToList(ITEM item){
        ITEM item2 = item;
        while (true){
            item2 = item2.prevBoard;
            if(item2 == null) {
                Collections.reverse(result);
                return;
            }
            result.add(item2.board);
        }
    }

    private boolean containsInPath(ITEM item, Board board){
        ITEM item2 = item;
        while (true){
            if(item2.board.equals(board)) return true;
            item2 = item2.prevBoard;
            if(item2 == null) return false;
        }
    }


    public boolean isSolvable() {
        int[][] board = initial.getBlocks();
        int size = initial.getBlocks().length;
        int[] a = new int[size*size];
        int k = -1;
        for (int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                k += 1;
                a[k] = board[i][j];
            }
        }

        int sum = 0;
        for (int i=0; i<a.length; i++) {
            if (a[i] != 0) {
                for (int j = i+1; j < a.length; j++) {
                    if (a[j]!=0 && a[j] < a[i]) {
                        sum++;
                    }
                }
            }
        }
        for (int i=0; i<a.length; ++i) {
            if (a[i] == 0) {
                sum += 1 + i / size;
            }
        }
        if (sum%2==0){
            return true;
        }
        else {
            return false;
        }

    }


    public ArrayList<Board> getResult() {
        return result;
    }
}
