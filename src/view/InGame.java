/**
 * @file Play.java
 * @date 30/12/2022
 * @brief Play view within the game
 */
package view;

import java.awt.Graphics;

import main.Main;
import model.Game;
import ui.InfoBar;
import ui.TaskBar;

public class InGame extends GameScene implements SceneMethods {

	private Game game;
	private InfoBar bottomBar;
	private TaskBar taskBar;
	private Main main;

	public InGame(Main main) {
		super(main);
		this.main = main;
		this.game = new Game();
		bottomBar = new InfoBar(0, 640, 640, 90, this);
		taskBar = new TaskBar(0, 0, 640, 640, this);
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
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	// Getters and setters
	public Game getPlaying() {
		return this.game;
	}

	public Main getGame() {
		return this.main;
	}

	public TaskBar getTaskBar() {
		return taskBar;
	}

}
