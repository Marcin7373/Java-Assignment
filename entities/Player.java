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
	private int xMove, yMove;
	private boolean canMoveX = true, canMoveY = true;
	private int blockW = 100;
	private int offset = -3;
	public Player(float x, float y, int width, int height) 
	{
		super(x, y, width, height);
		this.width = width;
		this.height = height;
		
		hitBox.x = (int)x;  //set up size of hitbox
		hitBox.y = (int)y;
		hitBox.width = width;
		hitBox.height = height;
	}

	public void Move(float scroll)
	{	
		if(canMoveX == true)
		{
			x += xMove;
		}
		offset -= scroll;
		x -= offset;
		offset = (int)scroll;
		hitBox.x = (int)x;
		xMove = 0;
		canMoveX = true;
		
		if(canMoveY == true)
		{
			y += yMove;
		}
		hitBox.y = (int)y;
		yMove = 0;
		canMoveY = true;
	}
	
	public void collision(int xBlock, int yBlock)
	{
		if(xMove > 0)
		{
			if(xBlock <= hitBox.x + hitBox.width + xMove && xBlock >= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveX = false;
				}
			}
		}
		else if(xMove < 0)
		{
			if(xBlock + blockW <= hitBox.x + hitBox.width + xMove && xBlock + blockW >= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveX = false;
				}
			}
		}
	}
	 
	@Override
	public void update() 
	{
		if(up == true) yMove = -5;
		if(down == true) yMove = 5;
		if(left == true) xMove = -5;
		if(right == true) xMove = 5;
		
		//graviy and scroll applied here to x/ymove for later clac
		
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

	public void setxMove(float scroll) 
	{
		xMove += -scroll;
	}
}
