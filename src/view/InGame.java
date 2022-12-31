/**
 * @file Play.java
 * @date 30/12/2022
 * @brief Play view within the game
 */
package view;

import java.awt.Graphics;

import model.Game;
import ui.InfoBar;
import ui.TasksUI;

public class InGame extends GameScene implements SceneMethods {

	private final Game game;
	private final InfoBar bottomBar;
	private final TasksUI taskBar;
	private final GUIManager GUIManager;

	public InGame(GUIManager GUIManager) {
		super(GUIManager);
		this.GUIManager = GUIManager;
		this.game = new Game();
		bottomBar = new InfoBar(0, 640, 640, 90, this);
		taskBar = new TasksUI(0, 0, 640, 640, this);
	}

	public void render(Graphics g) {
		bottomBar.draw(g);
		taskBar.draw(g);
	}

	// Control
	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			bottomBar.mouseClicked(x, y);

		taskBar.mouseClicked(x, y);
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			bottomBar.mouseMoved(x, y);

		taskBar.mouseMoved(x, y);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640)
			bottomBar.mousePressed(x, y);

		taskBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {
		bottomBar.mouseReleased(x, y);
		taskBar.mouseReleased(x, y);
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
		return taskBar;
	}

}
