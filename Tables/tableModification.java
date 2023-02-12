import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class tableModification{

  /**
   * Resizing the column spacing on the path table
   */
  public static void pathColumnResizing(JTable table){
    //Number column
    table.getColumnModel().getColumn(0).setPreferredWidth(10);
    
    //File column
    table.getColumnModel().getColumn(1).setPreferredWidth(280);
    
    //Checkbox column
    table.getColumnModel().getColumn(2).setPreferredWidth(20);
  }

  /**
   * Changing the tables background color 
   * @param table
   */
  public static void cellBackGroundColor(JTable table){
    for(int i = 0; i < table.getColumnCount() - 1; i++){
      table.getColumnModel().getColumn(i).setCellRenderer(new pathCellRenderer());
    }
  }

  /**
   * Changing the tables border. Border comes from JScrollPane
   * @return new border
   */
  public static Border getTableBorder(){
    Border border = new LineBorder(colorPalette.light, 1);

    return border;
  }
    
}
