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
import ui.PerksUI;
import ui.TasksUI;
import utils.PlayingStates;

public class InGame extends GameScene implements SceneMethods {

	private final Game game;
	private final InfoBar infoBar;
	private final TasksUI tasksUI;
	private final DuringDayUI duringDayUI;
	private final PerksUI perksUI;
	private final GUIManager GUIManager;
	private final int gameElementsHeight;

	public InGame(GUIManager GUIManager) {
		super(GUIManager);
		this.GUIManager = GUIManager;
		this.gameElementsHeight = this.GUIManager.getScreen().getSize().height - 120;
		this.game = new Game();
		int width = this.GUIManager.getScreen().getSize().width;
		infoBar = new InfoBar(0, gameElementsHeight, width, 120, this);
		tasksUI = new TasksUI(0, 0, width, gameElementsHeight, this);
		duringDayUI = new DuringDayUI(0, 0, width, gameElementsHeight, this);
		perksUI = new PerksUI(0, 0, width, gameElementsHeight, this);
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
				perksUI.draw(g);
				break;
		}
	}

	// Control
	@Override
	public void mouseClicked(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBar.mouseClicked(x, y);
		
		switch (PlayingStates.playingState) {
			case TASK -> {
				tasksUI.mouseClicked(x, y);
			}
			case DAY -> {
				duringDayUI.mouseClicked(x, y);
			}
			case PERK -> {
				perksUI.mouseClicked(x, y);
			}
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBar.mouseMoved(x, y);
		
		switch (PlayingStates.playingState) {
			case TASK -> {
				tasksUI.mouseMoved(x, y);
			}
			case DAY -> {
				duringDayUI.mouseMoved(x, y);
			}
			case PERK -> {
				perksUI.mouseMoved(x, y);
			}
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBar.mousePressed(x, y);
		
		switch (PlayingStates.playingState) {
			case TASK -> {
				tasksUI.mousePressed(x, y);
			}
			case DAY -> {
				duringDayUI.mousePressed(x, y);
			}
			case PERK -> {
				perksUI.mousePressed(x, y);
			}
		}	
	}

	@Override
	public void mouseReleased(int x, int y) {
		infoBar.mouseReleased(x, y);
		
		switch (PlayingStates.playingState) {
			case TASK -> {
				tasksUI.mouseReleased(x, y);
			}
			case DAY -> {
				duringDayUI.mouseReleased(x, y);
			}
			case PERK -> {
				perksUI.mouseReleased(x, y);
			}
		}		
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
