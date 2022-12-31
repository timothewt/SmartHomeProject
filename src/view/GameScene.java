/**
 * @file GameScene.java
 * @date 27/12/2022
 * @brief Create the Game Scene
 */
package view;

import main.Main;

public class GameScene {

	protected Main main;

	public GameScene(Main main) {
		this.main = main;
	}

	public Main getMain() {
		return main;
	}

}
