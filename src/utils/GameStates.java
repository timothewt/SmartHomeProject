/**
 * @file GameStates.java
 * @date 27/12/2022
 * @brief Enum that represent the current scene
 */
package utils;

public enum GameStates {

	PLAYING, MENU, SETTINGS;

	public static GameStates gameState = MENU;

	/**
	 * @Brief modify the state of the game -> the UI where the player is
	 * @param state : The new state
	 */
	public static void SetGameState(GameStates state) {

		gameState = state;
	}
}
