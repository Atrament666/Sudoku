/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku;

import java.util.Arrays;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Atrament
 */
public class SudokuUtils {
    
    private static final Logger log = LoggerFactory.getLogger(SudokuUtils.class);

    /**
     * Generates a sudoku
     * @param level how many pre-filled numbers there will be
     * @return generated sudoku
     */
    public static int[][] generateSudoku(int level) {
        Random rand = new Random();
        int[][] result = new int[9][9];
        for (int i = 0; i <= 6; i += 3) {
            fillSegment(result, i, i);
        }
        result = solveSudoku(result);
        int k = (9 * 9) - level;
        while (k > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (result[row][col] != 0) {
                result[row][col] = 0;
                k--;
            }

        }
        return result;
    }

    /**
     * Solves given sudoku
     * @param board sudoku board to solve
     * @return solved sudoku or null if solution doesn't exist
     */
    public static int[][] solveSudoku(int[][] board) {
        int[][] result = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        Boolean solved = solveBoard(result);
        if (solved) {
            log.debug("Sudoku solved");
            return result;
        } else {
            log.debug("Sudoku not solved");
            return null;
        }
    }
    
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int i = 1; i <= 9; i++) {
                        if (isValidPlacement(board, i, row, col)) {
                            board[row][col] = i;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks whether the given number is a valid number for the given position
     * @param board sudoku board to check
     * @param number a number
     * @param row row in sudoku board
     * @param col column in sudoku board
     * @return
     */
    public static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row)
                && !isNumberInColumn(board, number, col)
                && !isNumberInSegment(board, number, row, col);
    }

    /**
     * Prints sudoku board to standard output
     * @param board board to print
     */
    public static void printBoard(int[][] board) {
        for (int row = 0; row < board[0].length; row++) {
            if ((row % 3 == 0) && (row != 0)) {
                System.out.println("-----------");

            }
            for (int col = 0; col < board.length; col++) {
                if ((col % 3 == 0) && (col != 0)) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println("");
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int col) {
        for (int[] board1 : board) {
            if (board1[col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInSegment(int[][] board, int number, int row, int col) {
        int localRow = row - (row % 3);
        int localColumn = col - (col % 3);

        for (int i = localRow; i < (localRow + 3); i++) {
            for (int j = localColumn; j < (localColumn + 3); j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void fillSegment(int[][] board, int row, int col) {
        Random rand = new Random();
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = rand.nextInt(10);

                } while (isNumberInSegment(board, num, row, col));
                board[row + i][col + j] = num;
            }
        }
    }

    
}
