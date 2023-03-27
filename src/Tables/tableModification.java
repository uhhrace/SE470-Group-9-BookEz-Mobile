package Tables;

import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import UI_Formatter.colorPalette;

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
   * Resizing the column spacing on the roi table
   */
  public static void roiColumnResizing(JTable table){
    //Order number column
    table.getColumnModel().getColumn(0).setPreferredWidth(70);
    
    //Order total column
    table.getColumnModel().getColumn(1).setPreferredWidth(40);
    
    //Item sold price column
    table.getColumnModel().getColumn(2).setPreferredWidth(50);

    //Charged shipping column
    table.getColumnModel().getColumn(3).setPreferredWidth(70);
    
    //Shipping paid column
    table.getColumnModel().getColumn(4).setPreferredWidth(60);
    
    //Taxes column
    table.getColumnModel().getColumn(5).setPreferredWidth(40);

    //Profit column
    table.getColumnModel().getColumn(6).setPreferredWidth(40);
    
    //Checkbox column
    table.getColumnModel().getColumn(7).setPreferredWidth(20);
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

