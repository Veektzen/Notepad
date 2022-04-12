package com.notepad;

import java.awt.*;
/**
 * This class implements all the elements under 'Edit' option.
 */
public class Function_Edit {

    GUI gui;

    /**
     * The only constructor
     * @param _gui the gui which the 'Format' option resides
     */
    public Function_Edit(GUI _gui){

        this.gui=_gui;
    }

    /**
     * This method implements the 'undo' option
     */
    public void undo(){
        try {
            gui.um.undo();
        }catch (Exception e){
            // Windows Sound
            final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
            if (runnable != null) runnable.run();
            // Console output
            System.out.println("There's nothing to undo");
        }
    }

    /**
     * This method implements the 'redo' option
     */
    public void redo(){
        try {
            gui.um.redo();
        }catch (Exception e){
            // Windows Sound
            final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
            if (runnable != null) runnable.run();
            // Console output
            System.out.println("There's nothing to redo");
        }
    }
}
