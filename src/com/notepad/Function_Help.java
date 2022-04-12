package com.notepad;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Function_Help {

    GUI gui;


    public Function_Help(GUI _gui) {

        this.gui = _gui;
    }

    public void about() {

        createAboutDialog();
    }

    public void createAboutDialog() {

        // Create padding
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        // Initialize dialog and its settings
        JDialog dialog = new JDialog(gui.window, "About Notepad+++", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(500, 350);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setBackground(Color.white);
        // set frame up left icon
        ImageIcon frameImg = new ImageIcon("data_repo/Notepad.png");
        dialog.setIconImage(frameImg.getImage());
        // Initialize panels
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(padding);
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(padding);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setBorder(padding);
        // Insert image to the panel
        try {
            BufferedImage myPicture = ImageIO.read(new File("data_repo/WindowsImage.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
            imageLabel.setToolTipText("Windows Version");
            imagePanel.add(imageLabel, BorderLayout.CENTER);
            // Or just insert text if the file is not found
        } catch (Exception e) {
            imagePanel.add(new JLabel("Windows Version"), BorderLayout.CENTER);
            System.out.println("Windows image not found");
        }

        JLabel informationLabel = new JLabel("""
                <html>
                    <body>
                        Manos T
                        <br>
                        <br>
                        Version: 1.0
                        <br>
                        <br>
                        This version of Notepad+++ made with love and compassion
                        </body>
                </html>
                    """);
        textPanel.add(informationLabel, BorderLayout.CENTER);

        // Initialize button
        JButton button = new JButton("OK");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(button, BorderLayout.EAST);

        // Add panels to the dialog
        dialog.add(imagePanel, BorderLayout.NORTH);
        dialog.add(textPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set default button when pressing 'Enter'
        JRootPane rootPane = SwingUtilities.getRootPane(button);
        rootPane.setDefaultButton(button);

        dialog.setVisible(true);
    }
}
