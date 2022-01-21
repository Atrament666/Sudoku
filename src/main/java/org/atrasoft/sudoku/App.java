/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku;

import javax.swing.SwingUtilities;
import org.atrasoft.sudoku.swingui.Frame;

/**
 *
 * @author Atrament
 */
public class App {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Frame().setVisible(true);
        });
    }
    
}
