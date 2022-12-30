/**
 * @file TaskBar.java
 * @date 27/12/2022
 * @brief Create a ui that manage the choice of tasks
 */
package ui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import model.Task;
import view.Play;

public class TaskBar extends Bar {

	private Play play;
	private ArrayList<Button> taskButtons = new ArrayList<>();
	private ArrayList<Button> jeanButtons = new ArrayList<>();
	private ArrayList<Button> marieButtons = new ArrayList<>();
	private boolean isJeanSelected;
	private boolean isMarieSelected;

	private Button bResetTask;
	private CheckboxGroup cbg;
	private Checkbox jean_cb, marie_cb;

	/**
	 * @brief default constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param play
	 */
	public TaskBar(int x, int y, int width, int height, Play play) {

		super(x, y, width, height);
		this.play = play;
		initButtons();
		initCheckbox();
		initPersonTasks(0, jeanButtons);
		initPersonTasks(1, marieButtons);

	}

	/**
	 * @brief init Buttons of a taskBar
	 */
	private void initButtons() {

		int w = 80;
		int h = 80;
		int xStart = 10;
		int yStart = 10;
		int xOffset = (int) (w * 1.1f);
		int yOffset = (int) (w * 1.1f);

		int i = 0;
		int line = 0;
		for (Task task : this.play.getPlaying().getAvailableTasks()) {
			if (xOffset * i > 640 - w) {
				i = 0;
				line++;
			}
			taskButtons
					.add(new Button(task.getName(), xStart + xOffset * i, yStart + yOffset * line, w, h, i + 7 * line));

			i++;
		}

		bResetTask = new Button("Reset", 140, 190, 100, 30);

	}

