import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;

public class roiHeaderRenderer extends JLabel implements TableCellRenderer{
    public roiHeaderRenderer() {
        setOpaque(true);
        setPreferredSize(new Dimension(getWidth(), 30));
        setBackground(colorPalette.background);
      }
    
      public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
      }
}
