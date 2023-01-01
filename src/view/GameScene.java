/**
 * @file GameScene.java
 * @date 27/12/2022
 * @brief Create the Game Scene
 */
package view;

public class GameScene {

	protected GUIManager GUIManager;

	public GameScene(GUIManager GUIManager) {
		this.GUIManager = GUIManager;
	}

	public GUIManager getMain() {
		return GUIManager;
	}

}
