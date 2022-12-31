package controller.ingame;

import utils.GameStates;
import view.GUIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBtnListener implements ActionListener {

    GUIManager guiManager;

    public MenuBtnListener(GUIManager guiManager) {
        this.guiManager = guiManager;
    }

    public void actionPerformed(ActionEvent e) {
        GameStates.SetGameState(GameStates.MENU);
        guiManager.updateGUI();
    }

}

