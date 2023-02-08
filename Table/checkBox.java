
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class checkBox extends JCheckBox implements TableCellRenderer {
    private JCheckBox box;

    /**
     * Constructor for checkBox, sets color and centers the check box
     */
    public checkBox(){
        box = new JCheckBox();
        box.setBackground(colorPalette.background);
        box.setHorizontalAlignment(JCheckBox.CENTER);

    }

    /**
     * Will place the check box render into the column and set boolean value
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
            
            if(row == table.getRowCount() - 1){
                return new JLabel();
            }

            if(value instanceof Boolean){
                box.setSelected((Boolean) value);
            }

            return box;

    }
}
