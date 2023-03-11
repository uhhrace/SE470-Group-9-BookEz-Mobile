package contentPanels;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import UI_Formatter.homeSlideShow;
import UI_Formatter.sideMenu;
import UI_Formatter.colorPalette;

public class homePanel extends JPanel{

    private static homeSlideShow homeSlideShow;
    private static ArrayList<JLabel> images = new ArrayList<>();
    private static JPanel slideShowPanel;
   
    public homePanel(){

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
        
        //message panel
        JPanel message = new JPanel();
        message.setPreferredSize(new Dimension(350, 50));
        message.setBackground(colorPalette.background);
        //adding elements to message panel
        JLabel messageText = new JLabel("Bookkeeping made easy");
        messageText.setFont(new Font("Arial", Font.PLAIN, 30));//resizing text within label
        messageText.setForeground(colorPalette.light);
        //adding elements to appName panel
        message.add(messageText);

        //message panel
        JPanel logout = new JPanel();
        logout.setPreferredSize(new Dimension(180, 50));
        logout.setBackground(colorPalette.background);
        //adding elements to message panel
        JLabel logoutLabel = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-logout-rounded-32.png"));
        JLabel logoutText = new JLabel("Logout");
        logoutText.setFont(new Font("Arial", Font.PLAIN, 30));//resizing text within label
        logoutText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        logoutLabel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                homeSlideShow.stopThread();//stopping animation 
                System.exit(0);//ends program
            }
        });
        logoutText.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                homeSlideShow.stopThread();//stopping animation 
                System.exit(0);//ends program
            }
        });
        //adding elements to appName panel
        logout.add(logoutLabel);
        logout.add(logoutText);
      
        //adding panels into the main panel
        middleTop1.add(topSpace, BorderLayout.NORTH);
        middleTop1.add(message, BorderLayout.WEST);
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
        
        //welcome panel
        JPanel welcome = new JPanel();
        welcome.setBackground(colorPalette.med);
        welcome.setPreferredSize(new Dimension(200, 50));
        //adding elements to panel
        JLabel welcomeText = new JLabel("Welcome!");
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 40));//resizing text within label
        welcomeText.setForeground(colorPalette.light);
        //adding elements to welcome panel
        welcome.add(welcomeText);

        //adding panels into the main panel 
        middleTop2.add(topSpace, BorderLayout.NORTH);
        middleTop2.add(welcome, BorderLayout.WEST);

    }

    /**
     * Designing the main content of the panel
     * @param middlePanel where content should be added
     */
    private void bottomMiddle(JPanel middlePanel){

        middlePanel.setLayout(new GridLayout(2, 2, 10, 10));

        JPanel topLeft = new JPanel();
        topLeft.setBackground(Color.RED);
        topLeft.setPreferredSize(new Dimension(350, 350));

        //slideShowPanel panel (topRight)
        slideShowPanel = new JPanel();
        slideShowPanel.setBackground(colorPalette.background);
        slideShowPanel.setPreferredSize(new Dimension(350, 350));
        slideShowPanel.setLayout(new BoxLayout(slideShowPanel, BoxLayout.Y_AXIS));
        //adding images into labels and into images arraylist  
        JLabel image1 = new JLabel(new ImageIcon("src/UI_Formatter/Images/Data extraction-pana.png"));
        JLabel image2 = new JLabel(new ImageIcon("src/UI_Formatter/Images/Data extraction-amico.png"));
        JLabel image3 = new JLabel(new ImageIcon("src/UI_Formatter/Images/Data extraction-bro.png"));
        images.add(image1);
        images.add(image2);
        images.add(image3);
        //creating a homeSlideShow instance
        homeSlideShow = new homeSlideShow(images, slideShowPanel);

        //bottomLeft panel
        JPanel bottomLeft = new JPanel();
        bottomLeft.setBackground(colorPalette.background);
        bottomLeft.setPreferredSize(new Dimension(350, 350));
        //adding elements to the panel
        JLabel exportText = new JLabel("Exported Files");

        //adding elements to the bottomLeft panel
        bottomLeft.add(exportText);
        JPanel bottomRight = new JPanel();
        bottomRight.setBackground(Color.BLUE);
        bottomRight.setPreferredSize(new Dimension(350, 350));

        //adding panels into the main panel
        middlePanel.add(topLeft);
        middlePanel.add(slideShowPanel);
        middlePanel.add(bottomLeft);
        middlePanel.add(bottomRight);

    }

    /**
     * Begining thread. Used to begin the slide show when the program is initially opened from controller
     */
    public void startSlideshow() {
        homeSlideShow.start();
    }

    /**
     * Used in mouseListener to determine when the thread shall stop and continue 
     * @param panelChange panel that will be changed to 
     */
    public static void slideShowHandler(String panelChange){
        if(panelChange == "Homescreen"){//if homescreen then begin slide show 
            homeSlideShow = new homeSlideShow(images, slideShowPanel);
            homeSlideShow.start();
        } else {//else stop slide show 
            if(homeSlideShow != null){
                homeSlideShow.stopThread();
                homeSlideShow = null;
            }
        }
    }
 
}

