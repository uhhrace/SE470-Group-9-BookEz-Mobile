package UI_Formatter;

import java.awt.*;
import javax.swing.*;

public class sideMenu {

    /**
     * Side menu containing all program icons 
     * @param sidePanel main panel where all elements will be added 
     */
    public static JPanel getSideMenu(){

        //side panel containing all the icons for the menu
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(200, 1000));
        sidePanel.setBackground(colorPalette.dark);

        //appName panel
        JPanel appName = new JPanel();
        JPanel appUnderLine = new JPanel();
        appName.setPreferredSize(new Dimension(200, 50));
        appName.setBackground(colorPalette.dark);
        appUnderLine.setPreferredSize(new Dimension(120, 20));
        appUnderLine.setBackground(colorPalette.light);
        //adding elements to appName panel
        JLabel appText = new JLabel("BookEz");
        appText.setFont(new Font("Arial", Font.PLAIN, 32));//resizing text within label
        appText.setForeground(colorPalette.light);
        //adding elements to appName panel
        appName.add(appText);
        appName.add(appUnderLine);

        //settings icon panel
        JPanel settings = new JPanel(new FlowLayout(FlowLayout.LEFT));
        settings.setPreferredSize(new Dimension(200, 80));
        settings.setBackground(colorPalette.dark);
        //adding elements to menu panel
        JLabel settingsLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-automatic-32.png"));
        JLabel settingsText = new JLabel("Settings");
        settingsText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        settingsText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        settingsLabel.addMouseListener(new MouseListener("Settings"));
        settingsText.addMouseListener(new MouseListener("Settings"));
        //adding elements to menu panel
        settings.add(settingsLabel);
        settings.add(settingsText);

        //home icon panel
        JPanel home = new JPanel(new FlowLayout(FlowLayout.LEFT));
        home.setPreferredSize(new Dimension(200, 100));
        home.setBackground(colorPalette.dark);
        //adding elements to menu panel
        JLabel homeLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-home-page-32.png"));
        JLabel homeText = new JLabel("Home");
        homeText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        homeText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        homeLabel.addMouseListener(new MouseListener("Homescreen"));
        homeText.addMouseListener(new MouseListener("Homescreen"));
        //adding elements to home panel
        home.add(homeLabel);
        home.add(homeText);

        //upload icon panel
        JPanel upload = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upload.setPreferredSize(new Dimension(200, 100));
        upload.setBackground(colorPalette.dark);
        //adding elements to upload panel
        JLabel uploadLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-medical-file-32.png"));
        JLabel uploadText = new JLabel("Upload");
        uploadText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        uploadText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        uploadLabel.addMouseListener(new MouseListener("Upload Photos"));
        uploadText.addMouseListener(new MouseListener("Upload Photos"));
        //adding elements to upload panel
        upload.add(uploadLabel);
        upload.add(uploadText);

        //table icon panel
        JPanel table = new JPanel(new FlowLayout(FlowLayout.LEFT));
        table.setPreferredSize(new Dimension(200, 100));
        table.setBackground(colorPalette.dark);
        //adding elements to table panel
        JLabel tableLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-table-32.png"));
        JLabel tableText = new JLabel("ROI Table");
        tableText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        tableText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        tableLabel.addMouseListener(new MouseListener("ROI Table"));
        tableText.addMouseListener(new MouseListener("ROI Table"));

        //adding elements to table panel
        table.add(tableLabel);
        table.add(tableText);

        //export icon panel
        JPanel export = new JPanel(new FlowLayout(FlowLayout.LEFT));
        export.setPreferredSize(new Dimension(200, 100));
        export.setBackground(colorPalette.dark);
        //adding elements to export panel
        JLabel exportLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-export-32.png"));
        JLabel exportText = new JLabel("Export");
        exportText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        exportText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        exportLabel.addMouseListener(new MouseListener("Export Files"));
        exportText.addMouseListener(new MouseListener("Export Files"));
        //adding elements to export panel
        export.add(exportLabel);
        export.add(exportText);

        //adding a space to seperate account from the rest of the icons
        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(200, 300));
        space.setBackground(colorPalette.dark);

        //account icon panel
        JPanel account = new JPanel(new FlowLayout(FlowLayout.LEFT));
        account.setPreferredSize(new Dimension(200, 100));
        account.setBackground(colorPalette.dark);
        //adding elements to account panel
        JLabel accountLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-male-user-32.png"));
        JLabel accountText = new JLabel("Account");
        accountText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        accountText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        accountLabel.addMouseListener(new MouseListener("Account"));
        accountText.addMouseListener(new MouseListener("Account"));
        //adding elements to account panel
        account.add(accountLabel);
        account.add(accountText);

        //leftCushion panel for icon spacing
        JPanel leftCushion = new JPanel();
        leftCushion.setLayout(new FlowLayout(FlowLayout.LEFT, 35, 0));
        leftCushion.setPreferredSize(new Dimension(200, 930));
        leftCushion.setBackground(colorPalette.dark);

        leftCushion.add(settings);
        leftCushion.add(home);
        leftCushion.add(upload);
        leftCushion.add(table);
        leftCushion.add(export);
        leftCushion.add(space);
        leftCushion.add(account);

        //adding panels into the main panel
        sidePanel.add(appName);
        sidePanel.add(leftCushion);

        return sidePanel;//returning the side panel menu
    }
}
