package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game_engine.Map;
import game_engine.SpriteCrop;

public class Player extends Entity implements KeyListener
{
	private boolean left, right, up, down;
	private Map map;
	
	public Player(Map map, float x, float y, int width, int height) 
	{
		super(x, y, width, height);
		this.map = map;
		this.width = width;
		this.height = height;
		
		hitBox.x = (int)x;  //set up size of hitbox
		hitBox.y = (int)y;
		hitBox.width = width-25;
		hitBox.height = height;
	}

	public void xMove()
	{
		int move = 0;
		if(left == true) move = -5;
		if(right == true) move = +5;
		
		
		if(move > 0)
		{
			int tx = (int)(x + move + hitBox.x + hitBox.width) / map.getbWidth();
			
		}
		else if(move < 0)
		{
			x += move;
			hitBox.x = (int)x;
		}
	}
	
	public void yMove()
	{
		int move = 0;
		if(up == true) move = -5;
		if(down == true) move = 5;
		y += move;
		hitBox.y = (int)y;
	}
	
	public boolean collision(int x, int y)
	{
		if(map.getBlock())
		{
			return true;
		}
		else if()
		{
			return true;
		}
	}
	 
	@Override
	public void update() 
	{
		xMove();
		yMove();
		
	}

	public void render(Graphics draw) 
	{
		draw.drawImage(SpriteCrop.one, (int)x, (int)y, width, height, null);
		draw.setColor(Color.red);
		draw.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}

	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = true;
		if(event.getKeyCode() == KeyEvent.VK_S) down = true;
		if(event.getKeyCode() == KeyEvent.VK_A) left = true;
		if(event.getKeyCode() == KeyEvent.VK_D) right = true;
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = false;
		if(event.getKeyCode() == KeyEvent.VK_S) down = false;
		if(event.getKeyCode() == KeyEvent.VK_A) left = false;
		if(event.getKeyCode() == KeyEvent.VK_D) right = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}
