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

@SuppressWarnings("serial")
public class Screen extends JPanel {

	private final GUIManager GUIManager;

	private Dimension size;
	private MyMouseListener myMouseListener;

	/**
	 * @brief Constructor
	 * @param GUIManager
	 */
	public Screen(GUIManager GUIManager) {
		this.GUIManager = GUIManager;

		setPanelSize();
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(GUIManager);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);

		requestFocus();
	}

	/**
	 * @brief Set the size of the window
	 */
	private void setPanelSize() {
		size = new Dimension(700, 720);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	/**
	 * @brief Graphic render
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GUIManager.getRender().render(g);
	}

	public Dimension getSize() {
		return this.size;
	}
}
