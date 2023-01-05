/**
 * @file Menu.java
 * @date 27/12/2022
 * @brief Menu GameScene
 */
package view;

import static utils.GameStates.PLAYING;
import static utils.GameStates.SetGameState;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

import ui.Button;

public class Menu extends GameScene implements SceneMethods {
	private ArrayList<Button> buttons;

	/**
	 * @brief Constructor
	 * @param GUIManager
	 */
	public Menu(GUIManager GUIManager) {
		super(GUIManager);
		initButtons();
	}

	private void initButtons() {
		int buttonWidth = 150;
		int buttonHeight = buttonWidth / 3;
		int x = this.GUIManager.getScreen().getSize().width / 2 - buttonWidth / 2;
		int y = 200;
		int yOffset = buttonHeight + 50;

		this.buttons = new ArrayList<>();
		this.buttons.add(new Button("Play", x, y, buttonWidth, buttonHeight, 0));
		this.buttons.add(new Button("Quit", x, y + yOffset, buttonWidth, buttonHeight, 1));
	}

	// Control
	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		this.buttons.forEach(button -> button.draw(g));
	}

	@Override
	public void mouseClicked(int x, int y) {
		this.buttons.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				switch (button.getId()) {
					case 0 -> SetGameState(PLAYING);
					case 1 -> System.exit(0);
				}
			}
		});
	}

	@Override
	public void mouseMoved(int x, int y) {
		this.buttons.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
	}

	@Override
	public void mousePressed(int x, int y) {
		this.buttons.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));
	}

	@Override
	public void mouseReleased(int x, int y) {
		this.buttons.forEach(Button::resetBooleans);
	}

	@Override
	public void mouseDragged(int x, int y) {}
}
