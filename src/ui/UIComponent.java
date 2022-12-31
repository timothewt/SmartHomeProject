/**
 * @file ActionBar.java
 * @date 27/12/2022
 * @brief Base class for ui
 */
package ui;

import javax.swing.*;
import java.util.ArrayList;

public class UIComponent {

	protected int x, y, width, height;
	protected ArrayList<JButton> buttons;
	protected ArrayList<JCheckBox> checkBoxes;
	protected ArrayList<JLabel> labels;

	/**
	 * @brief Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public UIComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttons = new ArrayList<>();
		this.checkBoxes = new ArrayList<>();
		this.labels = new ArrayList<>();
	}

	public void setVisible(boolean isVisible) {
		for (JButton button: this.buttons) {
			button.setVisible(isVisible);
		}
		for (JCheckBox checkBox: this.checkBoxes) {
			checkBox.setVisible(isVisible);
		}
		for (JLabel label: this.labels) {
			label.setVisible(isVisible);
		}
	}

}