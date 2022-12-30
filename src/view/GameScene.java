/**
 * @file ActionBar.java
 * @date 27/12/2022
 * @brief Create the Game Scene
 */
package view;

import main.Game;

public class GameScene {
	protected Game game;

	public GameScene(Game game) {
		this.game = game;

	}

	public Game getGame() {
		return game;
	}
}
