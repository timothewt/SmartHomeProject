package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import view.InGame;

public class DuringDayUI extends UIComponent {

	private InGame inGame;
	
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
	}
	
	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		new Button("Test", 10, 10, 100, 30).draw(g);
		drawText(g);
	}
	
	public void drawText(Graphics g) {
		Graphics2D dayMessage = (Graphics2D)g;
		dayMessage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		dayMessage.drawString("Day #"+ inGame.getGame().getDayNumber() +" is going on !", 300, 20);
	}

	public void setVisible(boolean isVisible) {

	}

	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
