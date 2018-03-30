package Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameLoop implements Runnable 
{
	private Window window;
	public int width, height;
	
	private Thread thread;
	private Boolean running = false;
	
	private BufferStrategy bufferStrat;
	private Graphics draw;
	public BufferedImage test;
	
	public GameLoop() //constructor
	{	
		
	}
	
	private void init()
	{
		Window window = new Window();
		test = ImageLoader.loadImage("/new/1.jpg");
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
		draw.drawImage(test, 20, 20, null);   //null = image observer not needed
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
		
		stop();
	}
	
	public synchronized void start()   //1. starts run
	{
		if(running)
		{
			return;
		}
		else
		{
			running = true;
			thread = new Thread(this);   //puts GameLoop on the thread
			thread.start();              //calls run
		}
	}
	
	public synchronized void stop()
	{
		if(running == false)
		{
			try 
			{
				thread.join();
			}catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	
}
