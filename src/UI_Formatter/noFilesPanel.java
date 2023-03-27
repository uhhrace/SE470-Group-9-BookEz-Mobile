package UI_Formatter;

import java.awt.*;
import javax.swing.*;

public class noFilesPanel extends JPanel{
    /**
     * Panel consisting of an icoan and text in the center that say no files have been uploaded 
     */
    public noFilesPanel(){
        setBackground(colorPalette.background);
        setLayout(new GridBagLayout());

        //creating elements 
        JLabel emptyIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-empty-box-96.png"));
        JLabel emptyText = new JLabel("No files have been uploaded");
        emptyText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        emptyText.setForeground(colorPalette.light);
        //adding elements to panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(emptyIcon, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(emptyText, gbc);
        gbc.gridy = 2;

    }
}
