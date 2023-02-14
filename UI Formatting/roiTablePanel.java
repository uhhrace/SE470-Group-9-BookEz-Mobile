import javax.swing.*;

public class roiTablePanel extends JPanel{

    private roiTable roi = new roiTable();

    public roiTablePanel(){

        setBackground(colorPalette.background);

        //adding elements to panel
        JTable table = roi.getTable();//obtaining the abstract table
        table.getTableHeader().setDefaultRenderer(new roiHeaderRenderer());
        tableModification.roiColumnResizing(table);
        tableModification.cellBackGroundColor(table);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(tableModification.getTableBorder());

        add(tableScroll);
    }
}
