/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui.actions;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.atrament.ManagedAction;
import org.atrasoft.sudoku.swingui.Frame;

/**
 *
 * @author Atrament
 */
public class NewGameAction extends ManagedAction {

    public NewGameAction(Frame mainWindow) {
        super(mainWindow);
        initComponents();
    }

    private void initComponents() {
        putValue(NAME, "New game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((Frame) mainWindow).createNewGame();
    }
    
}
