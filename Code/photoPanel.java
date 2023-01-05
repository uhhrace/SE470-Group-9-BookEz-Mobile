import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class photoPanel extends JPanel implements ActionListener{
    
    private JButton back, upload, delete, clearList;
    private JLabel name,prompt, blank, listID;
    static JTextArea fileList, deleteField;

    public photoPanel(){

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
        back.setForeground(colorPalette.ezBlue);
 
        upload = new JButton("Select Files");
        upload.addActionListener(this);
        upload.setForeground(colorPalette.ezBlue);

        delete = new JButton("Delete");
        delete.addActionListener(this);
        delete.setForeground(colorPalette.ezBlue);
        delete.setEnabled(false);//ensures that files must be first uploaded

        clearList = new JButton("Clear All");
        clearList.addActionListener(this);
        clearList.setForeground(colorPalette.ezBlue);
        clearList.setEnabled(false);//ensures that files must be first uploaded
 
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
        listID.setForeground(colorPalette.ezBlue);

        //creating text area 
        fileList = new JTextArea("         No Files Have Been Uploaded");
        fileList.setPreferredSize(new Dimension(250, 200));
        fileList.setEditable(false);

        deleteField = new JTextArea();
        deleteField.setPreferredSize(new Dimension(250, 20));
        deleteField.setEditable(false);//ensures that files must first be uploaded

        //creating JScrollPane 
        JScrollPane pathScroll = new JScrollPane(fileList);
        pathScroll.setBorder(null);
 
        //adding elements to panels 
        title.add(name);
        title.setPreferredSize(new Dimension(850, 40));
        title.setBackground(colorPalette.ezBlue);
 
        space.add(blank);
        space.setPreferredSize(new Dimension(850, 10));
        space.setBackground(colorPalette.ezBlue);
 
        promptPanel.add(prompt);
        promptPanel.setPreferredSize(new Dimension(850, 30));
        promptPanel.setBackground(colorPalette.ezBlue);
 
        space2.add(blank);
        space2.setPreferredSize(new Dimension(850, 10));
        space2.setBackground(colorPalette.ezBlue);
 
        listIDPanel.add(listID);
        file.add(upload);
        file.add(pathScroll); 

        deletePanel.add(delete);
        deletePanel.add(deleteField);

        button.add(clearList);
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

    //if button is clicked, move to a different panel/preform actions
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == upload){//uploading order reciepts
            uploadFiles();
        }
        else if(e.getSource() == delete){
            deleteFile();
        } 
        else if(e.getSource() == clearList){
            clear();
        }
        else if(e.getSource() == back){//returning to homescreen
            controller.getInstance().changeCard("Homescreen");
            deleteField.setText("");

            if(ROIManager.paths.isEmpty()){
                fileList.setText("         No Files Have Been Uploaded");
            }
        }

    }//end of actionPreformed 

    //creates an instance of ROIManager to read in selected files from the users device
    private void uploadFiles(){

        //read in all selected files 
        ROIManager.readInFiles();

        //displaying pathList to screen
        listManager.updatePathList();

        //user can now choose to delete a file that was uploaded
        delete.setEnabled(true);
        deleteField.setEditable(true);
        clearList.setEnabled(true);

    }//end of uploadFiles

    //deletes a file based on user selection 
    private void deleteFile(){
        if(deleteField.getText().isEmpty()){//trying to delete something when nothing was entered 
            JOptionPane.showMessageDialog(null, "Please enter a file to delete. Ex 1 from 1: File");
        }
        else {//something was entered in text field
            String fieldResponse = deleteField.getText();//obtaining entered text 
            if(fieldResponse.matches("\\d+")){ //entered field was an integer 

                listManager.searchAndRemove(fieldResponse);//remove element from hash map
            } else { //entered field was not an integer
                JOptionPane.showMessageDialog(null, "Please enter a valid number Ex: 1");
            }
        }
    }//end of deleteFile

    //resets fields for new information
    private void clear(){
        
        //resetting list display to be empty
        fileList.setText("No Files Have Been Uploaded");
        
        //resetting delete acessibility to false
        delete.setEnabled(false);
        deleteField.setText("");
        deleteField.setEditable(false);

        //resetting roi managers ID to 1
        ROIManager.identifyer = 1;

        //deleting text files and resetting vectors
        ROIManager.output.delete();
        roiPanel.roiTable.setText("Empty");
        ROIManager.orders.clear();//clearing the hashmap 
    
        //checking if files exists as it is created when an element is deleted
        File pathFile = new File("pathList.txt");
        if(pathFile.exists()){
            listManager.outputList.delete();
            photoPanel.fileList.setText("         No Files Have Been Uploaded");
        }
        ROIManager.paths.clear();//clearing the vector since it is created when files have been uploaded

    }//end of clear

}//end of class
