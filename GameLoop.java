package Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameLoop
{
	private Window window;
	public int width, height;
	
	private Boolean running = true;
	
	private BufferStrategy bufferStrat;
	private Graphics draw;
	public BufferedImage test;
	
	public GameLoop() //constructor
	{	
		
	}
	
	private void init()
	{
		window = new Window();
		SpriteCrop.init();
	}

	private void tick()
	{
		
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
		draw.drawImage(SpriteCrop.one, 0, 0, null);   //null = image observer not needed
		draw.drawImage(SpriteCrop.two, 200, 150, null);
		bufferStrat.show();
		draw.dispose();
	}
	
	//required by Runnable
	public void run()                 //2. Main game loop started by start 
	{							 
		init();
		
		while(running == true)
		{
			tick();
			render();
		}
	}
	
}