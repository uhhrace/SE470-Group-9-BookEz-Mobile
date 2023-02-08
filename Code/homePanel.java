import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class homePanel extends JPanel implements ActionListener{
    
    private JButton photo, roi, exit;
    private JLabel appName, blank, option, tab;

    public homePanel(){

        //Creating Panels
        JPanel main = new JPanel();
        JPanel prompt = new JPanel();
        JPanel space = new JPanel();
        JPanel space2 = new JPanel();
        JPanel title = new JPanel();
        JPanel middle = new JPanel();
        JPanel button = new JPanel();

        //Creating buttoms
        photo = new JButton("Upload Images");//creating uploading photos button 
        photo.addActionListener(this);//monitor if clicked 
        photo.setPreferredSize(new Dimension(150, 35));
        photo.setForeground(colorPalette.ezBlue);

        roi = new JButton("View ROI Table");
        roi.addActionListener(this);
        roi.setPreferredSize(new Dimension(150, 35));
        roi.setForeground(colorPalette.ezBlue);

        exit = new JButton("Exit Application"); 
        exit.addActionListener(this);
        exit.setForeground(colorPalette.ezBlue);

        //Creating Lables
        appName = new JLabel("Welcome To BookEz");
        appName.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label
        appName.setForeground(colorPalette.background);

        option = new JLabel("Please choose an option");
        option.setFont(new Font("Arial", Font.PLAIN, 15));//resizing text within label
        option.setForeground(colorPalette.background);

        tab = new JLabel("    ");
        blank = new JLabel("");

        //Adding elements to panels 
        space.add(blank);
        space.setPreferredSize(new Dimension(850, 15));
        space.setBackground(colorPalette.ezBlue);

        title.add(appName);
        title.setPreferredSize(new Dimension(850, 65));
        title.setBackground(colorPalette.ezBlue);

        prompt.add(option);
        prompt.setPreferredSize(new Dimension(850, 30));
        prompt.setBackground(colorPalette.ezBlue);

        middle.add(photo);
        middle.add(tab);
        middle.add(roi); 

        button.add(exit);

        space2.add(blank);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(space);
        main.add(title);
        main.add(prompt);
        main.add(space2);
        main.add(middle);
        main.add(button);

        //adding for display 
        add(main);

        //displaying panel
        setVisible(true);
        setSize(500, 500);

    }
 
    /**
     * Preforming an action for every event
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == photo){//if user wants to upload photos, move to photo panel
            controller.getInstance().changeCard("Upload Photos");
            //controller.getInstance().changeCard("Test");
        }
        else if(e.getSource() == roi){
            controller.getInstance().changeCard("View ROI Table");
        }
        else if(e.getSource() == exit){

            //deleting output.text files only if they have something in them ie were created 
            File path = new File("pathList.txt");
    
            if(path.length() > 0){
                //listManager.outputList.delete();
                //photoPanel.fileList.setText("Empty");

                //ROIManager.paths.clear();//clearing the vector 
            }
            System.exit(0);//ends program 

        }

    }
}
