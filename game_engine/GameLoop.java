package game_engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import states.GameState;
import states.State;

public class GameLoop
{
	public int i = 0;
	private Window window;
	public int width, height;
	
	private Boolean running = true;
	
	private BufferStrategy bufferStrat;
	private Graphics draw;
	public BufferedImage test;
	
	private State gameState;
	
	public GameLoop() //constructor
	{	
		
	}
	
	private void init()
	{
		window = new Window();
		SpriteCrop.init();
		
		gameState = new GameState();
		State.setState(gameState);
	}

	private void tick()
	{
		if(State.getState() != null)
		{
			State.getState().tick();
		}
	}
	
	private void render()
	{
		bufferStrat = window.getCanvas().getBufferStrategy();
		if(bufferStrat == null)
		{
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		
		draw = bufferStrat.getDrawGraphics();
		draw.clearRect(0, 0, width, height);
		
		if(State.getState() != null)
		{
			State.getState().render(draw);
		}
		
		if(i == 0)
		{
		draw.drawImage(SpriteCrop.one, 0, 0, null);   //null = image observer not needed
		i = 1;
		}
		else {
		draw.drawImage(SpriteCrop.two, 0, 0, null);
		i = 0;
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
				tick();
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
	
}