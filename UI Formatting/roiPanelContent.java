import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class roiPanelContent extends JPanel{

    private roiTable roi = new roiTable();

    public roiPanelContent(){

        setLayout(new BorderLayout());

        //topPanel panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(Color.RED);
        topPanel.setPreferredSize(new Dimension(1000, 250));

        //bottomPanel panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(25, 20, 10, 20));
        //adding elements to panel
        JTable table = roi.getTable();//obtaining the abstract table
        table.getTableHeader().setDefaultRenderer(new roiHeaderRenderer());
        tableModification.roiColumnResizing(table);
        tableModification.cellBackGroundColor(table);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(tableModification.getTableBorder());

        bottomPanel.add(tableScroll);

        //adding panels into the main panel
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
