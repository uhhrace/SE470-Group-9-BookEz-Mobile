package UI_Formatter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import Tables.roiTable;
import Tables.tableModification;
import Tables.roiHeaderRenderer;
import contentPanels.roiPanel;
import contentPanels.uploadPanel;
import Managers.ROIManager;
import Controller.controller;

public class roiPanelContent extends JPanel{

    private roiTable roi = new roiTable();

    public roiPanelContent(){

        setLayout(new BorderLayout());
        setBackground(colorPalette.background);

        //topPanel panel houses the roi table options 
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(colorPalette.background);
        topPanel.setPreferredSize(new Dimension(800, 250));

        topPanel.add(optionsPanel(), BorderLayout.CENTER);

        //bottomPanel panel houses the roi table 
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(25, 20, 10, 20));
        bottomPanel.setBackground(colorPalette.background);
        //adding elements to panel
        JTable table = roi.getTable();//obtaining the abstract table
        table.getTableHeader().setDefaultRenderer(new roiHeaderRenderer());
        tableModification.roiColumnResizing(table);
        tableModification.cellBackGroundColor(table);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(tableModification.getTableBorder());

        //adding elements into the bottomPanel
        bottomPanel.add(tableScroll);

        //adding panels into the main panel
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel optionsPanel(){
        JPanel export = new JPanel(new FlowLayout(FlowLayout.LEFT));
        export.setBackground(colorPalette.background);
        export.setPreferredSize(new Dimension(250, 50));
        JLabel exportIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-export-32.png"));
        JLabel exportText= new JLabel("Export ROI Table");
        exportText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        exportText.setForeground(colorPalette.light);
        export.add(exportIcon);
        export.add(exportText);
        //adding mouse listeners to the jlabels 
        exportIcon.addMouseListener(new MouseListener("Export Files"));
        exportText.addMouseListener(new MouseListener("Export Files"));

        JPanel delete = new JPanel(new FlowLayout(FlowLayout.LEFT));
        delete.setBackground(colorPalette.background);
        delete.setPreferredSize(new Dimension(250, 50));
        JLabel deleteIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-clear-symbol-32.png"));
        JLabel deleteText= new JLabel("Delete Selected Files");
        deleteText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        deleteText.setForeground(colorPalette.light);
        delete.add(deleteIcon);
        delete.add(deleteText);
        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roiPanel.getTable().deleteRows();
                checkFiles();
            }
        });
        deleteText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roiPanel.getTable().deleteRows();
                checkFiles();
            }
        });

        JPanel clear = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clear.setBackground(colorPalette.background);
        clear.setPreferredSize(new Dimension(250, 50));
        JLabel clearIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-empty-trash-32 (1).png"));
        JLabel clearText= new JLabel("Clear All Files");
        clearText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        clearText.setForeground(colorPalette.light);
        clear.add(clearIcon);
        clear.add(clearText);

        clearIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadPanel.getTable().clear();
                notification.showNotificationPopup(controller.getFrame(), "Successfully Cleared Files", true);
                uploadPanel.changeToNoFiles();
                roiPanel.changeToNoFiles();
                ROIManager.resetID();//resetting identifyer 
            }
        });
        clearText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadPanel.getTable().clear();
                notification.showNotificationPopup(controller.getFrame(), "Successfully Cleared Files", true);
                uploadPanel.changeToNoFiles();
                roiPanel.changeToNoFiles();
                ROIManager.resetID();//resetting identifyer 
            }
        });

        //adding the elements into the topPanel
        JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        options.setBackground(colorPalette.background);
        options.add(export);
        options.add(clear);
        options.add(delete);

        return options;
    }

    /**
     * Function is only used with the delete option. Checks if all files were deleted and acts accordingly to give user feedback
     */
    private void checkFiles(){
        //checking if all files were deleted
        if (uploadPanel.getTable().empty()){
            notification.showNotificationPopup(controller.getFrame(), "All Files Have Been Deleted", true);
            uploadPanel.changeToNoFiles();
            roiPanel.changeToNoFiles();
            ROIManager.resetID();//resetting identifyer
        }
    }
}
