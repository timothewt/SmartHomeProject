/**
 * @file Task.java
 * @date 14/12/2022
 * @brief Display different type of view
 */
package main;

import java.awt.Graphics;

public class Render {

	private Game game;

	/**
	 * @brief Constructor
	 * @param game
	 */
	public Render(Game game) {

		this.game = game;
	}

	public void render(Graphics g) {

		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().render(g);
			break;
		case PLAYING:
			game.getPlay().render(g);
			break;
		default:
			break;
		}
	}
}
