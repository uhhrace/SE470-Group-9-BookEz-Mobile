package UI_Formatter;

import javax.swing.*;
import java.awt.*;

public class notification{
    
    /**
     * Creates and displays a pop-up message that spans the entire top of the frames content 
     * @param frame where message is added on to
     * @param message message to be displayed
     * @param green determines the notification color
     */
    public static void showNotificationPopup(JFrame frame, String message, boolean green) {
        JPanel notificationPanel = new JPanel();
        JLabel messageLabel = new JLabel(message);
        
        //formatting the panel and label
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        messageLabel.setForeground(Color.WHITE);

        //adding element into the panel
        notificationPanel.add(messageLabel);

        if(green){//if color should be green then change the background 
            notificationPanel.setBackground(colorPalette.notificationColor);
        } else {//if color should be red 
            notificationPanel.setBackground(colorPalette.negativeNotificationColor);
        }
    
        //creating the layeredPane
        JLayeredPane layeredPane = frame.getRootPane().getLayeredPane();
        layeredPane.add(notificationPanel, JLayeredPane.POPUP_LAYER);
        notificationPanel.setBounds(0, 0, frame.getWidth(), notificationPanel.getPreferredSize().height);
    
        //auto-hide the notification after 3 seconds
        Timer timer = new Timer(3000, e -> {
            layeredPane.remove(notificationPanel);
            layeredPane.revalidate();
            layeredPane.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
}