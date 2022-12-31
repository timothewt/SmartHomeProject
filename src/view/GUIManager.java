/**
 * @file GUIManager.java
 * @date 27/12/2022
 * @brief manages the current displayed GUI according to the game State
 */
package view;

import utils.GameStates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIManager extends JFrame {

	private Menu menu;
	private ArrayList<JButton> menuButtons;
	private InGame inGame;

	/**
	 * Constructor
	 */
	public GUIManager() {}

	public void startGame() {
		for (JButton button: this.menuButtons) {
			button.setVisible(false);
		}
		this.updateGUI();
	}

	/**
	 * @brief Start the thread
	 */
	public void buildAndRun() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		this.setLayout(null);

		this.menu = new Menu(this);
		this.menu.setVisible(false);
		this.inGame = new InGame(this);
		this.inGame.setVisible(false);
		this.menuButtons = menu.getButtons();

		this.setSize(680, 740);
		setVisible(true);
		this.updateGUI();
	}

	public void updateGUI() {
		switch (GameStates.gameState) {
			case MENU -> {
				inGame.setVisible(false);
				menu.setVisible(true);
			}
			case INGAME -> {
				menu.setVisible(false);
				inGame.setVisible(true);
			}
		}
		revalidate();
	}

	// Getters and setters
	public Menu getMenu() {
		return menu;
	}

	public InGame getInGame() {
		return inGame;
	}
}
