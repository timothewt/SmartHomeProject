/**
 * @file Play.java
 * @date 30/12/2022
 * @brief Play view within the game
 */
package view;

import java.awt.Graphics;

import main.Game;
import model.Playing;
import ui.ActionBar;
import ui.TaskBar;

public class Play extends GameScene implements SceneMethods {

	private Playing playing;
	private ActionBar bottomBar;
	private TaskBar taskBar;
	private Game game;

	public Play(Game game) {
		super(game);
		this.game = game;
		this.playing = new Playing();
		bottomBar = new ActionBar(0, 640, 640, 90, this);
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
	public Playing getPlaying() {
		return this.playing;
	}

	public Game getGame() {
		return this.game;
	}

	public TaskBar getTaskBar() {
		return taskBar;
	}

}
