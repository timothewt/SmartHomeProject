/**
 * @file Game.java
 * @date 27/12/2022
 * @brief Main class of the program
 */
package main;

import javax.swing.JFrame;

import view.Menu;
import view.Play;

public class Game extends JFrame implements Runnable {
	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 90.0;
	private final double UPS_SET = 48.0;

	// Classes
	private Render render;
	private Menu menu;
	private Play play;

	/**
	 * @brief Constructor
	 */
	public Game() {

		initClasses();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(gameScreen);
		pack();
		setVisible(true);

	}

	private void initClasses() {

		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		play = new Play(this);
	}

	/**
	 * @brief Start the thread
	 */
	private void start() {
		gameThread = new Thread(this) {
		};
		gameThread.start();
	}

	private void updateGame() {
		switch (GameStates.gameState) {
		case MENU:
			play.getTaskBar().visibleOrNot(false);
			break;
		case PLAYING:
			play.getTaskBar().visibleOrNot(true);
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
			play.getTaskBar().visibleOrNot(false);
			break;

		}
	}

	/**
	 * @brief Launch the game
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
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

	public Play getPlay() {
		return play;
	}
}
