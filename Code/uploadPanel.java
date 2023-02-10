import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class uploadPanel extends JPanel implements ActionListener{
    
    private JButton back, upload, delete, clear;
    private JLabel name,prompt, blank, listID;
    private pathTable pathTable = new pathTable();

    public uploadPanel(){

        //creating JPanels  
        JPanel main = new JPanel();
        JPanel space = new JPanel();
        JPanel space2 = new JPanel();
        JPanel title = new JPanel();
        JPanel promptPanel = new JPanel();
        JPanel listIDPanel = new JPanel();
        JPanel file = new JPanel();
        JPanel deletePanel = new JPanel();
        JPanel button = new JPanel();

        //creating JButtons 
        back = new JButton("<-");//create back button 
        back.addActionListener(this);//monitor if clicked 
        back.setForeground(colorPalette.light);
 
        upload = new JButton("Upload Files");
        upload.addActionListener(this);
        upload.setForeground(colorPalette.light);

        delete = new JButton("Delete");
        delete.addActionListener(this);
        delete.setForeground(colorPalette.light);
        delete.setEnabled(false);//ensures that files must be first uploaded

        clear = new JButton("Clear All");
        clear.addActionListener(this);
        clear.setForeground(colorPalette.light);
        clear.setEnabled(false);//ensures that files must be first uploaded
 
        //creating JLabels
        name = new JLabel("Upload Files");
        name.setFont(new Font("Arial", Font.BOLD, 35));//resizing text within label
        name.setForeground(Color.white);
 
        blank = new JLabel("");

        prompt = new JLabel("Ensure files are in a PDF format");
        prompt.setFont(new Font("Arial", Font.PLAIN, 15));
        prompt.setForeground(Color.white);

        listID = new JLabel("List of files uploaded");
        listID.setFont(new Font("Arial", Font.PLAIN, 15));
        listID.setForeground(colorPalette.light);
 
     
        JTable table = pathTable.getTable();
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(null);
 
        //adding elements to panels 
        title.add(name);
        title.setPreferredSize(new Dimension(850, 40));
        title.setBackground(colorPalette.light);
 
        space.add(blank);
        space.setPreferredSize(new Dimension(850, 10));
        space.setBackground(colorPalette.light);
 
        promptPanel.add(prompt);
        promptPanel.setPreferredSize(new Dimension(850, 30));
        promptPanel.setBackground(colorPalette.light);
 
        space2.add(blank);
        space2.setPreferredSize(new Dimension(850, 10));
        space2.setBackground(colorPalette.light);
 
        listIDPanel.add(listID);
        file.add(upload);
        file.add(tableScroll);
        
        deletePanel.add(delete);

        button.add(clear);
        button.add(back);
 
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(title);
        main.add(space);
        main.add(promptPanel);
        main.add(space2);
        main.add(listIDPanel);
        main.add(file);
        main.add(deletePanel);
        main.add(button);
 
        //adding for display
        add(main);

        //displaying panel
        setVisible(true);
        setSize(500, 500);

    }//end of photoPanel

    /**
     * Preforming an action for every event
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == upload){//uploading order reciepts
            //read in all selected files 
            ROIManager.readInFiles();

            //ensuring that information was sucessfully uploaded 
            if(pathTable.returnRowCount() > 0){

                //user can now choose to delete a file that was uploaded
                delete.setEnabled(true);
                roiPanel.delete.setEnabled(true);

                //user can clear all of the uploaded information 
                clear.setEnabled(true);
            }
           
        }
        else if(e.getSource() == delete){
            pathTable.deleteRows();
        } 
        else if(e.getSource() == clear){
            //clear();
            System.out.println("Complete clear");
        }
        else if(e.getSource() == back){//returning to homescreen
            controller.getInstance().changeCard("Homescreen");

        }

    }

}
