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
import java.util.ArrayList;

import model.Task;
import view.InGame;
import static utils.PlayingStates.DAY;
import static utils.PlayingStates.setPlayingState;

public class TasksUI extends UIComponent {

	private InGame inGame;
	private ArrayList<Button> taskButtons = new ArrayList<>();
	private ArrayList<Button> jeanButtons = new ArrayList<>();
	private ArrayList<Button> marieButtons = new ArrayList<>();
	private boolean isJeanSelected;
	private boolean isMarieSelected;

	private Button btnPlayDay;
	private Button btnResetTask;
	private CheckboxGroup cbg;
	private Checkbox jean_cb, marie_cb;

	/**
	 * @brief default constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param inGame
	 */
	public TasksUI(int x, int y, int width, int height, InGame inGame) {

		super(x, y, width, height);
		this.inGame = inGame;
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
		for (Task task : this.inGame.getGame().getAvailableTasks()) {
			if (xOffset * i > 640 - w) {
				i = 0;
				line++;
			}
			taskButtons
					.add(new Button(task.getName(), xStart + xOffset * i, yStart + yOffset * line, w, h, i + 7 * line));

			i++;
		}

		btnResetTask = new Button("Reset", 140, 190, 100, 30);
		btnPlayDay = new Button("Start Day", 500, 550, 100, 30);

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

		jean_cb.addItemListener(e -> {
			isJeanSelected = true;
			isMarieSelected = false;
		});
		marie_cb.addItemListener(e -> {
			isJeanSelected = false;
			isMarieSelected = true;
		});
		inGame.getMain().add(jean_cb);
		inGame.getMain().add(marie_cb);
	}

	/**
	 * @brief Show checkbox button if the player is on the good scene
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
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
		drawButton(g, btnResetTask);
		drawButton(g, btnPlayDay);
		drawButtons(g, taskButtons);

		Graphics2D maxTask = (Graphics2D) g;
		maxTask.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int xMaxTaskMsg = 10;
		int yMaxTaskMsg = 430;
		
		if (isJeanSelected) {
			drawButtons(g, jeanButtons);
			// if no longer slot for tasks available : print a message
			if (inGame.getGame().getHouse().getCouple().getPersons().get(0).getTasks().size() == inGame.getGame().getHouse().getCouple().getPersons().get(0).getMaxStamina())
				maxTask.drawString("Maximum amount of task reached !", xMaxTaskMsg, yMaxTaskMsg);
		} else if (isMarieSelected) {
			drawButtons(g, marieButtons);
			if (inGame.getGame().getHouse().getCouple().getPersons().get(1).getTasks().size() == inGame.getGame().getHouse().getCouple().getPersons().get(1).getMaxStamina())
				maxTask.drawString("Maximum amount of task reached !", xMaxTaskMsg, yMaxTaskMsg);
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
		int i = inGame.getGame().getHouse().getCouple().getPersons().get(id).getMaxStamina(); // Max stamina because
																								// stamina decrease
		// System.out.println("Debug TaskBar:"+i);
		for (; i >= 0; --i) {
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

		Task taskToAdd = inGame.getGame().findTaskWithId(id);
		for (int i = 0; i < Math.abs(taskToAdd.getStamina()); i++)
			if (isJeanSelected) {
				if (inGame.getGame().getHouse().getCouple().getPersons().get(0).getTasks().size() == inGame.getGame().getHouse().getCouple().getPersons().get(0).getMaxStamina())
					return;
				inGame.getGame().getHouse().getCouple().getPersons().get(0).addTask(taskToAdd);
				setButtonName(getFirstNonUsedSlot(jeanButtons), taskToAdd.getName(), jeanButtons);
			}

			else if (isMarieSelected) {
				if (inGame.getGame().getHouse().getCouple().getPersons().get(1).getTasks().size() == inGame.getGame().getHouse().getCouple().getPersons().get(1).getMaxStamina())
					return;
				inGame.getGame().getHouse().getCouple().getPersons().get(1).addTask(taskToAdd);
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

	/**
	 * @brief Reset tasks of the selected player
	 */
	private void resetTask() {
		if (isJeanSelected) {
			inGame.getGame().getHouse().getCouple().getPersons().get(0).setTasks(new ArrayList<Task>());
			resetPersonTasks(0, jeanButtons);
		} else if (isMarieSelected) {
			inGame.getGame().getHouse().getCouple().getPersons().get(1).setTasks(new ArrayList<Task>());
			resetPersonTasks(1, marieButtons);
		}
	}

	// Control
	public void mouseClicked(int x, int y) {
		if (btnResetTask.getBounds().contains(x, y)) {
			resetTask();
			System.out.println("Debug TaskUI : Reset task !");
		}
		if (btnPlayDay.getBounds().contains(x, y)) {
			setPlayingState(DAY);
			System.out.println("Debug TAskUI : Go to Day State !");
		}
	}

	public void mouseMoved(int x, int y) {
		btnResetTask.setIsMouseOver(false);
		btnPlayDay.setIsMouseOver(false);

		if (btnResetTask.getBounds().contains(x, y)) {
			btnResetTask.setIsMouseOver(true);
			return;
		}
		else if (btnPlayDay.getBounds().contains(x, y)) {
			btnPlayDay.setIsMouseOver(true);
			return;
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
		if (btnResetTask.getBounds().contains(x, y)) {
			btnResetTask.setIsMousePressed(true);
			return;
		}
		
		if (btnPlayDay.getBounds().contains(x, y)) {
			btnPlayDay.setIsMousePressed(true);
			return;
		}
		
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
		btnResetTask.resetBooleans();
		btnPlayDay.resetBooleans();
		for (Button b : taskButtons)
			b.setIsMousePressed(false);
	}
}