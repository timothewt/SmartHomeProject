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
import java.lang.reflect.Array;
import java.util.ArrayList;

import model.Person;
import view.InGame;

import static java.lang.Math.min;

public class DuringDayUI extends UIComponent {

	private InGame inGame;
	private Button nextTaskButton;
	private ArrayList<Person> persons;
	private int currentTaskIndex;

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
		this.persons = inGame.getGame().getHouse().getCouple().getPersons();
		this.currentTaskIndex = 0;
		initButtons();
	}

	/**
	 * @brief init buttons
	 */
	private void initButtons() {
		this.nextTaskButton = new Button("Next Task", (x + width) - 140, (y + height) - 50, 120, 30);
	}

	/**
	 * @brief Draw components
	 * @param g
	 */
	public void draw(Graphics g) {
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
		int x = this.x + 20;
		int yStart = this.y + 50;

		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Day #" + inGame.getGame().getDayNumber() + " is going on !", 280, 20);
		int personTextWidth = (this.width - x) / this.persons.size();

		this.persons.forEach(person -> {
			int personTextX = x + (this.persons.indexOf(person) * personTextWidth);
			graphics2D.drawLine(personTextX + personTextWidth, yStart - 10, personTextX + personTextWidth, this.y + this.height - 50);
			graphics2D.drawString(person.getName() + ":", personTextX + 5, yStart);
			for (int i = 0; i < min(this.currentTaskIndex, person.getTasks().size()); i++) {
				graphics2D.drawString(person.getTasks().get(i).getMessage(), personTextX + 15, yStart + 20 * (i + 1));
			}
		});
	}

	// Controller
	public void setVisible(boolean isVisible) {

	}

	public void mouseClicked(int x, int y) {
		if (this.nextTaskButton.getBounds().contains(x, y)) {
			this.currentTaskIndex++;
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
