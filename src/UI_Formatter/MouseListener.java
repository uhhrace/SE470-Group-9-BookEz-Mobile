package UI_Formatter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import contentPanels.homePanel;
import Controller.controller;

public class MouseListener extends MouseAdapter {

    private String panelChange;//panels name under controller

    /**
     * Class obtains panels set name in controller
     * @param panelChange panels name
     */
    public MouseListener(String panelChange) {
        this.panelChange = panelChange;
    }

    /**
     * Once panel name is obtained, the controller then changes to the said panel
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        homePanel.slideShowHandler(panelChange);
        controller.getInstance().changeCard(panelChange);
        
    }
}