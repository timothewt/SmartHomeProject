/**
 * @file PlayingStates.java
 * @date 27/12/2022
 * @brief Use to show the good GUI while playing
 */
package utils;

public enum PlayingStates {

	PICKING_TASKS, DURING_DAY, BUYING_PERKS;

	public static PlayingStates playingState = PICKING_TASKS;

	/**
	 * @Brief modify the state of the game -> the UI where the player is
	 * @param state : The new state
	 */
	public static void SetPlayingState(PlayingStates state) {
		playingState = state;
	}
}
