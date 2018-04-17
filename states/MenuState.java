package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game_engine.GameLoop;
import gfx.SpriteCrop;

public class MenuState extends State implements KeyListener
{
	private float distance, highScore;
	private boolean start = false;
	private int width, height;
	
	public MenuState(GameLoop game)
	{
		super(game);
		this.width = game.getWidth();
		this.height = game.getHeight();
	}
	
	@Override
	public void update() 
	{
		distance = game.getDistance();
		distance = distance/100;
		if(highScore < distance)
		{
			highScore = distance;
		}
	}

	@Override
	public void render(Graphics draw) 
	{
		draw.setColor(new Color(201, 231, 236));//first layer of background
		draw.fillRect(0, 0, width, height);     //little lighter then game
		draw.drawImage(SpriteCrop.background[0], 0, 221, width, 51, null);//static version of
	    draw.drawImage(SpriteCrop.background[1], 0, 264, width, 80, null);//background from the game
	    draw.drawImage(SpriteCrop.background[2], 0, 14, width, 446, null);
	    draw.drawImage(SpriteCrop.background[3], 0, 90, width, 318, null);
	    draw.drawImage(SpriteCrop.background[4], 0, 386, width, 137, null);
       
	    //looks clearer
		((Graphics2D) draw).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        draw.setFont(new Font("Dialog", Font.BOLD, 50));
        draw.setColor(Color.BLACK);
        draw.drawRoundRect(351, 51, 500, 130, 30, 30);//border around enter prompt
        draw.setColor(new Color(255, 249, 243));      //almost white
    	draw.fillRoundRect(350, 50, 500, 130, 30, 30);//enter box
    	
    	draw.setColor(new Color(25, 52, 56));         //dark purple
        draw.drawString("\"Enter\" to go again", 372, 128); 
        draw.drawString("Distance:   "+distance, 390, 280);
        draw.drawString("Highscore: "+highScore, 390, 360);
	}	

	/***Key listener methods***/
	@Override
	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_ENTER) start = true; 
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	/***Getters and Setters***/
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
}
