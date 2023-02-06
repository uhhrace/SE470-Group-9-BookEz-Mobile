import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class roiPanel extends JPanel implements ActionListener{
    
    private JButton back, display, highProfitSort, lowProfitSort;
    private JLabel sortMess;
    static JTextArea roiTable;

    static tableWriter t = new tableWriter();
    
    public roiPanel(){
        
        setLayout(new BorderLayout());

        //creating panels
        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(115, 500));
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //creating buttons 
        back = new JButton("<-");//creating back button 
        back.addActionListener(this);//monitor if clicked
        back.setForeground(colorPalette.ezBlue);

        display = new JButton("Display ROI Table"); 
        display.addActionListener(this);
        display.setForeground(colorPalette.ezBlue);

        highProfitSort = new JButton("Highest Profit");
        highProfitSort.addActionListener(this);
        highProfitSort.setForeground(colorPalette.ezBlue);

        lowProfitSort = new JButton("Lowest Profit");
        lowProfitSort.addActionListener(this);
        lowProfitSort.setForeground(colorPalette.ezBlue);

        //creating JTextAreas
        roiTable = new JTextArea("\t      Click on Display ROI Table to view table or upload files to generate one");
        roiTable.setPreferredSize(new Dimension(700, 720));
        roiTable.setEditable(false);//set so the user cannot type into area

        //creating JScrollPane
        JScrollPane roiScroll = new JScrollPane(roiTable);
        roiScroll.setBorder(null);


        JTable table = new JTable(t);
        table.setPreferredScrollableViewportSize(new Dimension(700, 500));
        JScrollPane tableScroll = new JScrollPane(table);

        //creating JLables
        sortMess = new JLabel("Sort By: ");
        sortMess.setForeground(colorPalette.ezBlue);

        //adding element into panels
        topPanel.add(display);
        leftPanel.add(sortMess);
        leftPanel.add(highProfitSort);
        leftPanel.add(lowProfitSort);
        //middlePanel.add(roiScroll); 
        middlePanel.add(tableScroll); 
        bottomPanel.add(back); 

        //formatting panels 
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //displaying panel
        setVisible(true);
        setSize(500, 500);
    }

    //if button is clicked preform an action
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == display || e.getSource() == highProfitSort || e.getSource() == lowProfitSort){

            /* 
            File f = new File("output.txt");
            if(f.exists()){//verifying that output text file has been created 

                if(e.getSource() == highProfitSort || e.getSource() == lowProfitSort){
                        
                    if(e.getSource() == highProfitSort){
                        sortManager.profitSort(true);
                    } else if(e.getSource() == lowProfitSort){
                       sortManager.profitSort(false);
                    }
                     
                } 
                //default to display roi table
                try{  
                        FileReader reader = new FileReader(ROIManager.output.getPath());
                        BufferedReader br = new BufferedReader(reader);
                        roiTable.read( br, null );
                        br.close();
                        roiTable.requestFocus();
                    } catch(Exception e2) { 
                        System.out.println(e2); 
                    }
            } else {//output.text has not been created yet 
                JOptionPane.showMessageDialog(null, "Please upload files first");
            }
            */
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");
        }
    }//end of action preformed 

}//end of roi panel

