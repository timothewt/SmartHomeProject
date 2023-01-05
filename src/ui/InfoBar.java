/**
 * @file ActionBar.java
 * @date 27/12/2022
 * @brief Create a ui visible at the bottom of the window when they player is in game
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import model.Person;
import view.InGame;

import static utils.GameStates.*;

public class InfoBar extends UIComponent {

	private final InGame inGame;
	private ArrayList<Button> buttons;

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
		this.buttons = new ArrayList<>();
		this.buttons.add(new Button("Menu", x + 2, y + 2, 100, 30, 0));
		this.buttons.add(new Button("Save", x + 2, y + 42, 100, 30, 1));
	}

	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(this.x, this.y, this.width, this.height);
		g.drawLine(this.x, this.y - 1, this.width, this.height);
		drawButtons(g);
		drawText(g);
	}

	private void drawButtons(Graphics g) {
		this.buttons.forEach(button -> button.draw(g));
	}

	/**
	 * @brief Draw information about the Couple and the Weather
	 * @param g
	 */
	private void drawText(Graphics g) {

		Graphics2D graphics2D = (Graphics2D) g;

		graphics2D.drawLine(x, y, x + width, y);

		graphics2D.drawString("Money : " + inGame.getGame().getHouse().getCouple().getMoney(), this.x + 110, this.y + 15);
		graphics2D.drawString("Energy : " + inGame.getGame().getHouse().getEnergy(), this.x + 110, this.y + 35);

		for (Person person: inGame.getGame().getHouse().getCouple().getPersons()) {
			graphics2D.drawString(person.toString(), this.x + 200, this.y + 15 + 20 * person.getId());
		}

		graphics2D.drawString(inGame.getGame().getWeather().toString(), this.x + 2, this.y + 90);
		graphics2D.drawString(inGame.getGame().getHouse().toString(), this.x + 2, this.y + 110);
	}

	// Control
	public void mouseClicked(int x, int y) {
		this.buttons.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				switch (button.getId()) {
					case 0 -> SetGameState(MENU);
					case 1 -> System.out.println("Game saved ! Not done yet, only this message in terminal to test");
				}
			}
		});
	}

	public void mouseMoved(int x, int y) {
		this.buttons.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
	}

	public void mousePressed(int x, int y) {
		this.buttons.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));
	}

	public void mouseReleased(int x, int y) {
		this.buttons.forEach(Button::resetBooleans);
	}
}
