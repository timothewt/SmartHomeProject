package view;

import static view.GameStates.MENU;
import static view.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;



import model.Playing;

public class ActionBar extends Bar {
	
	private Playing playing;
	private Button bMenu, bSave;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		initButtons();
		this.playing = playing;
	}

	private void initButtons() {
		bMenu = new Button("Menu", 2, 642, 100, 30);
		bSave = new Button("Save", 2, 674, 100, 30);
	}

	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);
		Graphics2D money = (Graphics2D)g;
		money.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		money.drawString("Money : "+playing.getHouse().getCouple().getMoney(), 110, 660);
		
		Graphics2D energy = (Graphics2D)g;
		energy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		energy.drawString("Energy : "+playing.getHouse().getEnergy(), 110, 680);
		
		Graphics2D jean = (Graphics2D)g;
		jean.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		jean.drawString(playing.getHouse().getCouple().getPersons().get(0).toString(), 110+90, 660);
		
		Graphics2D marie = (Graphics2D)g;
		marie.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		marie.drawString(playing.getHouse().getCouple().getPersons().get(1).toString(), 110+90, 680);
		
		Graphics2D weather = (Graphics2D)g;
		weather.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		weather.drawString(playing.getWeather().toString(), 2, 720);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);
			
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if(bSave.getBounds().contains(x, y))
			System.out.println("Game saved ! Not done yet, only this message in terminal to test");
	}

	public void mouseMoved(int x, int y) {
		bMenu.setIsMouseOver(false);
		bSave.setIsMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setIsMouseOver(true);
		else if(bSave.getBounds().contains(x, y))
			bSave.setIsMouseOver(true);
	}

	public void mousePressed(int x, int y) {
		bMenu.setIsMousePressed(false);
		bSave.setIsMousePressed(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setIsMousePressed(true);
		else if(bSave.getBounds().contains(x, y))
			bSave.setIsMousePressed(true);
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
	}
}
