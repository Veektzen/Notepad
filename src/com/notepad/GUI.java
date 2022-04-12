package com.notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This class contains all the GUI of this application
 */
public class GUI implements ActionListener {

    // Main frame
    JFrame window;
    // Text area components
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean isWrapped = false;
    // Bottom counter
    JPanel wordCounterPanel;
    JLabel wordCounterLabel;
    // Top menu bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor, menuHelp;
    // File menu
    JMenuItem iNew, iNewWindow, iOpen, iSave, iSaveAs, iExit;
    // Edit menu
    JMenuItem iUndo, iRedo;
    // Format menu has JMenuItems and JMenus
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;
    // Color menu has JMenuItems and JMenus
    JMenu fontColor, backgroundColor;
    JMenuItem iBgColor1, iBgColor2, iBgColor3, iBgColorRGB, iFontColor1, iFontColor2, iFontColor3, iFontColorRGB;
    // Help Menu
    JMenuItem iAbout;

    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);
    Function_Help help = new Function_Help(this);

    KeyBinds keybinds = new KeyBinds(this);

    UndoManager um = new UndoManager();

    /**
     * This is the only constructor
     */
    public GUI() {

        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();
        createHelpMenu();
        createCounterArea();

        // Default font and color
        format.selectedFont = "Arial";
        format.createFont(16);
        color.changeFontColor("BlackF");
        color.changeBackgroundColor("WhiteB");

        window.setVisible(true);
    }

    /**
     * This method creates the main Window of the application
     */
    public void createWindow() {

        // Initialize window
        window = new JFrame();
        // Give title to the window
        window.setTitle("Notepad+++");
        // Set layout to the window
        window.setLayout(new BorderLayout());
        // When you hit X, the program closes completely
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Set the size of the window
        window.setSize(800, 600);
        // Frame is displayed in the middle of the screen
        window.setLocationRelativeTo(null);
        // set frame up left icon
        ImageIcon frameImg = new ImageIcon("data_repo/Notepad.png");
        window.setIconImage(frameImg.getImage());
    }

    /**
     * This method creates the text area
     */
    public void createTextArea() {

        // Initialize text area
        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        // We want to make undo and redo on the text area
        // so we use UndoManager's methods and undoable Listener
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        // We want to add key binds on the text area in order to use
        // some of the menu options from keyboard like Ctrl+S -> Save
        textArea.addKeyListener(keybinds);
        // Add Document Listener to the text area so that the Character and word counter will update automatically
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String newText = "Characters: " + textArea.getText().length() + " Words: " + textArea.getText().split("\\s").length +" Lines: "+textArea.getText().split("\n").length;
                wordCounterLabel.setText(newText);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String newText = "Characters: " + textArea.getText().length() + " Words: " + textArea.getText().split("\\s").length +" Lines: "+textArea.getText().split("\n").length;
                wordCounterLabel.setText(newText);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        // Initialize scroll pane
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Set empty border for scroll pane
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // Add scroll pane to the window
        window.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * This method creates the counter area that counts characters,words and lines of the text area
     */
    public void createCounterArea() {

        // Initialize Counter panel
        wordCounterPanel = new JPanel(new BorderLayout());
        // Set empty border for panel
        wordCounterPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        // Set background color for panel
        wordCounterPanel.setBackground(Color.lightGray);
        // Add panel to the south of window
        window.add(wordCounterPanel, BorderLayout.SOUTH);
        // Initialize label
        wordCounterLabel = new JLabel("Characters: 0 Words: 0 Lines: 0");
        // Set font color to black
        wordCounterLabel.setForeground(Color.black);
        // Change font
        wordCounterLabel.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        // Add label to the panel
        wordCounterPanel.add(wordCounterLabel, BorderLayout.SOUTH);
    }

    /**
     * This method creates the menu bar of the window
     */
    public void createMenuBar() {

        // Initialize menu bar
        menuBar = new JMenuBar();
        // Add menu bar to the window
        window.setJMenuBar(menuBar);
        // Initialize menu option file, edit, format and color
        // File
        menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menuFile);
        // Edit
        menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menuEdit);
        // Format
        menuFormat = new JMenu("Format");
        menuFormat.setMnemonic(KeyEvent.VK_O);
        menuBar.add(menuFormat);
        // Color
        menuColor = new JMenu("Color");
        menuColor.setMnemonic(KeyEvent.VK_C);
        menuBar.add(menuColor);
        // Help
        menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menuHelp);
    }

    /**
     * This method creates the 'File' option on menu bar
     */
    public void createFileMenu() {

        // Initialize menu items for 'File' option
        // New
        iNew = new JMenuItem("New");
        KeyStroke ctrlNKeyStroke = KeyStroke.getKeyStroke("control N");
        iNew.setAccelerator(ctrlNKeyStroke);
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);
        // New Window
        iNewWindow = new JMenuItem("New Window");
        KeyStroke ctrlShiftNKeyStroke = KeyStroke.getKeyStroke("control shift N");
        iNewWindow.setAccelerator(ctrlShiftNKeyStroke);
        iNewWindow.addActionListener(this);
        iNewWindow.setActionCommand("New Window");
        menuFile.add(iNewWindow);
        // Open
        iOpen = new JMenuItem("Open");
        KeyStroke ctrlOKeyStroke = KeyStroke.getKeyStroke("control O");
        iOpen.setAccelerator(ctrlOKeyStroke);
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);
        // Save
        iSave = new JMenuItem("Save");
        KeyStroke ctrlSKeyStroke = KeyStroke.getKeyStroke("control S");
        iSave.setAccelerator(ctrlSKeyStroke);
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);
        // Save as
        iSaveAs = new JMenuItem("Save As...");
        KeyStroke ctrlShiftSKeyStroke = KeyStroke.getKeyStroke("control shift S");
        iSaveAs.setAccelerator(ctrlShiftSKeyStroke);
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);
        // Exit
        iExit = new JMenuItem("Exit");
        KeyStroke ctrlWKeyStroke = KeyStroke.getKeyStroke("control W");
        iExit.setAccelerator(ctrlWKeyStroke);
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    /**
     * This method creates the 'Edit' option on menu bar
     */
    public void createEditMenu() {

        // Edit>Undo
        iUndo = new JMenuItem("Undo");
        KeyStroke ctrlZKeyStroke = KeyStroke.getKeyStroke("control Z");
        iUndo.setAccelerator(ctrlZKeyStroke);
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);
        // Edit>Redo
        iRedo = new JMenuItem("Redo");
        KeyStroke ctrlYKeyStroke = KeyStroke.getKeyStroke("control Y");
        iRedo.setAccelerator(ctrlYKeyStroke);
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    /**
     * This method creates the 'Format' option on menu bar
     */
    public void createFormatMenu() {

        // Format>Wrap On/Off
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);
        // Format>Font
        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);
        // Format>Font>Different Fonts
        // Arial
        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);
        // Comic Sans MS
        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);
        // Times New Roman
        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);
        // Format>Font Size
        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);
        // Format>Font Size>Different Sizes
        // Size 8
        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size8");
        menuFontSize.add(iFontSize8);
        // Size 12
        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("size12");
        menuFontSize.add(iFontSize12);
        // Size 16
        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size16");
        menuFontSize.add(iFontSize16);
        // Size 20
        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        menuFontSize.add(iFontSize20);
        // Size 24
        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        menuFontSize.add(iFontSize24);
        // Size 28
        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("size28");
        menuFontSize.add(iFontSize28);
    }

    /**
     * This method creates the 'Color' option on menu bar
     */
    public void createColorMenu() {

        // Color>Font Color
        fontColor = new JMenu("Font Color");
        menuColor.add(fontColor);
        // Color>Font Color>Different Colors
        // White
        iFontColor1 = new JMenuItem("White");
        iFontColor1.addActionListener(this);
        iFontColor1.setActionCommand("WhiteF");
        fontColor.add(iFontColor1);
        // Black
        iFontColor2 = new JMenuItem("Black");
        iFontColor2.addActionListener(this);
        iFontColor2.setActionCommand("BlackF");
        fontColor.add(iFontColor2);
        // Blue
        iFontColor3 = new JMenuItem("Blue");
        iFontColor3.addActionListener(this);
        iFontColor3.setActionCommand("BlueF");
        fontColor.add(iFontColor3);
        // RGB
        iFontColorRGB = new JMenuItem("More...");
        iFontColorRGB.addActionListener(this);
        iFontColorRGB.setActionCommand("RGBF");
        fontColor.add(iFontColorRGB);
        // Color>Background Color
        backgroundColor = new JMenu("Background Color");
        menuColor.add(backgroundColor);
        // Color>Background Color>Different Colors
        // White
        iBgColor1 = new JMenuItem("White");
        iBgColor1.addActionListener(this);
        iBgColor1.setActionCommand("WhiteB");
        backgroundColor.add(iBgColor1);
        // Black
        iBgColor2 = new JMenuItem("Black");
        iBgColor2.addActionListener(this);
        iBgColor2.setActionCommand("BlackB");
        backgroundColor.add(iBgColor2);
        // Blue
        iBgColor3 = new JMenuItem("Blue");
        iBgColor3.addActionListener(this);
        iBgColor3.setActionCommand("BlueB");
        backgroundColor.add(iBgColor3);
        // RGB
        iBgColorRGB = new JMenuItem("More...");
        iBgColorRGB.addActionListener(this);
        iBgColorRGB.setActionCommand("RGBB");
        backgroundColor.add(iBgColorRGB);
    }

    /**
     * This method creates the 'Help' option on menu bar
     */
    public void createHelpMenu() {
        //Help>About
        iAbout = new JMenuItem("About");
        iAbout.addActionListener(this);
        iAbout.setActionCommand("About");
        menuHelp.add(iAbout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Get String action command and store it to variable 'command'
        String command = e.getActionCommand();

        switch (command) {
            // File
            case "New" -> file.newFile();
            case "New Window" -> file.newWindow();
            case "Open" -> file.open();
            case "Save" -> file.save();
            case "SaveAs" -> file.saveAs();
            case "Exit" -> file.exit();
            // Edit
            case "Undo" -> edit.undo();
            case "Redo" -> edit.redo();
            // Format
            case "Word Wrap" -> format.wordWrap();
            // Format>Font
            case "Arial", "Comic Sans MS", "Times New Roman" -> format.setFont(command);
            // Format>Font Size
            case "size8" -> format.createFont(8);
            case "size12" -> format.createFont(12);
            case "size16" -> format.createFont(16);
            case "size20" -> format.createFont(20);
            case "size24" -> format.createFont(24);
            case "size28" -> format.createFont(28);
            // Color>Font Color
            case "WhiteF", "BlackF", "BlueF","RGBF" -> color.changeFontColor(command);
            // Color>Background Color
            case "WhiteB", "BlackB", "BlueB","RGBB" -> color.changeBackgroundColor(command);
            case "About" -> help.about();

        }
    }
}
