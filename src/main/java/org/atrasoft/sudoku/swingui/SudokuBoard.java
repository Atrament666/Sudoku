/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.atrasoft.sudoku.SudokuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Atrament
 */
public class SudokuBoard extends JPanel {

    private int[][] board;
    private SudokuCell[][] cells;
    private MouseListener editCell;

    private final Color darkBrown = new Color(100, 45, 10);
    private final Color lightBrown = new Color(240, 180, 140);

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public SudokuBoard(int[][] aBoard) {
        super();
        board = aBoard;
        createCells();
        updateCells(board);
        initComponents();
    }
    
    public SudokuBoard() {
        this(new int[9][9]);
    }
    
    

    private void initComponents() {
        setLayout(new MigLayout("align 50% 50%", "20[][][]20[][][]20[][][]20", "20[][][]20[][][]20[][][]20[]"));
        setBackground(darkBrown);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                add(cells[i][j], "cell " + i + " " + j);
            }
        }

    }

    private void createCells() {
        log.debug("Creating cells");
        cells = new SudokuCell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new SudokuCell(0);
            }
        }
    }

    private void updateCells(int[][] board) {
        log.debug("Updating cells with values from sudoku");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                int value = board[i][j];
                final int x = i;
                final int y = j;
                editCell = new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            log.debug("Clicked right button in " + this);
                            board[x][y] = 0;
                            cells[x][y].setText(" ");
                            return;
                        }
                        List<Integer> allowedNumbers = new ArrayList<>();
                        for (int i = 1; i <= 9; i++) {
                            if (SudokuUtils.isValidPlacement(board, i, x, y)) {
                                allowedNumbers.add(i);
                            }
                        }
                        if (allowedNumbers.isEmpty()) {
                            JOptionPane.showMessageDialog(cells[x][y], "No valid numbers for this location!!");
                        } else {
                            log.debug("Creating digit selector with numbers: " + allowedNumbers.toString());
                            DigitSelector ds = new DigitSelector(e.getLocationOnScreen(), allowedNumbers);

                            int selected = ds.selectDigit();
                            log.debug("Selected: " + selected);
                            board[x][y] = selected;
                            cells[x][y].setText(selected + "");
                        }
                    }

                };
                if (value == 0) {

                    cells[i][j].setText(" ");
                    cells[i][j].addMouseListener(editCell);

                } else {
                    cells[i][j].setText(value + "");
                    cells[i][j].setForeground(Color.BLUE);
                    cells[i][j].removeMouseListener(editCell);

                }
            }
        }

    }

    public void showSolution(int[][] aBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = aBoard[i][j];
                    cells[i][j].setText(board[i][j] + "");
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
        updateCells(board);
    }
    
    

}
