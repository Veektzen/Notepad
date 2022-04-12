package com.notepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinds implements KeyListener {
    GUI gui;
    public KeyBinds(GUI _gui){

        this.gui=_gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        // Select Menu option
        // Alt + F = click 'File' option
        if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_F){
            gui.menuFile.doClick();
        }
        // Alt + E = click 'Edit' option
        if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_E){
            gui.menuEdit.doClick();
        }
        // Alt + O = click 'Format' option
        if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_O){
            gui.menuFormat.doClick();
        }
        // Alt + C = click 'Color' option
        if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_C){
            gui.menuColor.doClick();
        }
        // Menu item options
        // Ctrl + N = new file
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_N){
            gui.file.newFile();
        }
        // Ctrl + Shift + N = new window
        if(e.isControlDown() && e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_N){
            gui.file.newWindow();
        }
        // Ctrl + O = open file
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_O){
            gui.file.open();
        }
        // Ctrl + S = save
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S){
            gui.file.save();
        }
        // Ctrl + Shift + S = save as
        if(e.isControlDown() && e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S){
            gui.file.saveAs();
        }
        // Ctrl + W = exit
        if(e.isControlDown()  && e.getKeyCode()==KeyEvent.VK_W){
            gui.file.exit();
        }
        // Ctrl + Z = undo
        if(e.isControlDown()  && e.getKeyCode()==KeyEvent.VK_Z){
            gui.edit.undo();
        }
        // Ctrl + Y = redo
        if(e.isControlDown()  && e.getKeyCode()==KeyEvent.VK_Y){
            gui.edit.redo();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // Nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nothing
    }

}
