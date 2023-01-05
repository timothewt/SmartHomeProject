package view;

import java.awt.Graphics;

import ui.Button;

import static utils.GameStates.MENU;
import static utils.GameStates.SetGameState;

public class GameOver extends GameScene implements SceneMethods {

	private GUIManager GUIManager;
	private Button menuButton;
	
	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public GameOver(GUIManager GUIManager) {
		super(GUIManager);
		this.GUIManager = GUIManager;
		initButtons();
	}
	
	public void initButtons() {
		int buttonWidth = 150;
		int buttonHeight = buttonWidth / 3;
		int x = this.GUIManager.getScreen().getSize().width / 2 - buttonWidth / 2;
		int y = 300;
		this.menuButton = new Button("Menu", x, y, buttonWidth, buttonHeight);
	}

	@Override
	public void render(Graphics g) {
		this.menuButton.draw(g);
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (this.menuButton.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		this.menuButton.setIsMouseOver(this.menuButton.getBounds().contains(x, y));
		
	}

	@Override
	public void mousePressed(int x, int y) {
		this.menuButton.setIsMousePressed(this.menuButton.getBounds().contains(x, y));
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		this.menuButton.resetBooleans();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
