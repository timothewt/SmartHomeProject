/**
 * @file DuringDayUI.java
 * @date 31/12/2022
 * Create a UI that manage the day : Persons are doing their tasks
 */
package ui;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import model.Person;
import view.InGame;

import static utils.PlayingStates.PERK;
import static utils.PlayingStates.setPlayingState;

public class DuringDayUI extends UIComponent {

	private final InGame inGame;
	private Button nextTaskButton;
	private final ArrayList<Person> persons;
	private int currentTaskIndex;
	private boolean areAllTasksDone;

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
		this.currentTaskIndex = -1;
		this.areAllTasksDone = false;
		initButtons();
	}

	/**
	 * @brief init buttons
	 */
	private void initButtons() {
		this.nextTaskButton = new Button("Next Task", (x + width / 2) - 60, (y + height) - 50, 120, 30);
	}

	/**
	 * @brief Draw components
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		this.nextTaskButton.draw(g);
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
		graphics2D.drawString("Day #" + inGame.getGame().getDayNumber() + " is going on !", this.y + this.width / 2 - 40, 20);
		int screenSplitWidth = (this.width - x) / this.persons.size();

		this.persons.forEach(person -> {
			int personTextX = x + (this.persons.indexOf(person) * screenSplitWidth);
			graphics2D.drawLine(personTextX + screenSplitWidth, yStart - 10, personTextX + screenSplitWidth, this.y + this.height - 100);
			graphics2D.drawString(person.getName() + ":", personTextX + 5, yStart);
			for (int i = 0; i < person.getStamina(); i++) {
				(new Button("", personTextX + screenSplitWidth - 15 * (i + 2), yStart - 10, 10, 10)).draw(g);
			}
			for (int i = 0; i <= min(this.currentTaskIndex, person.getTasks().size() - 1); i++) {
				graphics2D.drawString(person.getTasks().get(i).getMessage(), personTextX + 15, yStart + 20 * (i + 1));
			}
		});
	}

	// Controller
	public void setVisible(boolean isVisible) {

	}

	public void mouseClicked(int x, int y) {
		if (this.nextTaskButton.getBounds().contains(x, y)) {
			if (this.areAllTasksDone) {
				setPlayingState(PERK);
			} else {
				int tasksNumber = 0;
				for (Person person : this.persons) {
					tasksNumber = max(person.getTasks().size(), tasksNumber);
				}
				this.currentTaskIndex++;
				this.inGame.getGame().doNthTaskOfAllPersons(this.currentTaskIndex);
				this.areAllTasksDone = tasksNumber == this.currentTaskIndex + 1;
				if (this.areAllTasksDone) {
					this.nextTaskButton.setText("End current day");
					this.nextTaskButton.setWidth(150);
					this.nextTaskButton.setX((this.x + width / 2) - 75);
				}
			}
		}
	}

	public void mouseMoved(int x, int y) {
		this.nextTaskButton.setIsMouseOver(nextTaskButton.getBounds().contains(x, y));

	}

	public void mousePressed(int x, int y) {
		this.nextTaskButton.setIsMousePressed(nextTaskButton.getBounds().contains(x, y));
	}

	public void mouseReleased(int x, int y) {
		this.nextTaskButton.resetBooleans();
	}

}
