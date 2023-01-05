/**
 * @file GameGUI.java
 * @date 30/12/2022
 * View that manages what is displayed during the game, when the user is playing.
 */
package view;

import java.awt.Graphics;
import model.Game;
import ui.DuringDayUI;
import ui.InfoBarUI;
import ui.TasksUI;
import utils.PlayingStates;

public class GameGUI extends GameScene implements SceneMethods {

	private final Game game; // instance of the game (model)
	private final InfoBarUI infoBarUI; // info bar indicating the information of the weather and the house
	private final TasksUI tasksUI; // UI of the tasks picking
	private final DuringDayUI duringDayUI; // UI of the tasks execution
	private final GUIManager GUIManager; // manages all the views of the application
	private final int gameElementsHeight; // height of the main game component

	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public GameGUI(GUIManager GUIManager) {
		super(GUIManager);
		this.GUIManager = GUIManager;
		this.gameElementsHeight = this.GUIManager.getScreen().getSize().height - 120;
		this.game = new Game();
		int width = this.GUIManager.getScreen().getSize().width;
		infoBarUI = new InfoBarUI(0, gameElementsHeight, width, 120, this);
		tasksUI = new TasksUI(0, 0, width, gameElementsHeight, this);
		duringDayUI = new DuringDayUI(0, 0, width, gameElementsHeight, this);
	}

	/**
	 * Draws a different UI depending on the current state of the game
	 * @param g: graphics component of the app
	 */
	public void render(Graphics g) {
		infoBarUI.draw(g);
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

	/**
	 * Called when the user clicks anywhere on the screen
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
	@Override
	public void mouseClicked(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBarUI.mouseClicked(x, y);
		tasksUI.mouseClicked(x, y);
		duringDayUI.mouseClicked(x, y);
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBarUI.mouseMoved(x, y);
		tasksUI.mouseMoved(x, y);
		duringDayUI.mouseMoved(x, y);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBarUI.mousePressed(x, y);
		tasksUI.mousePressed(x, y);
		duringDayUI.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {
		infoBarUI.mouseReleased(x, y);
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
