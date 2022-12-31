/**
 * @file Menu.java
 * @date 27/12/2022
 * @brief Menu GameScene
 */
package view;

import java.util.ArrayList;
import javax.swing.*;

import controller.menu.PlayBtnListener;
import controller.menu.QuitBtnListener;


public class Menu extends GameScene {

	private ArrayList<JButton> buttons;

	/**
	 * Constructor
	 */
	public Menu(GUIManager GUIManager) {
		super(GUIManager);
		initButtons();
	}

	private void initButtons() {
		this.buttons = new ArrayList<>();

		int width = 150;
		int height = width / 3;
		int x = 640 / 2 - width / 2;
		int y = 150;
		int verticalGap = 40;

		this.buttons.add(this.createJButton("Play", x, y, width, height, new PlayBtnListener(this.GUIManager)));
		this.buttons.add(this.createJButton("Quit", x, y + height + verticalGap, width, height, new QuitBtnListener()));

		for (JButton button: this.buttons) {
			this.GUIManager.add(button);
		}
	}

	public void setVisible(boolean isVisible) {
		for (JButton button: this.buttons) {
			button.setVisible(isVisible);
		}
	}

	public ArrayList<JButton> getButtons() {
		return this.buttons;
	}
}
