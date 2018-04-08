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
	private boolean canMoveX = true, canMoveYUp = true, canMoveYDown = true;
	private boolean peakF = false, jumpF = false, deathF = false;   //flags
	private int blockW = 100;
	private int offset, scroll, distance;
	private int speedX = 13, speedY = 8, velocityY = 1, gravity = 1;
	public Player(float x, float y, int width, int height) 
	{
		super(x, y, width, height);
		this.width = width;
		this.height = height;
		
		hitBox.x = (int)x+2;  //set up size of hitbox
		hitBox.y = (int)y+1;
		hitBox.width = width-4;
		hitBox.height = height-2;
	}

	@Override
	public void update()
	{
		xMove = 0;
		yMove = 0;
		if(left == true) xMove += -speedX;
		if(right == true) xMove += speedX;
		if(up == true) yMove += -speedX;
		if(down == true) yMove += speedX;
		
		if(canMoveX == true)
		{
			if((x +xMove) - x > 0) distance += (int) + ((x + xMove) - x); //track distance
			x += xMove;
			hitBox.x += xMove;
		}
		
		//scroll to left with level
		offset -= scroll;
		x -= offset;
		hitBox.x -= offset;
		offset = scroll;
	
		canMoveX = true;
		
		if(canMoveYUp == true || canMoveYDown == true)
		{
			y += yMove;
			hitBox.y += yMove;
		}
		canMoveYDown = true;
		canMoveYUp = true;
	}
	
	public void jump()    //jump!!!!!!!!!!!!!!!!!!!!!!!
	{	
		//System.out.println(canMoveYUp);
		//System.out.println(canMoveYDown);
		/*if(gravityF == false)
		{
			gravity = 0;
			gravityF = true;
		}
		System.out.println(gravity);
		*/
		xMove = 0;
		yMove = 0;
		if(left == true) xMove = -speedX;
		if(right == true) xMove = speedX;
		
		if(canMoveYDown == false)
		{
			velocityY = 1;
			peakF = false;
		}
		
		if(canMoveYUp == false)
		{
			velocityY = 0;
			peakF = false;
		}
		
		if(velocityY <= 0)
		{
			peakF = true;
		}
		else if(velocityY > 0)
		{
			peakF = false;
		}
		
		if(peakF == true)
		{
			jumpF = false;
		}
		
		if(jumpF == true || peakF == true)
		{
			up = false;
		}
		
		if(up == true)
		{
			if((canMoveYDown == false || jumpF == true) && peakF == false)
			{
				jumpF = true;
				yMove -= speedY * velocityY;
				velocityY -= 0.1;
				System.out.println(yMove+speedY);
			}
		}
		else if(jumpF == true && peakF == false)
		{
			yMove -= speedY * velocityY;
			velocityY -= 0.2;
		}
		
		if(down == true) 
		{
			yMove += speedY;
		}
	
		canMoveYDown = true;
		canMoveYUp = true;
	}
	
	public void collision(int xBlock, int yBlock)
	{
		int off = 1;
		if(xMove > 0)//remove yMove == 0 deal with the other problem and check order of execution i.e not render that one frame 
		{
			if(xBlock <= hitBox.x + hitBox.width + xMove && xBlock >= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveX = false;
			
					hitBox.x -= x - (xBlock - hitBox.width - off);
					x = xBlock - hitBox.width - off;
				}
			}
		}
		else if(xMove < 0) //going left
		{
			if(xBlock + blockW <= hitBox.x + hitBox.width + xMove && xBlock + blockW >= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveX = false;
					
					hitBox.x -= x - (xBlock + blockW + off); 
					x = xBlock + blockW + off;
				}
			}
		}
		
		if(yMove > 0) //going down
		{
			if(xBlock <= hitBox.x + hitBox.width + xMove && xBlock + blockW >= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveYDown = false;
					
					hitBox.y -= (int) (y -(yBlock - hitBox.height - off));
					y = yBlock - hitBox.height - off;
				}
			}
		}
		else if(yMove < 0) //going up
		{
			if(xBlock <= hitBox.x + hitBox.width + xMove && xBlock + blockW >= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveYUp = false;
					hitBox.y -= y - (yBlock + off);
					y = yBlock + off;
				}
			}
		}
	}

	public void render(Graphics draw) 
	{
		draw.drawImage(SpriteCrop.player, (int)x, (int)y, width, height, null);
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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getScroll() {
		return scroll;
	}

	public void setScroll(int scroll) {
		this.scroll = scroll;
	}
}
