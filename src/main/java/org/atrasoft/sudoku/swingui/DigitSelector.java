/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lets a user to select a digit from 1 to 9
 * @author Atrament
 */
public class DigitSelector extends JDialog {

    private int selected = 0;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * Creates DigitSelector
     * @param location location on screen where the DigitSelector will be created
     * @param allowedNumbers list of number that will be enabled in the selector. Numbers not in the list will be greyed out.
     */
    public DigitSelector(Point location, List<Integer> allowedNumbers) {
        super();
        initComponents(location, allowedNumbers);
    }

    private void initComponents(Point location, List<Integer> allowedNumbers) {
        log.debug("Creating digit selector with this numbers: " + allowedNumbers.toString());
        setLayout(new MigLayout());
        setModal(true);
        setTitle("Select number");
        for (int i = 1; i <= 9; i++) {
            String constraints = (i % 3 == 0) ? "wrap" : "";
            SudokuCell cell = new SudokuCell(i);
            if (allowedNumbers.contains(i)) {
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        selected = Integer.parseInt(cell.getText());
                        setVisible(false);
                        dispose();
                    }

                });
            } else {
                cell.setEnabled(false);
            }

            add(cell, constraints);
        }
        pack();
        setMinimumSize(getSize());
        Point p = new Point(location.x - (getWidth() / 2), location.y - (getHeight() / 2));
        setLocation(p);
        setResizable(false);
    }

    public int selectDigit() {
        setVisible(true);
        return selected;
    }

}
