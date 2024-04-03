package org.example.sudoku;

import java.util.*;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    public SudokuGenerator() {

    }

    public static int[][] removeSomeCells(int[][] board){
        Random random = new Random();
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < SIZE; i++) {
            newBoard[i] = Arrays.copyOf(board[i], SIZE);
        }
        return removeCells(newBoard, random);
    }

    public static int[][] generateSudoku() {
        Random random = new Random();
        int[][] board = new int[9][9];
        solveSudoku(board, random);
        return board;
    }

    private static boolean solveSudoku(int[][] board, Random random) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    List<Integer> nums = new LinkedList<>();
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            nums.add(num);
                        }
                    }
                    Collections.shuffle(nums, random);
                    for (int num : nums) {
                        board[row][col] = num;
                        if (solveSudoku(board, random)) {
                            return true;
                        }
                        board[row][col] = EMPTY;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        return !usedInRow(board, row, num) && !usedInCol(board, col, num) && !usedInBox(board, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] board, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] board, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[][] removeCells(int[][] board, Random random) {
//        final int cellsToRemove = 45;
        final int cellsToRemove = 1;
        for (int i = 0; i < cellsToRemove; i++) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col] != EMPTY) {
                board[row][col] = EMPTY;
            } else {
                i--;
            }
        }
        return board;
    }

    static void printSudoku(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
