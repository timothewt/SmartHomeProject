/**
 * @file TaskBar.java
 * @date 27/12/2022
 * Create a UI that manage the choice of tasks
 */
package ui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import model.Person;
import model.Task;
import view.InGame;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static utils.PlayingStates.DAY;
import static utils.PlayingStates.setPlayingState;

public class TasksUI extends UIComponent {

	private final InGame inGame;
	private ArrayList<Button> taskButtons;
	private int selectedPersonId;

	private ArrayList<Button> utilityButtons;
	private ArrayList<Checkbox> personsCheckboxes;
	private int taskListY;

	/**
	 * default constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param inGame
	 */
	public TasksUI(int x, int y, int width, int height, InGame inGame) {
		super(x, y, width, height);
		this.inGame = inGame;
		initControls();
	}

	/**
	 * init Buttons of a taskBar
	 */
	private void initControls() {
		int w = 115;
		int h = 30;
		int xStart = 10;
		int yStart = 10;
		int xOffset = w + 10;
		int yOffset = h + 10;

		this.taskButtons = new ArrayList<>();

		int i = 0;
		int currenTaskId = 0;
		int line = 0;
		for (Task task : this.inGame.getGame().getAvailableTasks()) {
			if (xOffset * i > 640 - w) {
				i = 0;
				line++;
			}
			taskButtons.add(new Button(task.getName(), xStart + xOffset * i, yStart + yOffset * line, w, h, currenTaskId));
			i++;
			currenTaskId++;
		}
		int tasksLinesNumber = line;

		this.utilityButtons = new ArrayList<>();
		this.utilityButtons.add(new Button("Reset",640 - 120, yStart + yOffset * (tasksLinesNumber + 1), 100, 30, 0));
		this.utilityButtons.add(new Button("Start Day", 640 - 120, 550, 100, 30, 1));

		CheckboxGroup cbg = new CheckboxGroup();
		this.personsCheckboxes = new ArrayList<>();

		ArrayList<Person> persons = this.inGame.getGame().getHouse().getCouple().getPersons();

		persons.forEach(person -> {
			int personId = person.getId();
			Checkbox personCheckbox = new Checkbox(person.getName(), cbg, false);
			personCheckbox.setBounds(10 + 60 * personId, yStart + yOffset * (tasksLinesNumber + 1), 50, 20);
			personCheckbox.addItemListener(e -> this.selectedPersonId = personId);
			this.personsCheckboxes.add(personCheckbox);
			inGame.getMain().add(personCheckbox);
		});
		this.selectedPersonId = 0;
		this.personsCheckboxes.get(0).setState(true);

		this.taskListY = yStart + yOffset * (tasksLinesNumber + 2);
	}

	/**
	 * Show checkbox button if the player is on the good scene
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		this.personsCheckboxes.forEach(checkbox -> checkbox.setVisible(isVisible));
	}

	/**
	 * draw components
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		this.utilityButtons.forEach(button -> button.draw(g));
		taskButtons.forEach(button -> button.draw(g));

		Graphics2D maxTask = (Graphics2D) g;
		maxTask.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Person selectedPerson = this.inGame.getGame().getHouse().getCouple().getPersonsFromId(this.selectedPersonId);
		ArrayList<Task> tasksToDisplay = selectedPerson.getTasks();

		if (tasksToDisplay != null) {
			for (int i = 0; i < tasksToDisplay.size(); i++) {
				(new Button(tasksToDisplay.get(i).getName(), 263, this.taskListY + 40 * i, 115, 30)).draw(g);
				int taskStamina = tasksToDisplay.get(i).getStamina();
				int xStart;
				String sign;
				if (taskStamina > 0) {
					xStart = 388;
					sign = "+";
				} else {
					xStart = 262 + taskStamina * 40;
					sign = "-";
				}
				for (int j = 0; j < abs(taskStamina); j++) {
					(new Button(sign, xStart + 40 * j, this.taskListY + 40 * i, 30, 30)).draw(g);
				}
			}
			if (selectedPerson.getMaxStamina() == -1 * selectedPerson.getResultingStaminaForCurrentTasks()) {
				maxTask.drawString("Maximum stamina reached !", 450, 600);
			}
		}
	}


	/**
	 * Add the selected task to the Person tasks
	 * @param id
	 */
	private void addTaskToSelectedPerson(int id) {
		Task selectedTask = inGame.getGame().findTaskFromId(id);
		Person selectedPerson = inGame.getGame().getHouse().getCouple().getPersonsFromId(this.selectedPersonId);
		System.out.println("Max stamina: " + selectedPerson.getMaxStamina());
		System.out.println("Task stamina: " + selectedTask.getStamina());
		System.out.println("Current stamina result: " + selectedPerson.getResultingStaminaForCurrentTasks());
		System.out.println("Comparison: " + (selectedTask.getStamina() + selectedPerson.getResultingStaminaForCurrentTasks()) * -1);
		if ((selectedTask.getStamina() + selectedPerson.getResultingStaminaForCurrentTasks()) * -1 <= selectedPerson.getMaxStamina()) { // * -1 because stamina decreases for task that needs it
			selectedPerson.addTask(selectedTask);
		}
	}

	/**
	 * Reset tasks of the selected player
	 */
	private void resetTasks() {
		inGame.getGame().getHouse().getCouple().getPersonsFromId(this.selectedPersonId).setTasks(new ArrayList<>());
	}

	// Control
	public void mouseClicked(int x, int y) {
		this.utilityButtons.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				switch (button.getId()) {
					case 0 -> resetTasks();
					case 1 -> setPlayingState(DAY);
				}
			}
		});

		this.taskButtons.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				addTaskToSelectedPerson(button.getId());
			}
		});
	}

	public void mouseMoved(int x, int y) {
		this.utilityButtons.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
		this.taskButtons.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
	}

	public void mousePressed(int x, int y) {
		this.utilityButtons.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));

		this.taskButtons.forEach(button -> {
			button.setIsMousePressed(button.getBounds().contains(x, y));
		});
	}

	public void mouseReleased(int x, int y) {
		this.utilityButtons.forEach(Button::resetBooleans);
		this.taskButtons.forEach(Button::resetBooleans);
	}
}