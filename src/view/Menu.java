package view;

import static view.GameStates.PLAYING;
import static view.GameStates.SETTINGS;
import static view.GameStates.SetGameState;

import java.awt.Graphics;

import main.Game;
import view.Button;

public class Menu extends GameScene implements SceneMethods {
	private Button bPlaying, bEdit, bSettings, bQuit;

	public Menu(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new Button("Play", x, y, w, h);
		bQuit = new Button("Quit", x, y + yOffset, w, h);
	}

	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bQuit.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setIsMouseOver(false);
		bQuit.setIsMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setIsMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setIsMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setIsMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setIsMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}
}
