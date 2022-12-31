/**
 * @file GameScreen.java
 * @date 27/12/2022
 * @brief Set parameter for the Game window
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.MyMouseListener;
import main.Main;

public class Screen extends JPanel {
	private Main main;

	private Dimension size;
	private MyMouseListener myMouseListener;

	/**
	 * @brief Constructor
	 * @param main
	 */
	public Screen(Main main) {
		this.main = main;

		setPanelSize();
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(main);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);

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
		main.getRender().render(g);
	}
}
