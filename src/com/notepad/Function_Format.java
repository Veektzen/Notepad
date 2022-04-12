package com.notepad;

import java.awt.*;

/**
 * This class implements all the elements under 'Format' option.
 */
public class Function_Format {

    GUI gui;
    Font arial,comicSansMS, timesNewRoman;
    String selectedFont;

    /**
     * The only constructor
     * @param _gui the gui which the 'Format' option resides
     */
    public Function_Format(GUI _gui){
        this.gui=_gui;
    }

    /**
     * This method implements the 'Word wrap' option
     */
    public void wordWrap(){

        if(gui.isWrapped){
            gui.isWrapped=false;
            // Without line wrap
            gui.textArea.setLineWrap(false);
            // Without word wrap
            gui.textArea.setWrapStyleWord(false);
            // Set text
            gui.iWrap.setText("Word Wrap: Off");
        }else{
            gui.isWrapped=true;
            // With line wrap
            gui.textArea.setLineWrap(true);
            // With word wrap because just line wrap
            // can brake one word to 2 lines
            gui.textArea.setWrapStyleWord(true);
            // Set text
            gui.iWrap.setText("Word Wrap: On");
        }
    }

    /**
     * This method creates a font
     * @param _fontSize the size of the font
     */
    public void createFont(int _fontSize){

        // Initialize fonts
        arial = new Font("Arial", Font.PLAIN, _fontSize);
        comicSansMS = new Font("Comic Sans MS", Font.PLAIN, _fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, _fontSize);

        setFont(selectedFont);
    }

    /**
     * This method sets the font in the text area
     * @param _font the fonts that will be set
     */
    public void setFont(String _font){

        selectedFont = _font;

        switch (selectedFont) {
            case "Arial" -> gui.textArea.setFont(arial);
            case "Comic Sans MS" -> gui.textArea.setFont(comicSansMS);
            case "Times New Roman" -> gui.textArea.setFont(timesNewRoman);
        }
    }
}
