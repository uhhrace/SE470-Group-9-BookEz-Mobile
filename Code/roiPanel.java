import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class roiPanel extends JPanel implements ActionListener{
    
    private JButton back, highProfitSort, lowProfitSort;
    public static JButton delete;
    private JLabel sortMess;
    private roiTable roi = new roiTable();
    
    public roiPanel(){
        
        setLayout(new BorderLayout());

        //creating JTables
        JTable table = roi.getTable();//obtaining the abstract table
        JScrollPane tableScroll = new JScrollPane(table);//adding jscrollpane

        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(115, 500));
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //creating buttons 
        back = new JButton("<-");//creating back button 
        back.addActionListener(this);//monitor if clicked
        back.setForeground(colorPalette.light);

        delete = new JButton("Delete Selected Items"); 
        delete.addActionListener(this);
        delete.setForeground(colorPalette.light);
        delete.setEnabled(false);

        highProfitSort = new JButton("Highest Profit");
        highProfitSort.addActionListener(this);
        highProfitSort.setForeground(colorPalette.light);

        lowProfitSort = new JButton("Lowest Profit");
        lowProfitSort.addActionListener(this);
        lowProfitSort.setForeground(colorPalette.light);

        //creating JLables
        sortMess = new JLabel("Sort By: ");
        sortMess.setForeground(colorPalette.light);

        //adding element into panels
        topPanel.add(delete);
        leftPanel.add(sortMess);
        leftPanel.add(highProfitSort);
        leftPanel.add(lowProfitSort);
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

    /**
     * Preforming an action for every event
     */
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
    }

}

