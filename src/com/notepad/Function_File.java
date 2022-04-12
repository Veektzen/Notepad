package com.notepad;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This class implements all the elements under 'File' option.
 */
public class Function_File {

    GUI gui;
    String fileName;
    String fileAddress;

    /**
     * The only constructor
     * @param _gui the gui which the 'File' option resides
     */
    public Function_File(GUI _gui){

        this.gui=_gui;
    }

    /**
     * This method basically erases everything from text area
     */
    public void newFile(){

        // Clear text area
        gui.textArea.setText("");
        // Set title of the window
        gui.window.setTitle("New Window - Notepad+++");
        // Reset file name and address
        fileName = null;
        fileAddress = null;
    }

    /**
     * This method creates a new GUI
     */
    public void newWindow(){
        // Open new window
        new GUI();
    }

    /**
     * This method displays a dialog for the user to choose a text file to open in the text area
     */
    public void open(){

        // Initialize a file dialog to display
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        // Set it to visible = true
        fd.setVisible(true);
        // Get File's details
        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            // Set title of the window
            gui.window.setTitle(fileName+" - Notepad+++");
        }

        try{
            // In order to read the content of the file we need
            // the address and the name of the file which we got earlier
            BufferedReader br = new BufferedReader(new FileReader(fileAddress+fileName));
            // Clear text area
            gui.textArea.setText("");
            // Initialize a null string
            String line = null;
            // While there's a next line in the .txt
            while((line = br.readLine())!=null){
                // Append line to my text area
                gui.textArea.append(line+"\n");
            }
            // Once it is done close buffered reader
            br.close();
        }catch(Exception e){
            System.out.println("File not opened");
        }
    }

    /**
     * This method saves the current text area
     * If there isn't any file saved, the user chooses from a dialog a name for the file
     */
    public void save(){
        if(fileName == null){
            saveAs();
        }else{
            try{
                // In order to write the content of our text area we need
                // the address and the name of the file which we got earlier
                FileWriter fw = new FileWriter(fileAddress+fileName);
                // Write the text of our text area to the given file
                fw.write(gui.textArea.getText());
                // Set title of the window
                gui.window.setTitle(fileName+" - Notepad+++");
                // Once it is done close file writer
                fw.close();
            }
            catch (Exception e){
                System.out.println("File not saved");
            }
        }
    }

    /**
     * The user chooses from a dialog a name for the file that will be saved
     */
    public void saveAs(){

        // Initialize a file dialog to display
        FileDialog fd = new FileDialog(gui.window, "Save as", FileDialog.SAVE);
        // Set it to visible = true
        fd.setVisible(true);
        // Get File's details
        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            // Set title of the window
            gui.window.setTitle(fileName+" - Notepad+++");
        }
        try{
            // In order to write the content of our text area we need
            // the address and the name of the file which we got earlier
            FileWriter fw = new FileWriter(fileAddress+fileName);
            // Write the text of our text area to the given file
            fw.write(gui.textArea.getText());
            // Once it is done close file writer
            fw.close();
        }catch(Exception e){
            System.out.println("File not saved");
        }
    }

    /**
     * This method exits from the current gui
     */
    public void exit(){
        gui.window.dispose();
    }
}
