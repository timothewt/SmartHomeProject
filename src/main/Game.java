package main;
import model.Playing;
import view.Menu;
import view.Render;
import view.GameScreen;
import view.GameStates;
import javax.swing.JFrame;

import java.util.ArrayList;


public class Game extends JFrame implements Runnable
{
	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 90.0;
	private final double UPS_SET = 48.0;
	
	//Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	
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
		playing = new Playing(this);
	}

	private void start()
	{
		gameThread = new Thread(this) {};
		gameThread.start();
	}
	
	private void updateGame()
	{
		switch(GameStates.gameState)
		{
		case MENU:
			break;
		case PLAYING:
			//playing.update();
			break;
		default:
			break;
		
		}
	}

	public static void main(String[] args)
	{
		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
	}
	
	@Override
	public void run() 
	{
		
		long lastFrame = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		long lastUpdate = System.nanoTime();
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		int frames = 0;
		int updates = 0;
		
		long now;
		
		while(true)
		{
			now = System.nanoTime();
			//Updates
			if(now - lastUpdate >= timePerUpdate)
			{
				lastUpdate = now;
				updates ++;
				updateGame();
			}
			
			//Render
			if(now - lastFrame >= timePerFrame)
			{
				lastFrame = now;
				repaint();
				frames++;
			}
			
			if (System.currentTimeMillis() - lastTimeCheck >= 1000)
			{
				System.out.println("FPS : "+frames + "| UPS : " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}
	
	//Getters and setters
	public Render getRender(){
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

}
