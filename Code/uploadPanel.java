import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class uploadPanel extends JPanel{
    
    private pathTable pathTable = new pathTable();

    public uploadPanel(){

        //side panel containing all the icons for the menu
        JPanel sidePanel = new JPanel();
        sidePanel = sideMenu.getSideMenu();

        //top panel, color of the background
        JPanel middleTop1 = new JPanel();
        middleTop1.setPreferredSize(new Dimension(800, 100));
        middleTop1.setBackground(colorPalette.background);
        topMiddle(middleTop1);

        //next panel containing the medium color 
        JPanel middleTop2 = new JPanel();
        middleTop2.setPreferredSize(new Dimension(850, 100));
        middleTop2.setBackground(colorPalette.med);
        middleMiddle(middleTop2);

        //middle panel containing most of the content 
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(850, 1000));
        middlePanel.setBackground(colorPalette.background);
        bottomMiddle(middlePanel);

        //setting the panels layout to border layout
        setLayout(new BorderLayout());

        //creatting a wrapper to layer the middle panels 
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        //adding the middle panels 
        wrapperPanel.add(middleTop1);
        wrapperPanel.add(middleTop2);
        wrapperPanel.add(middlePanel);

        //adding panels to format the main panel
        add(wrapperPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);
    }

    private void topMiddle(JPanel middleTop1){

        middleTop1.setLayout(new BorderLayout());

        //topSpace panel 
        JPanel topSpace = new JPanel();
        topSpace.setPreferredSize(new Dimension(1000, 25));
        topSpace.setBackground(colorPalette.background);

        //logout panel
        JPanel logout = new JPanel();
        logout.setPreferredSize(new Dimension(180, 50));
        logout.setBackground(colorPalette.background);
        //adding elements to logout panel
        JLabel logoutLabel = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-logout-rounded-32.png"));
        JLabel logoutText = new JLabel("Logout");
        logoutText.setFont(new Font("Arial", Font.PLAIN, 30));//resizing text within label
        logoutText.setForeground(colorPalette.light);
        //adding mouse listeners to the jlabels 
        logoutLabel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);//ends program
            }
        });
        logoutText.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);//ends program
            }
        });
        //adding elements to appName panel
        logout.add(logoutLabel);
        logout.add(logoutText);
      
        //adding panels into the main panel
        middleTop1.add(topSpace, BorderLayout.NORTH);
        middleTop1.add(logout, BorderLayout.EAST);
    }

    private void middleMiddle(JPanel middleTop2){
        middleTop2.setLayout(new BorderLayout());
        
        //topSpace panel
        JPanel topSpace = new JPanel();
        topSpace.setBackground(colorPalette.med);
        topSpace.setPreferredSize(new Dimension(850, 30));
        
        //upload panel
        JPanel upload = new JPanel();
        upload.setBackground(colorPalette.med);
        upload.setPreferredSize(new Dimension(600, 50));
        //adding elements to panel
        JLabel uploadText = new JLabel("Upload eBay Order Reciept PDFs");
        uploadText.setFont(new Font("Arial", Font.PLAIN, 40));//resizing text within label
        uploadText.setForeground(colorPalette.light);
        //adding elements to upload panel
        upload.add(uploadText);

        //adding panels into the main panel 
        middleTop2.add(topSpace, BorderLayout.NORTH);
        middleTop2.add(upload, BorderLayout.WEST);

    }

    private void bottomMiddle(JPanel middlePanel){

        middlePanel.setLayout(new BorderLayout());

        //creating cardPanel with cardLayout to change panels when files have been uploaded to the program
        JPanel cardPanel = new JPanel(new CardLayout());
        CardLayout c1 = (CardLayout)(cardPanel.getLayout());
        JPanel noFiles =  noFilesUploded();
        JPanel filesUploaded = fileUploadSucess(c1, cardPanel);
        //adding both panels into the card panel
        cardPanel.add(noFiles, "No Files");
        cardPanel.add(filesUploaded, "Files");
        //showing no files panel to start 
        c1.show(cardPanel, "No Files");

        //topPanel panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(colorPalette.background);
        topPanel.setPreferredSize(new Dimension(1000, 400));
        //adding elements to panel
        JPanel topCenter = new JPanel();
        topCenter = roundPanelBorder.roundBorder();
        topCenter.setBackground(colorPalette.background);
        JLabel centerIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-medical-file-96.png"));
        JLabel centerText1 = new JLabel("Select a PDF order reciept to upload");
        centerText1.setFont(new Font("Arial", Font.PLAIN, 20));
        centerText1.setForeground(colorPalette.light);
        JLabel centerText2 = new JLabel("or drag and drop it here");
        centerText2.setFont(new Font("Arial", Font.PLAIN, 15));
        centerText2.setForeground(Color.GRAY);
        //adding elements to center panel
        topCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        topCenter.add(centerIcon, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        topCenter.add(centerText1, gbc);
        gbc.gridy = 2;
        topCenter.add(centerText2, gbc);

        //adding function to panel incase user decides to upload pdf files instead of dragging and dropping 
        topCenter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ROIManager.readInFiles();

                //ensuring that information was sucessfully uploaded 
                if(pathTable.returnRowCount() > 0){
                    c1.show(cardPanel, "Files");
                } else {
                    c1.show(cardPanel, "No Files");
                }
            }
        });

        //adding elements to topPanel panel
        topPanel.add(topCenter, BorderLayout.CENTER);

        //bottomPanel panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        bottomPanel.setBackground(colorPalette.background);
        bottomPanel.setPreferredSize(new Dimension(1000, 400));
        //adding elements to bottomPanel panel
        bottomPanel.add(cardPanel, BorderLayout.CENTER);

        //adding panels into the main panel
        middlePanel.add(topPanel, BorderLayout.NORTH);
        middlePanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel fileUploadSucess(CardLayout c1, JPanel cardPanel){

        //adding elements to panel
        JPanel bottomCenter = new JPanel();
        bottomCenter.setBackground(colorPalette.background);

        JTable table = pathTable.getTable();
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
                pathTable.deleteRows();
                changePanel(pathTable, c1, cardPanel);
            }
        });
        deleteText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pathTable.deleteRows();
                changePanel(pathTable, c1, cardPanel);
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
        clearIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pathTable.clear();
                changePanel(pathTable, c1, cardPanel);
            }
        });
        clearText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pathTable.clear();
                changePanel(pathTable, c1, cardPanel);
            }
        });

        JPanel sideOptions = new JPanel();
        sideOptions.setLayout(new BoxLayout(sideOptions, BoxLayout.Y_AXIS));
        sideOptions.setBackground(colorPalette.background);
        sideOptions.add(roi);
        sideOptions.add(export);
        sideOptions.add(clear);
        sideOptions.add(delete);

        bottomCenter.add(sideOptions);
        bottomCenter.add(tableScroll);
        return bottomCenter;
    }

    /**
     * Panel consisting of an icoan and text in the center that say no files have been uploaded 
     */
    private JPanel noFilesUploded(){
        JPanel panel = new JPanel();
        panel.setBackground(colorPalette.background);
        //creating elements 
        JLabel emptyIcon = new JLabel(new ImageIcon("UI Formatting/Icons/icons8-empty-box-96.png"));
        JLabel emptyText = new JLabel("No files have been uploaded");
        emptyText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        emptyText.setForeground(colorPalette.light);
        //adding elements to panel
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(emptyIcon, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(emptyText, gbc);
        gbc.gridy = 2;

        return panel;
    }

    /**
     * Uses pathTable for tables current state and then determines if the panel sould change on cardPanel through CardLayout
     * @param pathTable table where files were uploaded and organized 
     * @param c1 cardlayout
     * @param cardPanel panel that changes 
     */
    private void changePanel(pathTable pathTable, CardLayout c1, JPanel cardPanel){
        //ensuring that information was sucessfully uploaded 
        if (pathTable.empty()){
            c1.show(cardPanel, "No Files");//changing to no files panel
            cardPanel.repaint();
            cardPanel.revalidate();
        }
    }
}
