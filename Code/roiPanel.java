import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class roiPanel extends JPanel implements ActionListener{
    
    private JButton back, delete, highProfitSort, lowProfitSort;
    private JLabel sortMess;
    public static JTextArea roiTable;
    private roiTable roi = new roiTable();
    
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

        delete = new JButton("Delete Selected Items"); 
        delete.addActionListener(this);
        delete.setForeground(colorPalette.ezBlue);

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

        JTable table = roi.getTable();
        JScrollPane tableScroll = new JScrollPane(table);

        //creating JLables
        sortMess = new JLabel("Sort By: ");
        sortMess.setForeground(colorPalette.ezBlue);

        //adding element into panels
        topPanel.add(delete);
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
    }//end of action preformed 

}//end of roi panel

