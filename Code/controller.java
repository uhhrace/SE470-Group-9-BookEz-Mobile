import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;

public class controller extends JPanel{

    private static controller instance;
    private static JFrame frame;

    JPanel cards;//create panel of cards

    //creating each classes panel to add to cards
    homePanel homePanel;
    uploadPanel photoPanel;
    roiPanel roiPanel;
    settingsPanel settingsPanel;

    public controller(){
        setLayout(new BorderLayout());
        setSize(500, 500);
        cards = new JPanel(new CardLayout());

        homePanel = new homePanel();
        homePanel.startSlideshow();//statting home slide show when program is opened 
        photoPanel = new uploadPanel();
        roiPanel = new roiPanel();
        settingsPanel = new settingsPanel();
        
        cards.add(homePanel, "Homescreen");
        cards.add(photoPanel, "Upload Photos");
        cards.add(roiPanel, "ROI Table");
        cards.add(settingsPanel, "Settings");
        //Export Files

        add(cards);
        setVisible(true);

    }//end of public controller 

    //dispalying homescreen panel to begin 
    private static void createAndDisplay(){
        frame = new JFrame("BookEz");

        instance = new controller();

        frame.getContentPane().add(instance);
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        createAndDisplay();
    }

    //to move between panels 
    public void changeCard(String card){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, card);
    }

    public static controller getInstance(){
        return instance;
    }

    public static JFrame getFrame(){
        return frame;
    }
}
