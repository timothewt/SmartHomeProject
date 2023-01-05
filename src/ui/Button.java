/**
 * @file Button.java
 * @date 27/12/2022
 * @brief Instanciate button
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {

	public int x, y, width, height, id;
	private String text;
	private Rectangle bounds;
	private boolean isMouseOver, isMousePressed;

	/**
	 * Normal Buttons without id
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Button(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	/**
	 * Normal Buttons with id
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 */
	public Button(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}

	/**
	 * @brief Buttons without text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 */
	public Button(int x, int y, int width, int height, int id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}

	/**
	 * @brief Set the bounds of the button
	 */
	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		// Body
		drawBody(g);
		// Border
		drawBorder(g);
		// Text
		if (text != null)
			drawText(g);
	}

	/**
	 * @brief Draw the border of the button
	 * @param g
	 */
	private void drawBorder(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		if (isMousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	/**
	 * @brief Draw the content of the button
	 */
	private void drawBody(Graphics g) {
		if (isMouseOver)
			g.setColor(Color.GRAY);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	/**
	 * @brief Write the button text
	 * @param g
	 */
	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
	}

	/**
	 * @brief Reset the button boolean
	 */
	public void resetBooleans() {
		this.isMouseOver = false;
		this.isMousePressed = false;
	}

	// Control
	public void setIsMousePressed(boolean isMousePressed) {
		this.isMousePressed = isMousePressed;
	}

	public void setIsMouseOver(boolean isMouseOver) {
		this.isMouseOver = isMouseOver;
	}

	// Getters and Setters
	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}
}
