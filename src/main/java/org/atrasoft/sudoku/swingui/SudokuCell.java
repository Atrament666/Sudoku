/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Atrament
 */
public class SudokuCell extends JLabel{

    private final Color lightBrown = new Color(240, 180, 140);
    
    SudokuCell(int number) {
        super();
        initComponents(number);
    }

    private void initComponents(int number) {
        setOpaque(true);
        setBackground(lightBrown);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        setMinimumSize(new Dimension(50, 50));
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setText(number+"");
    }
    
}
