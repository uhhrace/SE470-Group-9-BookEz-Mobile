import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class pathTable {
    private static pathTableWriter p = new pathTableWriter();//creating an instance of table writer

    /**
     * Creating the table and storing it within a JTable to be returned and used within the panel display
     * @return
     */
    public JTable getTable(){
        JTable table = new JTable(p);//creating table with tablewriter 

        table.setPreferredScrollableViewportSize(new Dimension(500, 310));//setting tables size 
        table.getColumnModel().getColumn(finalTableValues.pathCheckCol).setCellRenderer(new pathCheckBox());//rendering checkboxes 

        //adding mouse listener for each check box 
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //obtaining each point on the table that was clicked
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
            
                if (col == finalTableValues.pathCheckCol) {//if column is the check box column 
                    Object value = table.getValueAt(row, col);//obtain the current value 

                    if(value instanceof Boolean){//if it is a boolean
                        Boolean checked = (Boolean) value;//obtain boolean value
                        p.setValueAt(!checked, row, col);//setting the value to the opposite of what it currently is
                        table.repaint();//updating check box render 
                    }
                }
            }
        });
        return table;//returning the jtable

    }

    /**
     * Returning the table writer instance used for the abstract table
     * @return returning table writer 
     */
    public static pathTableWriter returnWriter(){
        return p;
    }

    /**
     * Deletes selected rows from table
     */
    public void deleteRows(){
        p.deleteSelectedRows();//making tableWriter function call
    }
 
    /**
     * Will return the amount of rows that are in the path table 
     * @return amount of rows 
     */
    public int returnRowCount(){
        return p.getRowCount();
    }

    /**
     * Used to ensure that the path table still contains files that were uploaded 
     * @return true/false
     */
    public boolean empty(){
        return p.getEmpty();
    }

    /**
     * Clearing data from both path table and roi table 
     */
    public void clear(){
        p.clearData();
    }

}
