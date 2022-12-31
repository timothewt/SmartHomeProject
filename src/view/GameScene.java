/**
 * @file GameScene.java
 * @date 27/12/2022
 * @brief Create the Game Scene
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameScene {

	protected GUIManager GUIManager;

	public GameScene(GUIManager GUIManager) {
		this.GUIManager = GUIManager;
	}

	public JButton createJButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.addActionListener(actionListener);
		return button;
	}

	public JLabel createJLabel(String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		System.out.println(label.getPreferredSize());
		label.setBounds(x, y, label.getPreferredSize().width, label.getPreferredSize().height);
		return label;
	}

	public GUIManager getMain() {
		return GUIManager;
	}

}
