import javax.swing.table.AbstractTableModel;

public class tableWriter extends AbstractTableModel{
    
    private final String[] columnNames = {"Order Number", "Order Total", "Item Sold Price", 
    "Charged Shipping", "Taxes", "Profit", "Check Box"};

    private Object[][]data = {};

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public int getRowCount(){
        return data.length;//adding one to create a space between totals row 
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col){
        return data[row][col];
    }

    /**
     * Adding a row to the table from an orders collected information
     * @param order Order whos information is to be added
     */
    public void addRow(orderObject order) {

        int rowCount = getRowCount();
        Object[][] newData = new Object[data.length + 1][];
        System.arraycopy(data, 0, newData, 0, rowCount);

        newData[rowCount] = new Object[]{order.getOrderNum(), order.getTotal(), order.getSoldPrice(),
                                        order.getShipPaid(), order.getShipCost(), order.getTax(), 
                                        order.getProfit(), false};
        data = newData;
        fireTableDataChanged();
    }

    /**
     * Sums up all totals and places them into their designated column 
     */
    public void addTotals(){
        int rowCount = getRowCount();//obtaining current rows in table
        Object[][] newData = new Object[data.length + 1][];//adding a new row 
        System.arraycopy(data, 0, newData, 0, rowCount);//copying over

        //adding the new data into an array
        newData[rowCount] = new Object[]{"N/A", getSum(0), getSum(1),
                                        getSum(2), getSum(3), getSum(4), 
                                        getSum(5)};
        data = newData;//setting data to new data
        fireTableDataChanged();//notify of change 

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
     * Will delete a row from the table 
     * @param rowInd Row to be deleted
     */
    public void deleteRow(int rowInd){
        int newRows = data.length - 1;
        Object[][] newData = new Object[newRows][];
        int newRowInd = 0;

        for(int i = 0; i < data.length; i++){
            if(i != rowInd){
                newData[newRowInd++] = data[i];
            }
        }

        data = newData;
        fireTableDataChanged();
    }

    /**
     * Clearing all the table data 
     */
    public void clearData(){
        data = new Object[0][0];
        fireTableDataChanged();
    }

}
