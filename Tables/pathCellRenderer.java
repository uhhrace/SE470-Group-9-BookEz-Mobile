import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
* Class main goal is to render cell differently to set background as cells own when selecting a checkbox
*/
public class pathCellRenderer extends DefaultTableCellRenderer{
    public pathCellRenderer() {
        setOpaque(true);
        setPreferredSize(new Dimension(getWidth(), 30));
      }
    
      public Component getTableCellRendererComponent(JTable table, Object value,
          boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
      }
    }
