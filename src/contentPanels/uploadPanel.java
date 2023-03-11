package contentPanels;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;
import java.util.Collections;
import java.util.List;
import java.awt.datatransfer.DataFlavor;

import Tables.pathTable;
import UI_Formatter.fileUIController;
import UI_Formatter.sideMenu;
import UI_Formatter.colorPalette;
import UI_Formatter.roundPanelBorder;
import Managers.ROIManager;
import UI_Formatter.notification;
import Controller.controller;

public class uploadPanel extends JPanel{
    
    private static pathTable pathTable = new pathTable();
    private static fileUIController fileUIController = new fileUIController();
    public static JPanel middleTop1;

    public uploadPanel(){

        //side panel containing all the icons for the menu
        JPanel sidePanel = new JPanel();
        sidePanel = sideMenu.getSideMenu();

        //top panel, color of the background
        middleTop1 = new JPanel();
        middleTop1.setPreferredSize(new Dimension(800, 100));
        middleTop1.setBackground(colorPalette.background);
        topMiddle(middleTop1);

        //next panel containing the medium color 
        JPanel middleTop2 = new JPanel();
        middleTop2.setPreferredSize(new Dimension(850, 100));
        middleTop2.setBackground(colorPalette.med);
        middleMiddle(middleTop2);

        //middle panel containing most of the content 
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(850, 1000));
        middlePanel.setBackground(colorPalette.background);
        bottomMiddle(middlePanel);

        //setting the panels layout to border layout
        setLayout(new BorderLayout());

        //creatting a wrapper to layer the middle panels 
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        //adding the middle panels 
        wrapperPanel.add(middleTop1);
        wrapperPanel.add(middleTop2);
        wrapperPanel.add(middlePanel);

        //adding panels to format the main panel
        add(wrapperPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);
    }

    /**
     * Constains logout icon
     * @param middleTop1 where content should be added
     */
    private void topMiddle(JPanel middleTop1){

        middleTop1.setLayout(new BorderLayout());

        //topSpace panel 
        JPanel topSpace = new JPanel();
        topSpace.setPreferredSize(new Dimension(1000, 25));
        topSpace.setBackground(colorPalette.background);

        //logout panel
        JPanel logout = new JPanel();
        logout.setPreferredSize(new Dimension(180, 50));
        logout.setBackground(colorPalette.background);
        //adding elements to logout panel
        JLabel logoutLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-logout-rounded-32.png"));
        JLabel logoutText = new JLabel("Logout");
        logoutText.setFont(new Font("Arial", Font.PLAIN, 30));//resizing text within label
        logoutText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        logoutLabel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);//ends program
            }
        });
        logoutText.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);//ends program
            }
        });
        //adding elements to appName panel
        logout.add(logoutLabel);
        logout.add(logoutText);
      
        //adding panels into the main panel
        middleTop1.add(topSpace, BorderLayout.NORTH);
        middleTop1.add(logout, BorderLayout.EAST);
    }

    /**
     * Creating the title of the panel
     * @param middleTop2 where content should be added
     */
    private void middleMiddle(JPanel middleTop2){
        middleTop2.setLayout(new BorderLayout());
        
        //topSpace panel
        JPanel topSpace = new JPanel();
        topSpace.setBackground(colorPalette.med);
        topSpace.setPreferredSize(new Dimension(850, 30));
        
        //upload panel
        JPanel upload = new JPanel();
        upload.setBackground(colorPalette.med);
        upload.setPreferredSize(new Dimension(600, 50));
        //adding elements to panel
        JLabel uploadText = new JLabel("Upload eBay Order Reciept PDFs");
        uploadText.setFont(new Font("Arial", Font.PLAIN, 40));//resizing text within label
        uploadText.setForeground(colorPalette.light);
        //adding elements to upload panel
        upload.add(uploadText);

        //adding panels into the main panel 
        middleTop2.add(topSpace, BorderLayout.NORTH);
        middleTop2.add(upload, BorderLayout.WEST);

    }

    /**
     * Designing the main content of the panel
     * @param middlePanel where content should be added 
     */
    private void bottomMiddle(JPanel middlePanel){

        middlePanel.setLayout(new BorderLayout());

        //topPanel panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(colorPalette.background);
        topPanel.setPreferredSize(new Dimension(1000, 400));
        //adding elements to panel
        JPanel topCenter = new JPanel();
        topCenter = roundPanelBorder.roundBorder();
        topCenter.setBackground(colorPalette.background);
        JLabel centerIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-medical-file-96.png"));
        JLabel centerText1 = new JLabel("Select a PDF order receipt to upload");
        centerText1.setFont(new Font("Arial", Font.PLAIN, 20));
        centerText1.setForeground(colorPalette.light);
        JLabel centerText2 = new JLabel("or drag and drop it here");
        centerText2.setFont(new Font("Arial", Font.PLAIN, 15));
        centerText2.setForeground(Color.GRAY);
        //adding elements to center panel
        topCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        topCenter.add(centerIcon, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        topCenter.add(centerText1, gbc);
        gbc.gridy = 2;
        topCenter.add(centerText2, gbc);

        //creating a DropTarget object and set it on the topPanel
        DropTarget dropTarget = new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
        
                    for (File file : droppedFiles) {
                        String fileName = file.getName();
                        int dotIndex = fileName.lastIndexOf(".");
                        String extension = fileName.substring(dotIndex + 1);
        
                        if (extension.equalsIgnoreCase("pdf")) {
                            ROIManager.readInDroppedFiles(Collections.singletonList(file));
                        } else {
                            notification.showNotificationPopup(controller.getFrame(), "Incorrect File Format", false);
                        }
                    }
                    uploadSucess();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        topPanel.setDropTarget(dropTarget);

        //adding function to panel incase user decides to upload pdf files instead of dragging and dropping 
        topCenter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ROIManager.readInFiles();
                uploadSucess();
            }
        });

        //adding elements to topPanel panel
        topPanel.add(topCenter, BorderLayout.CENTER);

        //bottomPanel panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        bottomPanel.setBackground(Color.BLUE);
        bottomPanel.setPreferredSize(new Dimension(1000, 400));
        fileUIController.setBackground(colorPalette.background);
        fileUIController.changeCard("No Files");
        //adding elements to bottomPanel panel
        bottomPanel.add(fileUIController, BorderLayout.CENTER);

        //adding panels into the main panel
        middlePanel.add(topPanel, BorderLayout.NORTH);
        middlePanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Returning pathTable to be used in other classes
     * @return pathTable
     */
    public static pathTable getTable(){
        return pathTable;
    }

    /**
     * Calling instance of fileUIController to change the card
     */
    public static void changeToPathFiles(){
        fileUIController.changeCard("Path Files");
    }

    /**
     * Calling instance of fileUIController to change the card
     */
    public static void changeToNoFiles(){
        fileUIController.changeCard("No Files");
    }

    /**
     * Changing the no files uploaded panel to display the paths of the files that were uploaded 
     */
    private static void uploadSucess(){
        //ensuring that information was sucessfully uploaded 
        if(pathTable.returnRowCount() > 0){
            changeToPathFiles();
            roiPanel.changeToROITable();
        } 
    }
}