import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

public class roiTableWriter extends AbstractTableModel{
    
    private final String[] columnNames = {"Order Number", "Order Total", "Item Sold Price", 
    "Charged Shipping", "Shipping Paid", "Taxes", "Profit", "Check Box"};

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
    public void addRow(orderObject order) {
        int rowCount = getRowCount();//obtaining current row count
        Object[][] newData = new Object[data.length + 1][];//adding new data
        System.arraycopy(data, 0, newData, 0, rowCount);

        //defining the new data to be added
        newData[rowCount] = new Object[]{order.getOrderNum(), order.getTotal(), order.getSoldPrice(),
                                        order.getShipPaid(), order.getShipCost(), order.getTax(), 
                                        order.getProfit(), false};
        data = newData;//setting data to new data
        fireTableDataChanged();//updating table
    }

    /**
     * Sums up all totals and places them into their designated column 
     */
    public void addTotals(){
        int rowCount = getRowCount();//obtaining current rows in table
        Object[][] newData = new Object[data.length + 1][];//adding a new row 
        System.arraycopy(data, 0, newData, 0, rowCount);//copying over

        //adding the new data into an array
        newData[rowCount] = new Object[]{"N/A", getSum(1), getSum(2),
                                        getSum(3), getSum(4), getSum(5), 
                                        getSum(6), "N/A"};
        data = newData;//setting data to new data
        fireTableDataChanged();//update table

    }

    /**
     * Obtaining the sum of a column 
     * @param col Column to be summed 
     * @return Double of the summed information
     */
    private double getSum(int col){
        double sum = 0;//initialize the double to 0

        //iterate through all the rows in a column
        for(int row = 0; row < data.length; row++){
            Object value = data[row][col];//find the column and current row 
            if(value instanceof Double){//if it is a double then sum it up 
                sum += (Double) value;//add to sum 
            }
        }

        return Math.round(sum * 1000) / 1000.0;//return the rounded sum
    }

    /**
     * Deletes all selected rows from the check box column
     */
    public void deleteSelectedRows(){

        pathTableWriter p = pathTable.returnWriter();//creating a path table writer variable 

        for(int i = getRowCount() - 1; i >= 0; i--){//itterates through all rows in table
            Object checked = getValueAt(i, finalTableValues.roiCheckCol);//obtains boolean value from check box column
            if(checked instanceof Boolean){//if checked for deletion
                boolean delete = (Boolean) checked;
                if(delete){
                    deleteRow(i);//delete the row
                    p.deleteRow(i);//deleting the row from the path table 
                }
            }
        }

        deleteRow(getRowCount() - 1);//deleting current totals row
        addTotals();//adding new totals row

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

    /**
     * Will sort the table based on the entered in colum and will be sorted in ascending or descending order based on the boolean
     * @param columnIndex column to be sorted
     * @param ascending method of sort 
     */
    public void sortTable(int columnIndex, boolean ascending) {

        int rowCount = data.length;//obtaining current amount of rows 
        Integer[] sortIndices = new Integer[rowCount - 1];//creating an array to store values of said column
        int sortInd = 0;//finding out the length of all rows minus the final one 

        for (int i = 0; i < rowCount; i++) {//looping through the rows 
            if(i != rowCount - 1){//if its not the last one 
                sortIndices[sortInd++] = i;//add it to te array 
            }
        }

        //sorting the array of row values 
        Arrays.sort(sortIndices, (a, b) -> {
            Object valueA = data[a][columnIndex];
            Object valueB = data[b][columnIndex];
            //checking if the values are doubles and compare them if they are
            if (valueA instanceof Double && valueB instanceof Double) {
                if(ascending){//sorting in ascending order
                    return Double.compare((Double) valueA, (Double) valueB);
                } else {//sorting in descending order
                    return ((Double) valueB).compareTo((Double) valueA);
                }
            } else {//returning 0 if the values are not doubles
                return 0;
            }
        });

        //making object with amount of rows to insert sorted array
        Object[][] sortedData = new Object[rowCount][];
        int sortedInd = 0;//initalizing a sorted index 

        //itterating through all rows
        for (int i = 0; i < rowCount; i++) {
            if(i != rowCount - 1){//if not last row then add to data
                sortedData[sortedInd++] = data[sortIndices[i]];
            } else {//if last row then add in regularly 
                sortedData[sortedInd++] = data[i];
            }
            
        }

        data = sortedData;//set data to sorted data 
        fireTableDataChanged();//update table 
    }

    /**
     * Clearing all the table data 
     */
    public void clearData(){
        data = new Object[0][0];//clears all table data 
        fireTableDataChanged();//update table 
    }

}
