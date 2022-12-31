/**
 * @file Menu.java
 * @date 27/12/2022
 * @brief Menu GameScene
 */
package view;

import static utils.GameStates.PLAYING;
import static utils.GameStates.SetGameState;

import java.awt.Graphics;

import ui.Button;

public class Menu extends GameScene implements SceneMethods {
	private Button btnPlaying, btnQuit;

	/**
	 * @brief Constructor
	 * @param GUIManager
	 */
	public Menu(GUIManager GUIManager) {
		super(GUIManager);
		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		btnPlaying = new Button("Play", x, y, w, h);
		btnQuit = new Button("Quit", x, y + yOffset, w, h);
	}

	// Control
	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		btnPlaying.draw(g);
		btnQuit.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (btnPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (btnQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		btnPlaying.setIsMouseOver(false);
		btnQuit.setIsMouseOver(false);

		if (btnPlaying.getBounds().contains(x, y)) {
			btnPlaying.setIsMouseOver(true);
		} else if (btnQuit.getBounds().contains(x, y)) {
			btnQuit.setIsMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (btnPlaying.getBounds().contains(x, y)) {
			btnPlaying.setIsMousePressed(true);
		} else if (btnQuit.getBounds().contains(x, y)) {
			btnQuit.setIsMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		btnPlaying.resetBooleans();
		btnQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {}
}
