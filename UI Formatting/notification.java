import javax.swing.*;
import java.awt.*;

public class notification{
    
    /**
     * Creates and displays a pop-up message that spans the entire top of the frames content 
     * @param frame where message is added on to
     * @param message message to be displayed
     */
    public static void showNotificationPopup(JFrame frame, String message) {
        JPanel notificationPanel = new JPanel();
        JLabel messageLabel = new JLabel(message);
        
        //formatting the panel and label
        notificationPanel.setBackground(colorPalette.notificationColor);
        notificationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        messageLabel.setForeground(colorPalette.light);

        //adding element into the panel
        notificationPanel.add(messageLabel);
    
        //creating the layeredPane
        JLayeredPane layeredPane = frame.getRootPane().getLayeredPane();
        layeredPane.add(notificationPanel, JLayeredPane.POPUP_LAYER);
        notificationPanel.setBounds(0, 0, frame.getWidth(), notificationPanel.getPreferredSize().height);
    
        // Auto-hide the notification after 3 seconds
        Timer timer = new Timer(3000, e -> {
            layeredPane.remove(notificationPanel);
            layeredPane.revalidate();
            layeredPane.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
}