import javax.swing.table.AbstractTableModel;

public class tableWriter extends AbstractTableModel{
    
    private final String[] columnNames = {"Order Total", "Item Sold Price", 
    "Charged Shipping", "Taxes", "Profit", "Order Number"};

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

    public void addRow(orderObject order) {

        int rowCount = getRowCount();
        Object[][] newData = new Object[data.length + 1][];
        System.arraycopy(data, 0, newData, 0, rowCount);

        newData[rowCount] = new Object[]{order.getTotal(), order.getSoldPrice(), order.getShipPaid(),
                                        order.getShipCost(), order.getTax(), order.getProfit(), 
                                        order.getOrderNum()};
        data = newData;
        fireTableDataChanged();
    }

}
