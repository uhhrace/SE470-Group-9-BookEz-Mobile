import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class pathFilesPanel extends JPanel{

    public pathFilesPanel(){

        //adding elements to panel
        setBackground(colorPalette.background);

        JTable table = uploadPanel.getTable().getTable();
        table.getTableHeader().setDefaultRenderer(new pathHeaderRenderer());
        tableModification.pathColumnResizing(table);
        tableModification.cellBackGroundColor(table);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(tableModification.getTableBorder());

        JPanel roi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roi.setBackground(colorPalette.background);
        roi.setPreferredSize(new Dimension(250, 50));
        JLabel roiIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-table-32.png"));
        JLabel roiText= new JLabel("View ROI Table");
        roiText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        roiText.setForeground(colorPalette.light);
        roi.add(roiIcon);
        roi.add(roiText);
        //adding mouse listeners to the jlabels 
        roiIcon.addMouseListener(new MouseListener("ROI Table"));
        roiText.addMouseListener(new MouseListener("ROI Table"));

        JPanel export = new JPanel(new FlowLayout(FlowLayout.LEFT));
        export.setBackground(colorPalette.background);
        export.setPreferredSize(new Dimension(250, 50));
        JLabel exportIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-export-32.png"));
        JLabel exportText= new JLabel("Export ROI Table");
        exportText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        exportText.setForeground(colorPalette.light);
        export.add(exportIcon);
        export.add(exportText);
        //adding mouse listeners to the jlabels 
        exportIcon.addMouseListener(new MouseListener("Export Files"));
        exportText.addMouseListener(new MouseListener("Export Files"));

        JPanel delete = new JPanel(new FlowLayout(FlowLayout.LEFT));
        delete.setBackground(colorPalette.background);
        delete.setPreferredSize(new Dimension(250, 50));
        JLabel deleteIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-clear-symbol-32.png"));
        JLabel deleteText= new JLabel("Delete Selected Files");
        deleteText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        deleteText.setForeground(colorPalette.light);
        delete.add(deleteIcon);
        delete.add(deleteText);
        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadPanel.getTable().deleteRows();
                checkFiles();
            }
        });
        deleteText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadPanel.getTable().deleteRows();
                checkFiles();
            }
        });

        JPanel clear = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clear.setBackground(colorPalette.background);
        clear.setPreferredSize(new Dimension(250, 50));
        JLabel clearIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-empty-trash-32 (1).png"));
        JLabel clearText= new JLabel("Clear All Files");
        clearText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        clearText.setForeground(colorPalette.light);
        clear.add(clearIcon);
        clear.add(clearText);
        //notification notificationInst = notification.getInstance(uploadPanel.middleTop1);

        clearIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadPanel.getTable().clear();
                notification.showNotificationPopup(controller.getFrame(), "Sucessfully Cleared Files");
                uploadPanel.changeToNoFiles();
                roiPanel.changeToBlank();
            }
        });
        clearText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadPanel.getTable().clear();
                notification.showNotificationPopup(controller.getFrame(), "Sucessfully Cleared Files");
                uploadPanel.changeToNoFiles();
                roiPanel.changeToBlank();
            }
        });

        JPanel sideOptions = new JPanel();
        sideOptions.setLayout(new BoxLayout(sideOptions, BoxLayout.Y_AXIS));
        sideOptions.setBackground(colorPalette.background);
        sideOptions.add(roi);
        //sideOptions.add(export);
        sideOptions.add(clear);
        sideOptions.add(delete);

        add(sideOptions);
        add(tableScroll);
    }

    /**
     * Function is only used with the delete option. Checks if all files were deleted and acts accordingly to give user feedback
     */
    private void checkFiles(){
        //checking if all files were deleted
        if (uploadPanel.getTable().empty()){
            notification.showNotificationPopup(controller.getFrame(), "All Files Have Been Deleted");
            uploadPanel.changeToNoFiles();
            roiPanel.changeToBlank();
        }
    }
}
