package view;

public enum PlayingStates {

	ONE,
	TWO,
	THREE;
	//temp name to create the enum
	
	public static PlayingStates playingState = ONE;
	
	/**	
	 * @Brief modify the state of the game -> the UI where the player is
	 * @param state : The new state
	 */
	public static void SetGameState(PlayingStates state){
		
		playingState = state;
	}
}
