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

public class InfoBar extends UIComponent {

	private InGame inGame;
	private Button btnMenu, btnSave;

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
		btnMenu = new Button("Menu", 2, 642, 100, 30);
		btnSave = new Button("Save", 2, 674, 100, 30);
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
		btnMenu.draw(g);
		btnSave.draw(g);

	}

	/**
	 * @brief Draw information about the Couple and the Weather
	 * @param g
	 */
	private void drawText(Graphics g) {

		Graphics2D money = (Graphics2D) g;
		money.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		money.drawString("Money : " + inGame.getGame().getHouse().getCouple().getMoney(), 110, 660);

		Graphics2D energy = (Graphics2D) g;
		energy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		energy.drawString("Energy : " + inGame.getGame().getHouse().getEnergy(), 110, 680);

		Graphics2D jean = (Graphics2D) g;
		jean.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		jean.drawString(inGame.getGame().getHouse().getCouple().getPersons().get(0).toString(), 110 + 90, 660);

		Graphics2D marie = (Graphics2D) g;
		marie.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		marie.drawString(inGame.getGame().getHouse().getCouple().getPersons().get(1).toString(), 110 + 90, 680);

		Graphics2D weather = (Graphics2D) g;
		weather.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		weather.drawString(inGame.getGame().getWeather().toString(), 2, 720);
	}

	// Control
	public void mouseClicked(int x, int y) {
		if (btnMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if (btnSave.getBounds().contains(x, y))
			System.out.println("Game saved ! Not done yet, only this message in terminal to test");
	}

	public void mouseMoved(int x, int y) {
		btnMenu.setIsMouseOver(false);
		btnSave.setIsMouseOver(false);

		if (btnMenu.getBounds().contains(x, y))
			btnMenu.setIsMouseOver(true);
		else if (btnSave.getBounds().contains(x, y))
			btnSave.setIsMouseOver(true);
	}

	public void mousePressed(int x, int y) {
		btnMenu.setIsMousePressed(false);
		btnSave.setIsMousePressed(false);

		if (btnMenu.getBounds().contains(x, y))
			btnMenu.setIsMousePressed(true);
		else if (btnSave.getBounds().contains(x, y))
			btnSave.setIsMousePressed(true);
	}

	public void mouseReleased(int x, int y) {
		btnMenu.resetBooleans();
		btnSave.resetBooleans();
	}
}
