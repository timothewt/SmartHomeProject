package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import model.Playing;
import model.Task;

public class TaskBar extends Bar {

	private Playing playing;
	private ArrayList<Button> taskButtons = new ArrayList<>();

	public TaskBar(int x, int y, int width, int height, Playing playing) {

		super(x, y, width, height);
		this.playing = playing;
		initButtons();
	}

	private void initButtons() {

		int w = 80;
		int h = 80;
		int xStart = 10;
		int yStart = 10;
		int xOffset = (int) (w * 1.1f);
		int yOffset = 90;

		int i = 0;
		int line = 0;
		for (Task task : this.playing.getAvailableTasks()) {
			if (xOffset * i < 640 - w) {
				taskButtons.add(new Button(task.getName(), xStart + xOffset * i, yStart + yOffset * line, w, h, i));
				i++;
			} else {
				i = 0;
				line++;
			}
		}
	}

	public void draw(Graphics g) {
		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);

	}

	private void drawButtons(Graphics g) {
		for (Button button : taskButtons) {
			button.draw(g);

			// Mouse Over
			if (button.isMouseOver())
				g.setColor(Color.white);
			else
				g.setColor(Color.black);

			// Border
			g.drawRect(button.x, button.y, button.width, button.height);

			// Mouse Pressed
			if (button.isMousePressed()) {
				g.drawRect(button.x + 1, button.y + 1, button.width - 2, button.height - 2);
				g.drawRect(button.x + 2, button.y + 2, button.width - 4, button.height - 4);
			}
		}
	}

	//Control
	public void mouseMoved(int x, int y) {
		for (Button b : taskButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setIsMouseOver(false);
				return;
			}
		}
		for (Button b : taskButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setIsMouseOver(true);
				return;
			}
		}
	}

	public void mousePressed(int x, int y) {
		for (Button b : taskButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setIsMousePressed(true);
				return;
			}
		}
	}

	public void mouseReleased(int x, int y) {
		for (Button b : taskButtons)
			b.setIsMousePressed(false);
	}

}
