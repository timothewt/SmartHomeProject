/**
 * @file MyMouseListener.java
 * @date 27/12/2022
 * @brief Mouse listener
 */
package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import view.GUIManager;
import utils.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private final GUIManager GUIManager;

	/**
	 * @brief Constructor
	 * @param GUIManager
	 */
	public MyMouseListener(GUIManager GUIManager) {
		this.GUIManager = GUIManager;
	}

	//Control
	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU -> GUIManager.getMenu().mouseDragged(e.getX(), e.getY());
			case PLAYING -> GUIManager.getPlay().mouseDragged(e.getX(), e.getY());
			default -> {
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU -> GUIManager.getMenu().mouseMoved(e.getX(), e.getY());
			case PLAYING -> GUIManager.getPlay().mouseMoved(e.getX(), e.getY());
			default -> {
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			switch (GameStates.gameState) {
				case MENU -> GUIManager.getMenu().mouseClicked(e.getX(), e.getY());
				case PLAYING -> GUIManager.getPlay().mouseClicked(e.getX(), e.getY());
				default -> {
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU -> GUIManager.getMenu().mousePressed(e.getX(), e.getY());
			case PLAYING -> GUIManager.getPlay().mousePressed(e.getX(), e.getY());
			default -> {
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU -> GUIManager.getMenu().mouseReleased(e.getX(), e.getY());
			case PLAYING -> GUIManager.getPlay().mouseReleased(e.getX(), e.getY());
			default -> {
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
