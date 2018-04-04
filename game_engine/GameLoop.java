package game_engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import states.GameState;
import states.Menu;
import states.State;

public class GameLoop
{
	private Window window;
	private int width = 400;
	private int height = 300;
	
	private Boolean running = true;
	
	private BufferStrategy bufferStrat;
	private Graphics draw;
	public BufferedImage test;
	
	//States
	private State gameState;
	private State menuState;
	
	public GameLoop() //constructor
	{	
		
	}
	
	private void init()
	{
		setWindow(new Window(width, height));
		SpriteCrop.init();
		
		gameState = new GameState(this);
		menuState = new Menu(this);
		State.setState(gameState);
	}

	private void update()
	{
		if(State.getState() != null)
		{
			State.getState().update();
		}
	}
	
	private void render()
	{
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
	}
	
	//required by Runnable
	public void run()                 //2. Main game loop started by start 
	{							 
		init();
		
		int fps = 60;
		double timePerFrame = 1000000000 / fps; //1 billion nanosecs in sec
		double delta = 0;              //time until next call
		long now;        
		long lastTime = System.nanoTime();
		long timer = 0;                 //time until 1 sec /for fps
		long frames = 0;                //                 /counter
		
		while(running == true)
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
		}
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
}