/**
 * @file Play.java
 * @date 30/12/2022
 * @brief Play view within the game
 */
package view;

import model.Game;
import ui.InfoBar;
import ui.TasksUI;
import utils.PlayingStates;

public class InGame extends GameScene {

	private final Game game;
	private final InfoBar infoBar;
	private final TasksUI taskUI;
	private final GUIManager guiManager;

	public InGame(GUIManager guiManager) {
		super(guiManager);
		this.guiManager = guiManager;
		this.game = new Game();
		taskUI = new TasksUI(0, 0, 680, 580, this);
		infoBar = new InfoBar(0, 580, 680, 130, this);
	}

	public void setVisible(boolean isVisible) {
		switch (PlayingStates.playingState) {
			case PICKING_TASKS:
				//this.taskUI.setVisible(isVisible);
				break;
			case DURING_DAY:
				this.taskUI.setVisible(false);
				break;
			case BUYING_PERKS:
				break;
		}
		infoBar.setVisible(isVisible);
	}

	// Getters and setters
	public Game getGame() {
		return this.game;
	}

	public GUIManager getGUIManager() {
		return this.guiManager;
	}

	public TasksUI getTaskUI() {
		return taskUI;
	}

	public InfoBar getInfoBar() {
		return infoBar;
	}

}
