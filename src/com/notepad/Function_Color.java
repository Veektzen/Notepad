package com.notepad;

import javax.swing.*;
import java.awt.*;

public class Function_Color {

    GUI gui;

    /**
     * This class implements all the elements under 'Color' option.
     */
    public Function_Color(GUI _gui) {

        this.gui=_gui;
    }

    /**
     * This method changes the font's color between 4 options
     * @param _fontColor the given color
     */
    public void changeFontColor(String _fontColor) {

        switch (_fontColor) {
            case "WhiteF" -> {
                gui.textArea.setForeground(Color.white);
            }
            case "BlackF" -> {
                gui.textArea.setForeground(Color.black);
            }
            case "BlueF" -> {
                gui.textArea.setForeground(Color.blue);
            }
            case "RGBF" -> {
                gui.textArea.setForeground(chooseRGBColor());
            }

        }
    }
    /**
     * This method changes the background's color between 4 options
     * @param _backgroundColor the given color
     */
    public void changeBackgroundColor(String _backgroundColor){

        switch (_backgroundColor) {
            case "WhiteB" -> {
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
            }
            case "BlackB" -> {
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(Color.black);
            }
            case "BlueB" -> {
                gui.window.getContentPane().setBackground(Color.blue);
                gui.textArea.setBackground(Color.blue);
            }
            case "RGBB" -> {
                Color rgb = chooseRGBColor();
                gui.window.getContentPane().setBackground(rgb);
                gui.textArea.setBackground(rgb);
            }
        }
    }

    /**
     * This method generates a dialog which the user chooses between colors
     * @return the color that the user chose in the dialog
     */
    public Color chooseRGBColor() {
        JColorChooser chooser = new JColorChooser();
        Color color = chooser.showDialog(null, "Pick a color",Color.black);
        return color;
    }
}
