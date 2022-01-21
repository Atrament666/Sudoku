/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.atrasoft.sudoku.swingui.actions;

import java.awt.event.ActionEvent;
import static javax.swing.Action.NAME;
import org.atrament.ManagedAction;
import org.atrasoft.sudoku.swingui.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Atrament
 */
public class ExitAction extends ManagedAction{
    
    private Frame frame;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public ExitAction(Frame mainWindow) {
        super(mainWindow);
        frame = mainWindow;
        initComponent();
    }

    private void initComponent() {
        putValue(NAME, "Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.debug("Exiting");
        System.exit(0);
    }
    
}
