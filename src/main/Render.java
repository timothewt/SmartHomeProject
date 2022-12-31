/**
 * @file Task.java
 * @date 14/12/2022
 * @brief Display different type of view
 */
package main;

import utils.GameStates;

import java.awt.Graphics;

public class Render {

	private Main main;

	/**
	 * @brief Constructor
	 * @param main
	 */
	public Render(Main main) {

		this.main = main;
	}

	public void render(Graphics g) {

		switch (GameStates.gameState) {
		case MENU:
			main.getMenu().render(g);
			break;
		case PLAYING:
			main.getPlay().render(g);
			break;
		default:
			break;
		}
	}
}
