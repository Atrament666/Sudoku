/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui;

import java.awt.CardLayout;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import net.miginfocom.swing.MigLayout;
import org.atrament.ActionManager;
import org.atrament.ManagedAction;
import org.atrasoft.sudoku.SudokuUtils;
import org.atrasoft.sudoku.swingui.actions.ExitAction;
import org.atrasoft.sudoku.swingui.actions.NewGameAction;
import org.atrasoft.sudoku.swingui.actions.SolveAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Atrament
 */
public class Frame extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(Frame.class);
    private SudokuBoard board;
    private JPanel boardPanel;
    private final ActionManager<ManagedAction, Frame> am;

    private static final int LEVEL = 17;

    public Frame() {
        am = new ActionManager<>(this);
        initComponents();
    }

    private void initComponents() {
        setLayout(new MigLayout("align 50% 50%", "[][]", "[]20[]"));

        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new CardLayout());

        //new BackgroundGenerator().execute();
        board = new SudokuBoard();
        
        boardPanel.add(board, "Board");
        add(boardPanel, "span 2 1, wrap");

        JMenuBar mainMenu = new JMenuBar();
        // file menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(am.getAction(ExitAction.class));

        //game menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(am.getAction(NewGameAction.class));
        gameMenu.add(am.getAction(SolveAction.class));

        mainMenu.add(fileMenu);
        mainMenu.add(gameMenu);
        setJMenuBar(mainMenu);

        pack();
        setMinimumSize(getSize());
        setResizable(false);
        setLocationRelativeTo(null);

    }

    

    public void runSolver() {
        new BackgroundSolver().execute();
    }

    public void createNewGame() {
        log.debug("Creating new sudoku");
        new BackgroundGenerator().execute();
    }

    private class BackgroundSolver extends SwingWorker<int[][], Void> {

        @Override
        protected int[][] doInBackground() throws Exception {
            log.debug("Solving sudoku in background");
            return SudokuUtils.solveSudoku(board.getBoard());

        }

        @Override
        protected void process(List<Void> chunks) {
            super.process(chunks);
        }

        @Override
        protected void done() {
            super.done();
            try {
                int[][] solution = get();
                if (solution != null) {
                    board.showSolution(get());
                } else {
                    JOptionPane.showMessageDialog(board, "There is no solution for this board. Try removing some numbers.");
                }
                
            } catch (InterruptedException | ExecutionException ex) {
                log.debug(ex.getMessage());
            }
        }
    }

    private class BackgroundGenerator extends SwingWorker<int[][], Void> {

        @Override
        protected int[][] doInBackground() throws Exception {
            boardPanel.removeAll();
            log.debug("Generating sudoku in background");
            return SudokuUtils.generateSudoku(LEVEL);

        }

        @Override
        protected void process(List<Void> chunks) {
            super.process(chunks);
        }

        @Override
        protected void done() {
            super.done();
            try {
                log.debug("Finished generating in background");
                board = new SudokuBoard(get());
                boardPanel.add(board);
                CardLayout cl = (CardLayout) boardPanel.getLayout();
                cl.last(boardPanel);
                pack();
            } catch (InterruptedException | ExecutionException ex) {
                log.debug(ex.getMessage());
            }
        }

    }
}
