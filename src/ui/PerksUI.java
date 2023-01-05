/**
 * @file DuringDayUI.java
 * @date 05/12/2022
 * Create a UI that manage perks upgrade
 */
package ui;

import static java.lang.Math.max;
import static utils.PlayingStates.TASK;
import static utils.PlayingStates.setPlayingState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import model.Perk;
import model.Person;
import view.InGame;

public class PerksUI extends UIComponent {

	private final InGame inGame;
	private Button nextDayButton;
	private ArrayList<Perk> availablePerks;
	private ArrayList<Perk> currentPerks;
	
	
	/**
	 * @brief Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param inGame
	 */
	public PerksUI(int x, int y, int width, int height, InGame inGame) {
		super(x, y, width, height);
		this.inGame = inGame;
		this.availablePerks = this.inGame.getGame().getAvailablePerks();
		this.currentPerks = this.inGame.getGame().getCurrentPerks();
		initButtons();
	}
	
	/**
	 * @brief init buttons
	 */
	private void initButtons() {
		this.nextDayButton = new Button("Next Day", (x + width / 2) - 60, (y + height) - 50, 120, 30);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		this.nextDayButton.draw(g);
		drawText(g);
		drawPerks(g);
	}
	
	/**
	 * @brief Draw texts components
	 * @param g
	 */
	public void drawText(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Day #" + inGame.getGame().getDayNumber() + ": Choose your upgrades !", this.y + this.width / 2 - 40, 20);
	}
	
	/**
	 * @brief draw Button and Texts links to perks
	 * @param g
	 */
	public void drawPerks(Graphics g) {
		int x = this.x + 20;
		int yStart = this.y  + 70;
		
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Purchasable perks :", x, 50);
		
		this.availablePerks.forEach(perk -> {
			(new Button("BUY", x, yStart + 50*perk.getId(), 100, 30)).draw(g);
			graphics2D.drawString(perk.toString(), x + 120, yStart+20 + 50*perk.getId());	//yStart +20 to line up the text with the button
		});
		
		graphics2D.drawString("Current perks :", x, yStart+20 + 50*this.availablePerks.size());
		this.currentPerks.forEach(perk -> {
			graphics2D.drawString(perk.toString(), x + 120, yStart+20 + 50*perk.getId());	//yStart +20 to line up the text with the button
		});
	}

	
	// Controller
		public void setVisible(boolean isVisible) {

		}

		public void mouseClicked(int x, int y) {
			if (this.nextDayButton.getBounds().contains(x, y))
				setPlayingState(TASK);
		}

		public void mouseMoved(int x, int y) {
			this.nextDayButton.setIsMouseOver(nextDayButton.getBounds().contains(x, y));
		}

		public void mousePressed(int x, int y) {
			this.nextDayButton.setIsMousePressed(nextDayButton.getBounds().contains(x, y));
		}

		public void mouseReleased(int x, int y) {
			this.nextDayButton.resetBooleans();
		}

}
