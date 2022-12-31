/**
 * @file ActionBar.java
 * @date 27/12/2022
 * @brief Create a ui visible at the bottom of the window when they player is in game
 */
package ui;

import static utils.GameStates.MENU;
import static utils.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import view.InGame;

public class InfoBar extends Bar {

	private InGame inGame;
	private Button bMenu, bSave;

	/**
	 * @Brief constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param inGame
	 */
	public InfoBar(int x, int y, int width, int height, InGame inGame) {
		super(x, y, width, height);
		initButtons();
		this.inGame = inGame;
	}

	private void initButtons() {
		bMenu = new Button("Menu", 2, 642, 100, 30);
		bSave = new Button("Save", 2, 674, 100, 30);
	}

	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);

		drawText(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);

	}

	/**
	 * @brief Draw information about the Couple and the Weather
	 * @param g
	 */
	private void drawText(Graphics g) {

		Graphics2D money = (Graphics2D) g;
		money.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		money.drawString("Money : " + inGame.getPlaying().getHouse().getCouple().getMoney(), 110, 660);

		Graphics2D energy = (Graphics2D) g;
		energy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		energy.drawString("Energy : " + inGame.getPlaying().getHouse().getEnergy(), 110, 680);

		Graphics2D jean = (Graphics2D) g;
		jean.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		jean.drawString(inGame.getPlaying().getHouse().getCouple().getPersons().get(0).toString(), 110 + 90, 660);

		Graphics2D marie = (Graphics2D) g;
		marie.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		marie.drawString(inGame.getPlaying().getHouse().getCouple().getPersons().get(1).toString(), 110 + 90, 680);

		Graphics2D weather = (Graphics2D) g;
		weather.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		weather.drawString(inGame.getPlaying().getWeather().toString(), 2, 720);
	}

	// Control
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if (bSave.getBounds().contains(x, y))
			System.out.println("Game saved ! Not done yet, only this message in terminal to test");
	}

	public void mouseMoved(int x, int y) {
		bMenu.setIsMouseOver(false);
		bSave.setIsMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setIsMouseOver(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setIsMouseOver(true);
	}

	public void mousePressed(int x, int y) {
		bMenu.setIsMousePressed(false);
		bSave.setIsMousePressed(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setIsMousePressed(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setIsMousePressed(true);
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
	}
}
