package UI_Formatter;

import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class homeSlideShow extends Thread {
    private ArrayList<JLabel> images;
    private JPanel slideShowPanel;
    private int currentImageIndex;
    private ButtonGroup buttonGroup;
    private JRadioButton[] radioButtons;
    public boolean isRunning = false;

    /**
     * Collecting and initializing all elements for the slide show 
     * @param images images that will be used 
     * @param slideShowPanel panel that the slide show will take place on 
     */
    public homeSlideShow(ArrayList<JLabel> images, JPanel slideShowPanel) {
        this.images = images;
        this.slideShowPanel = slideShowPanel;
        currentImageIndex = 0;

        //initialize the button group and radio buttons
        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[images.size()];
        for (int i = 0; i < images.size(); i++) {
            radioButtons[i] = new JRadioButton();
            buttonGroup.add(radioButtons[i]);
        }
    }

    /**
     * Running the thread when the function is called 
     */
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                slideShowPanel.removeAll();
                slideShowPanel.add(images.get(currentImageIndex));

                //add the radio buttons to the bottom of the panel
                JPanel radioButtonPanel = new JPanel();
                radioButtonPanel.setBackground(colorPalette.background);
                for (JRadioButton radioButton : radioButtons) {
                    radioButton.setEnabled(false);//taking off the user ability to select the buttons 
                    radioButtonPanel.add(radioButton);
                }
                slideShowPanel.add(radioButtonPanel);

                slideShowPanel.revalidate();
                slideShowPanel.repaint();

                //select the current radio button to show which photo is being displayed
                radioButtons[currentImageIndex].setSelected(true);

                currentImageIndex = (currentImageIndex + 1) % images.size();

                Thread.sleep(3000); //change the image every 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stopping the thread when the function is called 
     */
    public void stopThread(){
        isRunning = false;
    }
}
