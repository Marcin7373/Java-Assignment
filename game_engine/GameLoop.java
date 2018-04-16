package game_engine;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import gfx.SpriteCrop;
import states.GameState;
import states.MenuState;
import states.State;

public class GameLoop
{
	private Window window;
	private static final int width = 1200, height = 500;//window sizes
	private float distance;                             //measuring score
	
	private BufferStrategy bufferStrat; 
	private Graphics draw;
	
	//States
	private State gameState;
	private State menuState;
	
	public GameLoop() //constructor
	{	
		window = new Window(width, height);
		SpriteCrop.init();                    //loads all images and crops them
		
		gameState = new GameState(this);      //game = were all playing will happen
		menuState = new MenuState(this);      //menu = after death menu restarts game = death screen
		this.getWindow().getFrame().addKeyListener((KeyListener) menuState);//KeyListener used by menu added to window
		State.setState(gameState);            //start the game in game as opposed to menu
	}
    //Updates variables for positions of objects and other
	private void update()
	{
		if(gameState.getMap().getPlayer().isDeathF())               //if death flag set by player 
		{
			distance = gameState.getMap().getPlayer().getDistance();//get distance traveled 
			State.setState(menuState);                              //back to menu to show score
		}

		if(menuState.isStart())                                     //if prompt triggered in menu 
		{
			gameState = new GameState(this);                        //start new game
			State.setState(gameState);                              //load the new game
			menuState.setStart(false);                              //menu object is the same so reset start flag
		}
		
		State.getState().update();                                  //update whichever state is set
	}
	
	//Renders or displays things in coordinates updated by update
	private void render()
	{
		/***REF Code taken from https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src***/
		bufferStrat = getWindow().getCanvas().getBufferStrategy();//image buffer
		
		if(bufferStrat == null)
		{
			getWindow().getCanvas().createBufferStrategy(2);      //create new one if one doesn't exist     
			return;
		}
		
		draw = bufferStrat.getDrawGraphics();
		draw.clearRect(0, 0, width, height); //clears the screen for next frame
		
		State.getState().render(draw);	     //renders whichever state is set
		
		bufferStrat.show();                  //shows everything in the buffer
		draw.dispose();                      //clear for next frame
		/***REF Code taken from Codenmore***/
	}
	
	//required by Runnable
	public void run()                           //Main game loop started by main 
	{	/*****REF Code taken from https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src******/						 
		int fps = 60;
		double timePerFrame = 1000000000 / fps; //1 billion nanosecs in sec
		double delta = 0;                       //time until next call
		long now;        
		long lastTime = System.nanoTime();
		long timer = 0;                         //time until 1 sec     |for fps
		long frames = 0;                        //counts up to fps set |counter
		
		while(true)  //runs until game is canceled out of
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerFrame;
			timer += now - lastTime;            //for fps counter
			lastTime = now;
			
			if(delta >= 1)
			{
				update();
				render();
				frames++;             //for fps counter
				delta--;
			}
			   /***FPS counter***/
			if(timer >= 1000000000)   //number of nanoseconds in a sec   
			{
				System.out.println("FPS: " + frames);
				frames = 0;           //reset cycle
				timer = 0;            //reset timer
			}
		}/***REF code taken from Codenmore***/
	}

	/***Getters and Setters***/
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
	public float getDistance() {
		return distance;
	}
}