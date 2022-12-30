/**
 * @file ActionBar.java
 * @date 27/12/2022
 * @brief Base class for ui
 */
package ui;

public class Bar {
	protected int x, y, width, height;

	/**
	 * @brief Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}
}