import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class homePanel extends JPanel{
    
    private JPanel sidePanel, middleTop1, middleTop2, middlePanel;

    public homePanel(){

        //side panel containing all the icons for the menu
        sidePanel = new JPanel();
        sidePanel = sideMenu.getSideMenu();
        

        //top panel, color of the background
        middleTop1 = new JPanel();
        middleTop1.setPreferredSize(new Dimension(800, 100));
        middleTop1.setBackground(colorPalette.background);
        topMiddle(middleTop1);

        //next panel containing the medium color 
        middleTop2 = new JPanel();
        middleTop2.setPreferredSize(new Dimension(850, 100));
        middleTop2.setBackground(colorPalette.med);
        middleMiddle(middleTop2);

        //middle panel containing most of the content 
        middlePanel = new JPanel();
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

    private void topMiddle(JPanel middleTop1){

        middleTop1.setLayout(new BorderLayout());

        JPanel topSpace, message, logout;
        JLabel messageText, logoutText, logoutLabel;

        //topSpace panel 
        topSpace = new JPanel();
        topSpace.setPreferredSize(new Dimension(1000, 50));
        topSpace.setBackground(colorPalette.background);
        
        //message panel
        message = new JPanel();
        message.setPreferredSize(new Dimension(350, 150));
        message.setBackground(colorPalette.background);
        //adding elements to message panel
        messageText = new JLabel("Bookkeeping made easy");
        messageText.setFont(new Font("Arial", Font.PLAIN, 30));//resizing text within label
        messageText.setForeground(colorPalette.light);
        //adding elements to appName panel
        message.add(messageText);

        //message panel
        logout = new JPanel();
        logout.setPreferredSize(new Dimension(180, 150));
        logout.setBackground(colorPalette.background);
        //adding elements to message panel
        logoutLabel = new JLabel(new ImageIcon("Icons/icons8-logout-rounded-32.png"));
        logoutText = new JLabel("Logout");
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
        middleTop1.add(message, BorderLayout.WEST);
        middleTop1.add(logout, BorderLayout.EAST);
    }

    private void middleMiddle(JPanel middleTop2){

    }

    private void bottomMiddle(JPanel middlePanel){


    }
 
}
