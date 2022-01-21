/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui.actions;

import java.awt.event.ActionEvent;
import org.atrament.ManagedAction;
import org.atrasoft.sudoku.swingui.Frame;

/**
 *
 * @author Atrament
 */
public class SolveAction extends ManagedAction{

    public SolveAction(Frame mainWindow) {
        super(mainWindow);
        initComponents();
    }

    private void initComponents() {
        putValue(NAME, "Solve");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       ((Frame)mainWindow).runSolver();
    }
    
}
