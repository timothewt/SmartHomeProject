/**
 * @file DuringDayUI.java
 * @date 31/12/2022
 * Create a UI that manage the day : Persons are doing their tasks
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import view.InGame;

public class DuringDayUI extends UIComponent {

	private InGame inGame;
	private Button nextTaskButton;
	int jean;
	int marie;

	/**
	 * @brief Default constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param inGame
	 */
	public DuringDayUI(int x, int y, int width, int height, InGame inGame) {
		super(x, y, width, height);
		this.inGame = inGame;
		this.jean = 0;
		this.marie = 0;
		initButtons();
	}

	/**
	 * @brief init buttons
	 */
	private void initButtons() {
		this.nextTaskButton = new Button("Next", 640 - 120, 550, 100, 30);
	}

	/**
	 * @brief Draw components
	 * @param g
	 */
	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		nextTaskButton.draw(g);
		drawText(g);
	}

	/**
	 * @brief Draw texts components
	 * @param g
	 */
	public void drawText(Graphics g) {
		int x = 80;
		int yStart = 100;
		int j = 0;
		int m = 0;

		Graphics2D message = (Graphics2D) g;
		message.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		message.drawString("Day #" + inGame.getGame().getDayNumber() + " is going on !", 300, 20);
		message.drawString("Jean :", x - 30, yStart - 20);
		message.drawString("Marie :", x + 300 - 30, yStart - 20);

		if (this.inGame.getGame().getHouse().getCouple().getPersons().get(0).getTasks().size() != 0) {
			while (j < jean) {
				message.drawString(
						this.inGame.getGame().getHouse().getCouple().getPersons().get(0).getTasks().get(j).getMessage(),
						x, yStart + j * 20);
				j++;
			}
		}

		if (this.inGame.getGame().getHouse().getCouple().getPersons().get(1).getTasks().size() != 0) {
			while (m < marie) {
				message.drawString(
						this.inGame.getGame().getHouse().getCouple().getPersons().get(1).getTasks().get(m).getMessage(),
						x + 300, yStart + m * 20);
				m++;
			}
		}
	}

	// Controller
	public void setVisible(boolean isVisible) {

	}

	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		if (this.nextTaskButton.getBounds().contains(x, y)) {
			if (this.inGame.getGame().getHouse().getCouple().getPersons().get(0).getTasks().size() - 1 >= jean) {
				jean++;
				System.out.println("Add one to jean");
			}

			if (this.inGame.getGame().getHouse().getCouple().getPersons().get(1).getTasks().size() - 1 >= marie)
				marie++;
		}
	}

	public void mouseMoved(int x, int y) {
		this.nextTaskButton.setIsMouseOver(nextTaskButton.getBounds().contains(x, y));

	}

	public void mousePressed(int x, int y) {
		this.nextTaskButton.setIsMousePressed(nextTaskButton.getBounds().contains(x, y));
	}

	public void mouseReleased(int x, int y) {
		nextTaskButton.resetBooleans();
	}

}
