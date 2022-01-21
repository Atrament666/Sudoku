/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.atrasoft.sudoku;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Atrament
 */
public class SudokuUtilsTest {
    
    public SudokuUtilsTest() {
    }

    @Test
    public void testIsNumberInRow() {
    }

    @Test
    public void testIsNumberInColumn() {
    }

    @Test
    public void testIsNumberInSegment() {
    }

    @Test
    public void testIsValidPlacement() {
    }

    @Test
    public void testPrintBoard() {
        int[][] sudoku = SudokuUtils.generateSudoku(17);
        SudokuUtils.printBoard(sudoku);
    }

    @Test
    public void testGenerateSudoku() {
        int[][] sudoku = SudokuUtils.generateSudoku(17);
        SudokuUtils.printBoard(sudoku);
    }

    @Test
    public void testSolveSudoku() {
        int[][] sudoku = SudokuUtils.generateSudoku(17);
        System.out.println("Unsolved:");
        SudokuUtils.printBoard(sudoku);
        int[][] solved = SudokuUtils.solveSudoku(sudoku);
        System.out.println("Solved:");
        SudokuUtils.printBoard(solved);
    }
    
}