	/**
	 * @brief init Checkbox of a taskBar
	 */
	private void initCheckbox() {
		cbg = new CheckboxGroup();
		jean_cb = new Checkbox("Jean", cbg, false);
		jean_cb.setBounds(10, 200, 50, 20);
		marie_cb = new Checkbox("Marie", cbg, false);
		marie_cb.setBounds(70, 200, 50, 20);

		jean_cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isJeanSelected = true;
				isMarieSelected = false;
				System.out.println("Debug : Jean selected");
				// initPersonTasks();
				System.out.println("Debug : Jean selected");
			}
		});
		marie_cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isJeanSelected = false;
				isMarieSelected = true;
				// initPersonTasks();
				System.out.println("Debug : Marie selected");
			}
		});
		play.getGame().add(jean_cb);
		play.getGame().add(marie_cb);
	}

	/**
	 * @brief Show checkbox button if the player is on the good scene
	 * @param isVisible
	 */
	public void visibleOrNot(boolean isVisible) {
		if (isVisible) {
			jean_cb.setVisible(true);
			marie_cb.setVisible(true);
		} else {
			jean_cb.setVisible(false);
			marie_cb.setVisible(false);
		}
	}

	/**
	 * @brief draw components
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButton(g, bResetTask);
		drawButtons(g, taskButtons);

		Graphics2D maxTask = (Graphics2D) g;
		maxTask.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isJeanSelected) {
			drawButtons(g, jeanButtons);
			// if no longer slot for tasks available : print a message
			if (play.getPlaying().getHouse().getCouple().getPersons().get(0).getTasks().size() == 9)
				maxTask.drawString("Maximum amount of task reached !", 10, 430);
		} else if (isMarieSelected) {
			drawButtons(g, marieButtons);
			if (play.getPlaying().getHouse().getCouple().getPersons().get(1).getTasks().size() == 9)
				maxTask.drawString("Maximum amount of task reached !", 10, 430);
		}
	}

	/**
	 * @brief draw buttons
	 * @param g
	 */
	private void drawButtons(Graphics g, ArrayList<Button> button) {
		for (Button b : button) {
			b.draw(g);

			// Mouse Over
			if (b.isMouseOver())
				g.setColor(Color.white);
			else
				g.setColor(Color.black);

			// Border
			g.drawRect(b.x, b.y, b.width, b.height);

			// Mouse Pressed
			if (b.isMousePressed()) {
				g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
				g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
			}
		}
	}

	/**
	 * @brief draw a button
	 * @param g
	 */
	private void drawButton(Graphics g, Button b) {
		b.draw(g);

		// Mouse Over
		if (b.isMouseOver())
			g.setColor(Color.white);
		else
			g.setColor(Color.black);

		// Border
		g.drawRect(b.x, b.y, b.width, b.height);

		// Mouse Pressed
		if (b.isMousePressed()) {
			g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
			g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
		}
	}

	/**
	 * @brief Draw the current selected Person (Jean or Marie) Tasks
	 * @param g
	 */
	private void initPersonTasks(int id, ArrayList<Button> buttons) {

		int w = 80;
		int h = 80;
		int xStart = 10;
		int yStart = 230;
		int xOffset = (int) (w * 1.1f);
		int yOffset = (int) (w * 1.1f);
		int line = 0;
		int y = 0;

		// get Stamina of Jean
		int i = play.getPlaying().getHouse().getCouple().getPersons().get(id).getMaxStamina(); // Max stamina because
																								// stamina
		// decrease
		// System.out.println("Debug TaskBar:"+i);
		for (; i > 0; --i) {
			// System.out.println("Debug TaskBar: "i);

			if (xOffset * y < 640 - w) {
				buttons.add(new Button(xStart + xOffset * y, yStart + yOffset * line, w, h, y + 7 * line));
				++y;
			} else {
				y = 0;
				line++;
			}
			// System.out.println("Debug TaskBar:"+line);
		}
		;
	}

	/**
	 * @brief Set all Task of a Person to null
	 * @param id
	 * @param buttons
	 */
	private void resetPersonTasks(int id, ArrayList<Button> buttons) {

		for (int i = 0; i < buttons.size(); ++i) {
			// System.out.println("Debug TaskBar: "i);
			buttons.get(i).setText(null);
		}
		;
	}

	/**
	 * @brief Add the selected task to the Person tasks ArrayList and set the Button
	 *        GUI name
	 * @param id
	 */
	private void addTask(int id) {

		Task taskToAdd = play.getPlaying().findTaskWithId(id);
		for (int i = 0; i < Math.abs(taskToAdd.getStamina()); i++)
			if (isJeanSelected) {
				if (play.getPlaying().getHouse().getCouple().getPersons().get(0).getTasks().size() == 9)
					return;
				play.getPlaying().getHouse().getCouple().getPersons().get(0).addTask(taskToAdd);
				setButtonName(getFirstNonUsedSlot(jeanButtons), taskToAdd.getName(), jeanButtons);
			}

			else if (isMarieSelected) {
				if (play.getPlaying().getHouse().getCouple().getPersons().get(1).getTasks().size() == 9)
					return;
				play.getPlaying().getHouse().getCouple().getPersons().get(1).addTask(taskToAdd);
				setButtonName(getFirstNonUsedSlot(marieButtons), taskToAdd.getName(), marieButtons);
			}

	}

	/**
	 * @param list
	 * @return the first button with a null text
	 */
	private int getFirstNonUsedSlot(ArrayList<Button> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText() == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @brief Set a button Text
	 * @param id      : the id of the button in the ArrayList of Button
	 * @param text    : the text to set
	 * @param buttons : the ArrayList of button
	 */
	private void setButtonName(int id, String text, ArrayList<Button> buttons) {
		buttons.get(id).setText(text);
	}

	/*
	 * @brief Reset tasks of the selected player
	 */
	private void resetTask() {
		if (isJeanSelected) {
			play.getPlaying().getHouse().getCouple().getPersons().get(0).setTasks(new ArrayList<Task>());
			resetPersonTasks(0, jeanButtons);
		} else if (isMarieSelected) {
			play.getPlaying().getHouse().getCouple().getPersons().get(1).setTasks(new ArrayList<Task>());
			resetPersonTasks(1, marieButtons);
		}
	}

	// Control
	public void mouseClicked(int x, int y) {
		if (bResetTask.getBounds().contains(x, y)) {
			resetTask();
			System.out.println("Debug TaskBar : Reset task !");
		}
	}

	public void mouseMoved(int x, int y) {
		bResetTask.setIsMouseOver(false);

		if (bResetTask.getBounds().contains(x, y)) {
			bResetTask.setIsMouseOver(true);
		}
		for (Button b : taskButtons) {
			b.setIsMouseOver(false);
		}
		for (Button b : taskButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setIsMouseOver(true);
				return;
			}
		}
	}

	public void mousePressed(int x, int y) {
		if (bResetTask.getBounds().contains(x, y))
			bResetTask.setIsMousePressed(true);
		for (Button b : taskButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setIsMousePressed(true);
				// System.out.println("Debug TaskBar :"+ b.getId());
				addTask(b.getId());
				return;
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bResetTask.resetBooleans();
		for (Button b : taskButtons)
			b.setIsMousePressed(false);
	}
}