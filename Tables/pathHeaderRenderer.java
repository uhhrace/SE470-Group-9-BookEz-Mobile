import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;

/**
 * Class goal is to render headers of table with different background color then the default one 
 */
public class pathHeaderRenderer extends JLabel implements TableCellRenderer{

  public pathHeaderRenderer() {
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