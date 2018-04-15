package game_engine;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gfx.SpriteCrop;
import states.GameState;
import states.MenuState;
import states.State;

public class GameLoop
{
	private Window window;
	private static final int width = 1200, height = 500;
	private float distance;
	
	private BufferStrategy bufferStrat;
	private Graphics draw;
	public BufferedImage test;
	
	//States
	private State gameState;
	private State menuState;
	
	public GameLoop() //constructor
	{	
		setWindow(new Window(width, height));
		SpriteCrop.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		this.getWindow().getFrame().addKeyListener((KeyListener) menuState);
		State.setState(gameState);
	}

	private void update()
	{
		if(gameState.getMap().getPlayer().isDeathF())
		{
			distance = gameState.getMap().getPlayer().getDistance();
			gameState = new GameState(this);
			State.setState(menuState);
		}

		if(menuState.isStart())
		{
			State.setState(gameState);
			menuState.setStart(false);
		}
		//State.setState(menuState);
		
		if(State.getState() != null)
		{
			State.getState().update();
		}
	}
	
	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	private void render()
	{
		/***REF***/
		bufferStrat = getWindow().getCanvas().getBufferStrategy();
		if(bufferStrat == null)
		{
			getWindow().getCanvas().createBufferStrategy(3);
			return;
		}
		
		draw = bufferStrat.getDrawGraphics();
		draw.clearRect(0, 0, width, height);
		
		if(State.getState() != null)
		{
			State.getState().render(draw);
		}
		
		bufferStrat.show();
		draw.dispose();
		/***ref***/
	}
	
	//required by Runnable
	public void run()                 //2. Main game loop started by start 
	{	/*****REF Code taken from Codenmore and other sources******/						 
		int fps = 60;
		double timePerFrame = 1000000000 / fps; //1 billion nanosecs in sec
		double delta = 0;              //time until next call
		long now;        
		long lastTime = System.nanoTime();
		long timer = 0;                 //time until 1 sec /for fps
		long frames = 0;                //                 /counter
		
		while(true)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerFrame;
			timer += now - lastTime;         //for fps counter
			lastTime = now;
			
			if(delta >= 1)
			{
				update();
				render();
				frames++;     //for fps counter
				delta--;
			}
			
			if(timer >= 1000000000)   //fps counter
			{
				System.out.println("FPS: "+frames);
				frames = 0;
				timer = 0;
			}
		}/***REF code taken from codenmore and otherplaces***/
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}
}