/**
 * @file MyMouseListener.java
 * @date 27/12/2022
 * @brief Mouse listener
 */
package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Main;
import utils.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private Main main;

	/**
	 * @brief Constructor
	 * @param main
	 */
	public MyMouseListener(Main main) {
		this.main = main;
	}

	//Control
	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			main.getMenu().mouseDragged(e.getX(), e.getY());
			break;
		case PLAYING:
			main.getPlay().mouseDragged(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			main.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			main.getPlay().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			switch (GameStates.gameState) {
			case MENU:
				main.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
				main.getPlay().mouseClicked(e.getX(), e.getY());
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			main.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PLAYING:
			main.getPlay().mousePressed(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			main.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			main.getPlay().mouseReleased(e.getX(), e.getY());
			break;
		default:
			break;
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
