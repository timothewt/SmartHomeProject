/**
 * @file Game.java
 * @date 27/12/2022
 * @brief Main class of the program
 */
package view;

import utils.GameStates;
import utils.PlayingStates;

import javax.swing.JFrame;

public class GUIManager extends JFrame implements Runnable {
	private Screen screen;
	private Thread gameThread;

	private final double FPS_SET = 90.0;
	private final double UPS_SET = 48.0;

	// Classes
	private Render render;
	private Menu menu;
	private InGame inGame;

	/**
	 * @brief Constructor
	 */
	public GUIManager() {

		initClasses();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(screen);
		pack();
		setVisible(true);

	}

	private void initClasses() {

		render = new Render(this);
		screen = new Screen(this);
		menu = new Menu(this);
		inGame = new InGame(this);
	}

	/**
	 * @brief Start the thread
	 */
	public void start() {
		gameThread = new Thread(this) {
		};
		gameThread.start();
	}

	private void updateGame() {
		switch (GameStates.gameState) {
		case MENU:
			inGame.getTaskBar().setVisible(false);
			break;
		case PLAYING:
			inGame.getTaskBar().setVisible(true);
			switch (PlayingStates.playingState) {
			case ONE:
				break;
			case TWO:
				break;
			case THREE:
				break;
			}
			break;
		default:
			inGame.getTaskBar().setVisible(false);
			break;

		}
	}

	/**
	 * @brief The game loop
	 */
	@Override
	public void run() {

		long lastFrame = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		long lastUpdate = System.nanoTime();

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();
			// Updates
			if (now - lastUpdate >= timePerUpdate) {
				lastUpdate = now;
				updates++;
				updateGame();
			}

			// Render
			if (now - lastFrame >= timePerFrame) {
				lastFrame = now;
				repaint();
				frames++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS : " + frames + "| UPS : " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}

	// Getters and setters
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public InGame getPlay() {
		return inGame;
	}

	public Screen getScreen() {
		return this.screen;
	}
}
