/**
 * @file Task.java
 * @date 14/12/2022
 * @brief Display different type of view
 */
package view;

import utils.GameStates;

import java.awt.Graphics;

public class Render {

	private GUIManager GUIManager;

	/**
	 * @brief Constructor
	 * @param GUIManager
	 */
	public Render(GUIManager GUIManager) {

		this.GUIManager = GUIManager;
	}

	public void render(Graphics g) {

		switch (GameStates.gameState) {
			case MENU -> GUIManager.getMenu().render(g);
			case PLAYING -> GUIManager.getPlay().render(g);
			default -> {
			}
		}
	}
}
