/**
 * @file Play.java
 * @date 30/12/2022
 * @brief Play view within the game
 */
package view;

import java.awt.Graphics;

import model.Game;
import ui.DaysUI;
import ui.InfoBar;
import ui.TasksUI;
import utils.PlayingStates;

public class InGame extends GameScene implements SceneMethods {

	private final Game game;
	private final InfoBar bottomBar;
	private final TasksUI taskUI;
	private final DaysUI daysUI;
	private final GUIManager GUIManager;

	public InGame(GUIManager GUIManager) {
		super(GUIManager);
		this.GUIManager = GUIManager;
		this.game = new Game();
		bottomBar = new InfoBar(0, 640, 640, 90, this);
		taskUI = new TasksUI(0, 0, 640, 640, this);
		daysUI = new DaysUI(0, 0, 640, 640, this);
	}

	public void render(Graphics g) {
		bottomBar.draw(g);
		
		switch (PlayingStates.playingState) {
		case TASK:
			taskUI.draw(g);
			break;
		case DAY:
			daysUI.draw(g);
			break;
		case PERK:
			break;
		}
	}

	// Control
	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			bottomBar.mouseClicked(x, y);

		taskUI.mouseClicked(x, y);
		daysUI.mouseClicked(x, y);
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			bottomBar.mouseMoved(x, y);

		taskUI.mouseMoved(x, y);
		daysUI.mouseMoved(x, y);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640)
			bottomBar.mousePressed(x, y);

		taskUI.mousePressed(x, y);
		daysUI.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {
		bottomBar.mouseReleased(x, y);
		taskUI.mouseReleased(x, y);
		daysUI.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {}

	// Getters and setters
	public Game getGame() {
		return this.game;
	}

	public GUIManager getMain() {
		return this.GUIManager;
	}

	public TasksUI getTaskBar() {
		return taskUI;
	}

}
