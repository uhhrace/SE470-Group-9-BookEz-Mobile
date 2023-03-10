import java.awt.CardLayout;
import javax.swing.*;

public class fileUIController extends JPanel{

    private static fileUIController instance;

    JPanel cards;//create panel of cards

    //creating each classes panel to add to cards
    noFilesPanel noFilesPanel;
    pathFilesPanel pathFilesPanel;
    roiTablePanel roiTablePanel;
    roiPanelContent roiPanelContent;

    public fileUIController(){
        cards = new JPanel(new CardLayout());

        noFilesPanel = new noFilesPanel();
        pathFilesPanel = new pathFilesPanel();
        roiTablePanel = new roiTablePanel();
        roiPanelContent = new roiPanelContent();
        
        cards.add(noFilesPanel, "No Files");
        cards.add(pathFilesPanel, "Path Files");
        cards.add(roiTablePanel, "ROI Table");
        cards.add(roiPanelContent, "ROI Content");
       
        add(cards);
        setVisible(true);

    }//end of public controller 

    //to move between panels 
    public void changeCard(String card){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, card);
    }

    public fileUIController getInstance(){
        if(instance == null){
            instance = new fileUIController();
        } 
        return instance;
    }
}

