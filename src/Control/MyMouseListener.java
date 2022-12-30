/**
 * @file MyMouseListener.java
 * @date 27/12/2022
 * @brief Mouse listener
 */
package Control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private Game game;

	/**
	 * @brief Constructor
	 * @param game
	 */
	public MyMouseListener(Game game) {
		this.game = game;
	}

	//Control
	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mouseDragged(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlay().mouseDragged(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlay().mouseMoved(e.getX(), e.getY());
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
				game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlay().mouseClicked(e.getX(), e.getY());
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
			game.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlay().mousePressed(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlay().mouseReleased(e.getX(), e.getY());
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
