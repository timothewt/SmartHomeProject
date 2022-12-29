package view;

import static view.GameStates.MENU;
import static view.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;


import model.Playing;

public class ActionBar extends Bar {
	private Playing playing;
	private Button bMenu;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		initButtons();
	}

	private void initButtons() {
		bMenu = new Button("Menu", 2, 642, 100, 30);
	}

	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
	}

	public void mouseMoved(int x, int y) {
		bMenu.setIsMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setIsMouseOver(true);
	}

	public void mousePressed(int x, int y) {
		bMenu.setIsMousePressed(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setIsMousePressed(true);
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
	}
}
