import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class roiPanel extends JPanel{

    private static roiTable roiTable = new roiTable();
    private static fileUIController fileUIController = new fileUIController();
    
    public roiPanel(){

        //side panel containing all the icons for the menu
        JPanel sidePanel = new JPanel();
        sidePanel = sideMenu.getSideMenu();

        //top panel, color of the background
        JPanel middleTop1 = new JPanel();
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
        JLabel logoutLabel = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-logout-rounded-32.png"));
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
        upload.setPreferredSize(new Dimension(500, 50));
        //adding elements to panel
        JLabel uploadText = new JLabel("Return on Investment Table");
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
        fileUIController.changeCard("No Files");

        //topPanel panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(colorPalette.background);
        topPanel.setPreferredSize(new Dimension(1000, 250));
        //adding elements to panel
        JPanel topCenter = new JPanel();
        topCenter.setBackground(colorPalette.background);
        //adding elements to center panel

        JPanel delete = new JPanel(new FlowLayout(FlowLayout.LEFT));
        delete.setBackground(colorPalette.background);
        delete.setPreferredSize(new Dimension(250, 50));
        JLabel deleteIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-clear-symbol-32.png"));
        JLabel deleteText= new JLabel("Delete Selected Files");
        deleteText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        deleteText.setForeground(colorPalette.light);
        delete.add(deleteIcon);
        delete.add(deleteText);
        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roiTable.deleteRows();
                changePanel();
            }
        });
        deleteText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roiTable.deleteRows();
                changePanel();
            }
        });

        topCenter.add(delete);

        //adding elements to topPanel panel
        topPanel.add(topCenter, BorderLayout.CENTER);

        //bottomPanel panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        bottomPanel.setBackground(colorPalette.background);
        bottomPanel.setPreferredSize(new Dimension(1000, 530));
        fileUIController.setBackground(colorPalette.background);
        //adding elements to bottomPanel panel
        bottomPanel.add(fileUIController, BorderLayout.CENTER);

        //adding panels into the main panel
        middlePanel.add(topPanel, BorderLayout.NORTH);
        middlePanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Returning roiTable to be used in other classes
     * @return roiTable
     */
    public static roiTable getTable(){
        return roiTable;
    }

    /**
     * Calling instance of fileUIController to change the card
     */
    public static void changeToROITable(){
        fileUIController.changeCard("ROI Table");
    }

    /**
     * Calling instance of fileUIController to change the card
     */
    public static void changeToNoFiles(){
        fileUIController.changeCard("No Files");
    }

    /**
     * Ensures that after deleting or clearing that the tables are empty to change the cards for each panel
     */
    private void changePanel(){
        //ensuring that information was sucessfully uploaded 
        if (uploadPanel.getTable().empty()){
            uploadPanel.changeToNoFiles();
            roiPanel.changeToNoFiles();
        }
    }

    /*
        if(e.getSource() == highProfitSort){
            roi.sortTable(6, true);
        }
        else if(e.getSource() == lowProfitSort){
            roi.sortTable(6, false);
        }
        else if(e.getSource() == delete){
            roi.deleteRows();
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");
        }
     */

}
