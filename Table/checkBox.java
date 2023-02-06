import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class checkBox extends JCheckBox implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
            setSelected(((Boolean) value).booleanValue());
            return this;
    }
}
