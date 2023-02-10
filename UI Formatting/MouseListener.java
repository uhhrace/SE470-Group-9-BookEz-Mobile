import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    private String labelText;

    public MouseListener(String labelText) {
        this.labelText = labelText;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.getInstance().changeCard(labelText);
    }
}