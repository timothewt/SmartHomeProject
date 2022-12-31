package controller.menu;
import view.GUIManager;
import utils.GameStates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayBtnListener implements ActionListener {

    private final GUIManager GUIManager;

    public PlayBtnListener(GUIManager GUIManager) {
        this.GUIManager = GUIManager;
    }

    public void actionPerformed(ActionEvent e) {
        GameStates.SetGameState(GameStates.INGAME);
        GUIManager.startGame();
    }

}
