/**
 * @file GameScreen.java
 * @date 27/12/2022
 * @brief Set parameter for the Game window
 */
package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Control.KeyboardListener;
import Control.MyMouseListener;

public class GameScreen extends JPanel {
	private Game game;

	private Dimension size;
	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;

	/**
	 * @brief Constructor
	 * @param game
	 */
	public GameScreen(Game game) {
		this.game = game;

		setPanelSize();
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener();

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	/**
	 * @brief Set the size of the window
	 */
	private void setPanelSize() {
		size = new Dimension(640, 730);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	/**
	 * @brief Graphic render
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.getRender().render(g);
		;
	}
}
