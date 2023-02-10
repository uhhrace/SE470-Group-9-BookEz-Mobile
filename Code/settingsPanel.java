import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class settingsPanel extends JPanel implements ActionListener{
    
    private JButton back;
    private JPanel dropBox;
    private dropBox box = new dropBox();

    public settingsPanel(){

        //Creating Panels
        JPanel main = new JPanel();
        dropBox = box.dropBoxArea();
        JPanel middle = new JPanel();
        JPanel button = new JPanel();

        //Creating buttoms
        back = new JButton("<-");//creating uploading photos button 
        back.addActionListener(this);//monitor if clicked 
        back.setPreferredSize(new Dimension(150, 35));
        back.setForeground(colorPalette.ezBlue);

        //creating JTables
        JScrollPane tableScroll = new JScrollPane(dropBox);//adding jscrollpane

        middle.add(tableScroll);
        button.add(back);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

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
        if(e.getSource() == back){//if user wants to upload photos, move to photo panel
            controller.getInstance().changeCard("Homescreen");
            //controller.getInstance().changeCard("Test");
        }

    }
}
