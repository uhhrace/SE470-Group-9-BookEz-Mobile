import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;

public class controller extends JPanel{

    private static controller instance;

    JPanel cards;//create panel of cards

    //creating each classes panel to add to cards
    homePanel mainPanel;
    photoPanel photoPanel;
    roiPanel roiPanel;

    public controller(){
        setLayout(new BorderLayout());
        setSize(500, 500);
        cards = new JPanel(new CardLayout());

        mainPanel = new homePanel();
        photoPanel = new photoPanel();
        roiPanel = new roiPanel();
        
        cards.add(mainPanel, "Homescreen");
        cards.add(photoPanel, "Upload Photos");
        cards.add(roiPanel, "View ROI Table");

        add(cards);
        setVisible(true);

    }//end of public controller 

    //dispalying homescreen panel to begin 
    private static void createAndDisplay(){
        JFrame frame = new JFrame("BookEz");

        instance = new controller();

        frame.getContentPane().add(instance);
        frame.setSize(950, 950);
        frame.setVisible(true);
    }//end of create and display 

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

}//end of controller 
