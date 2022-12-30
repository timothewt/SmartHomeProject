/**
 * @file SceneMethods.java
 * @date 27/12/2022
 * @brief Create an interface for interacting with different types of scenes or views
 */
package view;

import java.awt.Graphics;

public interface SceneMethods {

	public void render(Graphics g);

	public void mouseClicked(int x, int y);

	public void mouseMoved(int x, int y);

	public void mousePressed(int x, int y);

	public void mouseReleased(int x, int y);

	public void mouseDragged(int x, int y);
}
