/**
 * @file ActionBar.java
 * @date 27/12/2022
 * @brief Create a ui visible at the bottom of the window when they player is in game
 */
package ui;

import java.util.ArrayList;
import javax.swing.*;

import controller.ingame.MenuBtnListener;
import controller.ingame.SaveBtnListener;
import model.Person;
import view.InGame;

public class InfoBar extends UIComponent {

	private final InGame inGame;

	/**
	 * constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param inGame
	 */
	public InfoBar(int x, int y, int width, int height, InGame inGame) {
		super(x, y, width, height);
		this.inGame = inGame;
		initButtons();
	}

	private void initButtons() {
		this.buttons.add(this.inGame.createJButton("Menu", x + 2, y + 2, 100, 30, new MenuBtnListener(this.inGame.getGUIManager())));
		this.buttons.add(this.inGame.createJButton("Save", x + 2, y + 34, 100, 30, new SaveBtnListener()));

		for (JButton button: this.buttons) {
			this.inGame.getGUIManager().add(button);
		}
	}

	@Override
	public void setVisible(boolean isVisible) {
		this.updateLabels();
		super.setVisible(isVisible);
	}

	/**
	 * Draws information about the Couple and the Weather
	 */
	private void updateLabels() {
		for (JLabel label: this.labels) {
			this.inGame.getGUIManager().remove(label);
		}

		this.labels = new ArrayList<>();
		this.labels.add(this.inGame.createJLabel("Money : " + inGame.getGame().getHouse().getCouple().getMoney(), x + 110, y + 2, 150, 20));
		this.labels.add(this.inGame.createJLabel("Energy : " + inGame.getGame().getHouse().getEnergy(), x + 110, y + 22, 150, 20));
		for (Person person: inGame.getGame().getHouse().getCouple().getPersons()) {
			this.labels.add(this.inGame.createJLabel(person.toString(), x + 200, y + (20 * person.getId()) + 2, 500, 20));
			this.labels.add(this.inGame.createJLabel(inGame.getGame().getWeather().toString(), x + 2, y + 80, 660, 20));
			this.labels.add(this.inGame.createJLabel(inGame.getGame().getHouse().toString(), x + 2, y + 100, 660, 20));
		}

		for (JLabel label: this.labels) {
			System.out.println(label);
			this.inGame.getGUIManager().add(label);
		}
	}
}
