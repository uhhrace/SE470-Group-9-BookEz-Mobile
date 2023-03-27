package UI_Formatter;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.geom.RoundRectangle2D;

public class roundPanelBorder extends AbstractBorder{

    /**
     * Constructor that creates a dashed rounded border for a JPanel. The border is light blue 
     * @return JPanel containing dashed border 
     */
    public static JPanel roundBorder(){

        JPanel panel = new JPanel();//creating panel to be returned 
        panel.setBackground(colorPalette.background);//settings its background color 

        //creating dashes and painting them onto the panel
        float dash[] = {10.0f};
        BasicStroke bs = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, dash, 0.0f);
        Border border = new Border() {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(bs);
            g2d.setColor(colorPalette.light);
            RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0,0,panel.getWidth()-1,panel.getHeight()-1,15,15);
            g2d.draw(r2d);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5,5,5,5);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
        };

        panel.setBorder(border);//settings panels border 
        return panel;//returning panel with border 
    }
}
    
