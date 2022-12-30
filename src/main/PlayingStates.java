/**
 * @file PlayingStates.java
 * @date 27/12/2022
 * @brief Use to show the good GUI while playing
 */
package main;

public enum PlayingStates {

	ONE, TWO, THREE;
	// temp name to create the enum

	public static PlayingStates playingState = ONE;

	/**
	 * @Brief modify the state of the game -> the UI where the player is
	 * @param state : The new state
	 */
	public static void SetGameState(PlayingStates state) {

		playingState = state;
	}
}
