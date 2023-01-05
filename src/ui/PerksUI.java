/**
 * @file PerksUI.java
 * @date 05/12/2022
 * UI used to buy perks for the house
 */
package ui;

import static utils.PlayingStates.TASK;
import static utils.PlayingStates.setPlayingState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import model.Perk;
import view.GameGUI;

public class PerksUI extends UIComponent {

	private final GameGUI gameGUI; // gameGUI containing the game model
	private Button nextDayButton; // button used to go to the next day
	private ArrayList<Perk> availablePerks; // list of all the available perks to buy
	private ArrayList<Perk> currentPerks; // list of all the perks already ought by the user
	private ArrayList<Button> availablePerksButton;

	/**
	 * Class constructor specifying the size and position of the window, and the
	 * gameGUI
	 * 
	 * @param x:       horizontal position of the UI
	 * @param y:       vertical position of the UI
	 * @param width:   width of the UI
	 * @param height:  height of the UI
	 * @param gameGUI: class that contains the game model
	 */
	public PerksUI(int x, int y, int width, int height, GameGUI gameGUI) {
		super(x, y, width, height);
		this.gameGUI = gameGUI;
		this.availablePerks = this.gameGUI.getGame().getAvailablePerks();
		this.currentPerks = this.gameGUI.getGame().getCurrentPerks();
		initButtons();
	}

	/**
	 * Initializes the buttons of the UI
	 */
	private void initButtons() {
		this.nextDayButton = new Button("Next Day", (x + width / 2) - 60, (y + height) - 50, 120, 30);
		initPerksButton();
	}

	private void initPerksButton() {
		int x = this.x + 20;
		int yStart = this.y + 70;

		availablePerksButton = new ArrayList<>();
		this.availablePerks.forEach(perk -> {
			availablePerksButton
					.add(new Button("BUY", x, yStart + 50 * availablePerks.indexOf(perk), 100, 30, perk.getId()));
		});
	}

	/**
	 * Draws components the UI on the current scene
	 * 
	 * @param g: graphics component of the app
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		this.nextDayButton.draw(g);
		drawText(g);
		drawPerks(g);
	}

	/**
	 * Draws the text of the current day
	 * 
	 * @param g: graphics component of the app
	 */
	public void drawText(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Day #" + gameGUI.getGame().getDayNumber() + ": Choose your upgrades !",
				this.y + this.width / 2 - 40, 20);
	}

	/**
	 * Draws the text of all the perks and the buttons
	 * 
	 * @param g: graphics component of the app
	 */
	public void drawPerks(Graphics g) {
		int x = this.x + 20;
		int yStart = this.y + 70;

		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Purchasable perks :", x, 50);

		this.availablePerks.forEach(perk -> {
			availablePerksButton.get(availablePerks.indexOf(perk)).draw(g);
			graphics2D.drawString(perk.toString(), x + 120, yStart + 20 + 50 * availablePerks.indexOf(perk)); // yStart
																												// +20
																												// to
																												// line
																												// up
																												// the
																												// text
																												// with
																												// the
																												// button
		});

		graphics2D.drawString("Current perks :", x, yStart + 20 + 50 * this.availablePerks.size());
		this.currentPerks.forEach(perk -> {
			graphics2D.drawString(perk.toString(), x + 120,
					yStart + 20 + 50 * this.availablePerks.size() + 50 * currentPerks.indexOf(perk)); // yStart +20 to
																										// line up the
																										// text with the
																										// button
		});
	}

	public void onNewDay() {

	}

	public boolean canAfford(int id) {
		if (availablePerks.get(id).getInstallationCost() <= this.gameGUI.getGame().getHouse().getCouple().getMoney()) {
			return true;
		}
		return false;
	}

	/**
	 * Make the scene controls visible or not
	 * 
	 * @param isVisible: tells if the UI is visible
	 */
	public void setVisible(boolean isVisible) {
	}

	/**
	 * Called when the user clicks anywhere on the screen. Used to know if he
	 * clicked on the nextDay button
	 * 
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
	public void mouseClicked(int x, int y) {
		if (this.nextDayButton.getBounds().contains(x, y)) {
			this.gameGUI.getGame().onNewDay();
			this.gameGUI.onNewDay();
			setPlayingState(TASK);
		}

		this.availablePerksButton.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				if (canAfford(availablePerksButton.indexOf(button))) {
					this.gameGUI.getGame().getHouse().getCouple()
							.setMoney(this.gameGUI.getGame().getHouse().getCouple().getMoney() - this.availablePerks
									.get(availablePerksButton.indexOf(button)).getInstallationCost());
					currentPerks.add(this.availablePerks.get(availablePerksButton.indexOf(button)));
					availablePerks.remove(availablePerksButton.indexOf(button));
					initPerksButton();
				}
			}
		});
	}

	public void mouseMoved(int x, int y) {
		this.nextDayButton.setIsMouseOver(nextDayButton.getBounds().contains(x, y));
		this.availablePerksButton.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
	}

	public void mousePressed(int x, int y) {
		this.nextDayButton.setIsMousePressed(nextDayButton.getBounds().contains(x, y));
		this.availablePerksButton.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));
	}

	public void mouseReleased(int x, int y) {
		this.nextDayButton.resetBooleans();
		this.availablePerksButton.forEach(button -> button.resetBooleans());
	}

}
