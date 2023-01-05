/**
 * @file TaskBar.java
 * @date 27/12/2022
 * UI used by the user to pick the daily tasks of the persons
 */
package ui;

import java.awt.*;
import java.util.ArrayList;
import model.Person;
import model.Task;
import view.GameGUI;
import static java.lang.Math.abs;
import static utils.PlayingStates.*;

public class TasksUI extends UIComponent {

	private final GameGUI gameGUI; // gameGUI containing the game model
	private int selectedPersonId; // id of the selected person by the user
	private ArrayList<Button> taskButtons; // button used to add a task to a person
	private ArrayList<Button> utilityButtons; // buttons managing the game like reset tasks or start the day
	private ArrayList<Checkbox> personsCheckboxes; // checkboxes used by the user to pick a person in order to add tasks to him
	private int taskListY; // vertical position of the tasks list of the current person selected

	/**
	 * Class constructor specifying the size and position of the window, and the gameGUI
	 * @param x: horizontal position of the UI
	 * @param y: vertical position of the UI
	 * @param width: width of the UI
	 * @param height: height of the UI
	 * @param gameGUI: class that contains the game model
	 */
	public TasksUI(int x, int y, int width, int height, GameGUI gameGUI) {
		super(x, y, width, height);
		this.gameGUI = gameGUI;
		initControls();
	}

	/**
	 * Initializes the controls of tje UI, the tasks, tasks reset and start day buttons, and the checkboxes to select a person
	 */
	private void initControls() {
		int buttonWidth = 125;
		int buttonHeight = 30;
		int xStart = 10;
		int yStart = 10;
		int xOffsetButtonsOrigin = buttonWidth + 10;
		int yOffsetButtonsOrigin = buttonHeight + 10;

		this.taskButtons = new ArrayList<>();

		int i = 0;
		int currenTaskId = 0;
		int line = 0;
		for (Task task : this.gameGUI.getGame().getAvailableTasks()) {
			if (xOffsetButtonsOrigin * i > (this.y + this.width) - buttonWidth) {
				i = 0;
				line++;
			}
			taskButtons.add(new Button(task.getName() + " (" + task.getStamina() + ")", xStart + xOffsetButtonsOrigin * i, yStart + yOffsetButtonsOrigin * line, buttonWidth, buttonHeight, currenTaskId));
			i++;
			currenTaskId++;
		}
		int tasksLinesNumber = line;

		this.utilityButtons = new ArrayList<>();
		this.utilityButtons.add(new Button("Reset",(this.x + this.width) - 120, yStart + yOffsetButtonsOrigin * (tasksLinesNumber + 1), 100, 30, 0));
		this.utilityButtons.add(new Button("Start Day", (this.x + this.width) - 120, (this.y + this.height) - 50, 100, 30, 1));

		CheckboxGroup cbg = new CheckboxGroup();
		this.personsCheckboxes = new ArrayList<>();

		ArrayList<Person> persons = this.gameGUI.getGame().getHouse().getCouple().getPersons();

		persons.forEach(person -> {
			int personId = person.getId();
			Checkbox personCheckbox = new Checkbox(person.getName(), cbg, false);
			personCheckbox.setBounds(x + 10 + 80 * persons.indexOf(person), yStart + yOffsetButtonsOrigin * (tasksLinesNumber + 1), 50, 20);
			personCheckbox.addItemListener(e -> this.selectedPersonId = personId);
			this.personsCheckboxes.add(personCheckbox);
			gameGUI.getMain().add(personCheckbox);
		});
		this.selectedPersonId = 0;
		this.personsCheckboxes.get(0).setState(true);

		this.taskListY = this.y + yStart + yOffsetButtonsOrigin * (tasksLinesNumber + 2);
	}

	/**
	 * Make the scene controls visible or not
	 * @param isVisible: tells if the UI is visible
	 */
	public void setVisible(boolean isVisible) {
		this.personsCheckboxes.forEach(checkbox -> checkbox.setVisible(isVisible));
	}

	/**
	 * Draws components the UI on the current scene
	 * @param g: graphics component of the app
	 */
	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		this.utilityButtons.forEach(button -> button.draw(g));
		taskButtons.forEach(button -> button.draw(g));

		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Person selectedPerson = this.gameGUI.getGame().getHouse().getCouple().getPersonsFromId(this.selectedPersonId);
		int remainingStamina = selectedPerson.getMaxStamina() - selectedPerson.getResultingStaminaForCurrentTasks() * -1;
		graphics2D.drawString("Remaining stamina:", this.x + 10, this.taskListY);
		for (int i = 0; i < remainingStamina; i++) {
			(new Button("",this.x + 130 + (i * 20), this.taskListY - 10, 15, 10, 99 + i)).draw(g);
		}
		ArrayList<Task> tasksToDisplay = selectedPerson.getTasks();

		if (tasksToDisplay != null) {
			for (int i = 0; i < tasksToDisplay.size(); i++) {
				(new Button(tasksToDisplay.get(i).getName(), this.x + this.width / 2 - 58, this.taskListY + 10 + 40 * i, 120, 30)).draw(g);
				int taskStamina = tasksToDisplay.get(i).getStamina();
				int xStart;
				String sign;
				if (taskStamina > 0) {
					xStart = this.x + this.width / 2 + 58 + 10;
					sign = "+";
				} else {
					xStart = this.x + this.width / 2 - 58 + taskStamina * 40;
					sign = "-";
				}
				for (int j = 0; j < abs(taskStamina); j++) {
					(new Button(sign, xStart + 40 * j, this.taskListY + 10 + 40 * i, 30, 30)).draw(g);
				}
			}
		}
	}


	/**
	 * Add the selected task to the Person tasks
	 * @param id: id of the task to add
	 */
	private void addTaskToSelectedPerson(int id) {
		Task selectedTask = gameGUI.getGame().findTaskFromId(id);
		Person selectedPerson = gameGUI.getGame().getHouse().getCouple().getPersonsFromId(this.selectedPersonId);
		if ((selectedTask.getStamina() + selectedPerson.getResultingStaminaForCurrentTasks()) * -1 <= selectedPerson.getMaxStamina()) { // * -1 because stamina decreases for task that needs it
			selectedPerson.addTask(selectedTask);
		}
	}

	/**
	 * Reset tasks of the selected player
	 */
	private void resetTasks() {
		gameGUI.getGame().getHouse().getCouple().getPersonsFromId(this.selectedPersonId).setTasks(new ArrayList<>());
	}

	/**
	 * Called when the user clicks anywhere on the screen. Used to know if the user clicked on the reset tasks or start day buttons
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
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

		this.taskButtons.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));
	}

	public void mouseReleased(int x, int y) {
		this.utilityButtons.forEach(Button::resetBooleans);
		this.taskButtons.forEach(Button::resetBooleans);
	}
}