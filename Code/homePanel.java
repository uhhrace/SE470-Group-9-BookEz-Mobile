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
        topSpace.setPreferredSize(new Dimension(1000, 25));
        topSpace.setBackground(colorPalette.background);
        
        //message panel
        message = new JPanel();
        message.setPreferredSize(new Dimension(350, 50));
        message.setBackground(colorPalette.background);
        //adding elements to message panel
        messageText = new JLabel("Bookkeeping made easy");
        messageText.setFont(new Font("Arial", Font.PLAIN, 30));//resizing text within label
        messageText.setForeground(colorPalette.light);
        //adding elements to appName panel
        message.add(messageText);

        //message panel
        logout = new JPanel();
        logout.setPreferredSize(new Dimension(180, 50));
        logout.setBackground(colorPalette.background);
        //adding elements to message panel
        logoutLabel = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-logout-rounded-32.png"));
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

        middleTop2.setLayout(new BorderLayout());

        JPanel welcome, topSpace;
        JLabel welcomeText;
        
        //topSpace panel
        topSpace = new JPanel();
        topSpace.setBackground(colorPalette.med);
        topSpace.setPreferredSize(new Dimension(850, 30));
        
        //welcome panel
        welcome = new JPanel();
        welcome.setBackground(colorPalette.med);
        welcome.setPreferredSize(new Dimension(200, 50));
        //adding elements to panel
        welcomeText = new JLabel("Welcome!");
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 40));//resizing text within label
        welcomeText.setForeground(colorPalette.light);
        //adding elements to welcome panel
        welcome.add(welcomeText);

        //adding panels into the main panel 
        middleTop2.add(topSpace, BorderLayout.NORTH);
        middleTop2.add(welcome, BorderLayout.WEST);

    }

    private void bottomMiddle(JPanel middlePanel){

        middlePanel.setLayout(new GridLayout(2, 2, 10, 10));

        //Creating inner panels 
        JPanel topLeft, topRight, bottomLeft, bottomRight;
        JLabel topRightImage, exportText;

        topLeft = new JPanel();
        topLeft.setBackground(Color.RED);
        topLeft.setPreferredSize(new Dimension(350, 350));

        //topRight panel 
        topRight = new JPanel();
        topRight.setBackground(colorPalette.background);
        topRight.setPreferredSize(new Dimension(350, 350));
        //adding elements to panel 
        topRightImage = new JLabel(new ImageIcon("UI Formatting/Images/Data extraction-pana.png"));
        //adding elements to topRight panel 
        topRight.add(topRightImage);

        //bottomLeft panel
        bottomLeft = new JPanel();
        bottomLeft.setBackground(colorPalette.background);
        bottomLeft.setPreferredSize(new Dimension(350, 350));
        //adding elements to the panel
        exportText = new JLabel("Exported Files");

        //adding elements to the bottomLeft panel
        bottomLeft.add(exportText);
        
        bottomRight = new JPanel();
        bottomRight.setBackground(Color.BLUE);
        bottomRight.setPreferredSize(new Dimension(350, 350));

        //adding panels into the main panel
        middlePanel.add(topLeft);
        middlePanel.add(topRight);
        middlePanel.add(bottomLeft);
        middlePanel.add(bottomRight);

    }
 
}
