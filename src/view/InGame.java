/**
 * @file Play.java
 * @date 30/12/2022
 * @brief Play view within the game
 */
package view;

import java.awt.Graphics;

import model.Game;
import ui.DuringDayUI;
import ui.InfoBar;
import ui.TasksUI;
import utils.PlayingStates;

public class InGame extends GameScene implements SceneMethods {

	private final Game game;
	private final InfoBar infoBar;
	private final TasksUI tasksUI;
	private final DuringDayUI duringDayUI;
	private final GUIManager GUIManager;

	public InGame(GUIManager GUIManager) {
		super(GUIManager);
		this.GUIManager = GUIManager;
		this.game = new Game();
		infoBar = new InfoBar(0, 640, 640, 90, this);
		tasksUI = new TasksUI(0, 0, 640, 640, this);
		duringDayUI = new DuringDayUI(0, 0, 640, 640, this);
	}

	public void render(Graphics g) {
		infoBar.draw(g);
		
		switch (PlayingStates.playingState) {
		case TASK:
			tasksUI.draw(g);
			break;
		case DAY:
			duringDayUI.draw(g);
			break;
		case PERK:
			break;
		}
	}

	// Control
	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			infoBar.mouseClicked(x, y);

		tasksUI.mouseClicked(x, y);
		duringDayUI.mouseClicked(x, y);
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			infoBar.mouseMoved(x, y);

		tasksUI.mouseMoved(x, y);
		duringDayUI.mouseMoved(x, y);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640)
			infoBar.mousePressed(x, y);

		tasksUI.mousePressed(x, y);
		duringDayUI.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {
		infoBar.mouseReleased(x, y);
		tasksUI.mouseReleased(x, y);
		duringDayUI.mouseReleased(x, y);
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

	public TasksUI getTasksUI() {
		return tasksUI;
	}

	public DuringDayUI getDuringDayUI() {
		return duringDayUI;
	}
}
