import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class roiTable {
    private static roiTableWriter t = new roiTableWriter();//creating an instance of table writer

    /**
     * Creating the table and storing it within a JTable to be returned and used within the panel display
     * @return
     */
    public JTable getTable(){
        JTable table = new JTable(t);//creating table with tablewriter 

        table.setPreferredScrollableViewportSize(new Dimension(940, 450));//setting tables size 
        table.getColumnModel().getColumn(finalTableValues.roiCheckCol).setCellRenderer(new roiCheckBox());//rendering checkboxes 

        //adding mouse listener for each check box 
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //obtaining each point on the table that was clicked
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
            
                if (col == finalTableValues.roiCheckCol) {//if column is the check box column 
                    Object value = table.getValueAt(row, col);//obtain the current value 

                    if(value instanceof Boolean){//if it is a boolean
                        Boolean checked = (Boolean) value;//obtain boolean value
                        t.setValueAt(!checked, row, col);//setting the value to the opposite of what it currently is
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
    public static roiTableWriter returnWriter(){
        return t;

    }

    /**
     * Deletes selected rows from table
     */
    public void deleteRows(){
        t.deleteSelectedRows();//making tableWriter function call
    }
    
    /**
     * Will sort the table based on the specified column and if it is ascending or descending 
     * @param col column whos info is to be sorted
     * @param ascending method of sorting 
     */
    public void sortTable(int col, boolean ascending){
        if(col > 0 && col < finalTableValues.roiCheckCol){//ensuring that the column is valid to be sorted
            t.sortTable(col, ascending);//calling function from table writer 
        }
    }
}
