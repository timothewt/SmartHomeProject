package view;

import java.awt.Graphics;
import main.Game;
import static view.GameStates.PLAYING;
import static view.GameStates.SETTINGS;
import static view.GameStates.SetGameState;


public class Render {
	
	private Game game;
	
	public Render(Game game){
		
		this.game = game;
	}
	
	public void render(Graphics g){
		
		switch(GameStates.gameState)
		{
		case MENU:
			game.getMenu().render(g);
			break;
		case PLAYING:
			game.getPlaying().render(g);
			break;
		}
	}
}
