import javax.swing.table.AbstractTableModel;

public class pathTableWriter extends AbstractTableModel{

    private final String[] columnNames = {"Number", "Path List", "CheckBox"};

    private Object[][]data = {};

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public int getRowCount(){
        return data.length; 
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col){
        return data[row][col];
    }

    @Override
    public void setValueAt(Object value, int row, int col){
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    /**
     * Adding a row to the table from an orders collected information
     * @param order Order whos information is to be added
     */
    public void addRow(pathObject path) {
        int rowCount = getRowCount();//obtaining current row count
        Object[][] newData = new Object[data.length + 1][];//adding new data
        System.arraycopy(data, 0, newData, 0, rowCount);

        //defining the new data to be added
        newData[rowCount] = new Object[]{path.getID(), path.getPath(), false};
        data = newData;//setting data to new data
        fireTableDataChanged();//updating table
    }

    /**
     * Deletes all selected rows from the check box column
     */
    public void deleteSelectedRows(){

        roiTableWriter r = roiTable.returnWriter();//creating an roi table writer variable 

        for(int i = getRowCount() - 1; i >= 0; i--){//itterates through all rows in table
            Object checked = getValueAt(i, finalTableValues.pathCheckCol);//obtains boolean value from check box column
            if(checked instanceof Boolean){//if checked for deletion
                boolean delete = (Boolean) checked;
                if(delete){
                    deleteRow(i);//delete the row
                    r.deleteRow(i);//deleting the row from the roi table 
                }
            }
        }

        r.deleteRow(r.getRowCount() - 1);//deleting current totals row from the roi table
        r.addTotals();//adding new totals row to the roi table 
    }

    /**
     * Will delete a row from the table 
     * @param rowInd Row to be deleted
     */
    public void deleteRow(int rowInd){
        int newRows = data.length - 1;//intialize a new row counter
        Object[][] newData = new Object[newRows][];//create new data
        int newRowInd = 0;//set start to 0

        for(int i = 0; i < data.length; i++){//iterate through current rows
            if(i != rowInd){//if not the one to be deleted
                newData[newRowInd++] = data[i];//add into new data
            }
        }

        data = newData;//set as data
        fireTableDataChanged();//update table
    }

    
}
