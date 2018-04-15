package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gfx.PlayerAnimation;
import gfx.SpriteCrop;

public class Player extends Entity implements KeyListener
{
	private boolean left, right, up, down;
	private int xMove, yMove;
	private boolean canMoveX = true, canMoveYUp = true, canMoveYDown = false; //canMoveYDown = false so player can jump at spawn
	private boolean groundF = false, peakF = false, jumpF = false, deathF = false;   //flags
	private int blockW = 98, mWidth, mHeight; 
	private int offset, scroll, distance, jumpCount = 0;
	private int speedX = 9, speedY = 16; //x = 9 y = 8
	private float velocityY = 1, gravity = 0;
	private PlayerAnimation animation;
	
	public Player(float x, float y, int width, int height, int mWidth, int mHeight) 
	{
		super(x, y, width, height);
		this.width = width;
		this.height = height;
		this.mWidth = mWidth;
		this.mHeight = mHeight;
		animation = new PlayerAnimation(width, height);
		
		hitBox.x = (int)x+2;  //set up size of hitbox
		hitBox.y = (int)y;
		hitBox.width = width-4;
		hitBox.height = height-1;
	}

	@Override
	public void update()
	{
		xMove = 0;
		yMove = 0;
		
		jump();
		
		if(left == true) xMove += -speedX;
		if(right == true) xMove += speedX;
		
		
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
	}
	
	public void jump()    //jump!!!!!!!!!!!!!!!!!!!!!!!
	{	
		if(left == true) xMove = -speedX;
		if(right == true) xMove = speedX;
		
		if(canMoveYDown == false)
		{
			velocityY = 1;
			peakF = false;
			groundF = true;
		}
		else
		{
			groundF = false;
		}
		
		if(canMoveYUp == false)
		{
			velocityY = 0;
			peakF = true;
			jumpF = false;
		}
		
		if(velocityY > 0)
		{
			peakF = false;
		}
		else if(velocityY <= 0)
		{
			peakF = true;
			jumpF = false;
		}
		
		if(jumpF == true || peakF == true)
		{
			//up = false;
			groundF = false;
		}
		
		/*if(up == true && groundF == true)
		{
			jumpCount++;
		}
		
		if(jumpCount > 1)
		{
			up = false;
			jumpCount = 0;
		} prevent double jumping*/
		
		if(up == true)
		{
			if((groundF == true || jumpF == true) && peakF == false)
			{
				jumpF = true;
				yMove -= speedY * velocityY;
				velocityY += 0.03;//total jump
			}
		}
		
		if(jumpF == true || peakF == true)
		{
			yMove -= speedY * velocityY;
			if(velocityY > -1.8)//cap on falling speed
			{
				velocityY = velocityY - 0.1f;//fall rate
			}
		}
		
		if(jumpF == false && peakF == false)
		{
			yMove += 13;//sliding of edge gravity
		}
		//System.out.println("vel: "+velocityY+" Jump:"+jumpF+" Peak:"+peakF+" Can:"+canMoveYDown+" Ground:"+groundF);
		System.out.println(distance);
		if(down == true) 
		{
			yMove = speedY+5;
		}
		
		canMoveYDown = true;
		canMoveYUp = true;
	}
	
	public void collision(int xBlock, int yBlock)
	{
		xBlock -= 2; //makes block hitBox smaller = -2
		int off = 1; //offset o stop triggering hitbox when pushing back
		if(xMove > 0) 
		{
			if(xBlock +3<= hitBox.x + hitBox.width + xMove && xBlock +3>= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height -yMove && yBlock >= hitBox.y -yMove)
				{
					canMoveX = false;
					
					hitBox.x -= x - (xBlock - hitBox.width - off);//have to get old x before change
					x = xBlock - hitBox.width - off;
				}
			}
			
			if(mWidth <= hitBox.x + hitBox.width + xMove && mWidth >= hitBox.x + xMove) 
			{
				canMoveX = false;
				hitBox.x -= x - (mWidth - hitBox.width - off);//have to get old x before change
				x = mWidth - hitBox.width - off;
			}
		}
		else if(xMove < 0) //going left
		{
			if(xBlock + blockW -3<= hitBox.x + hitBox.width + xMove && xBlock + blockW -3>= hitBox.x + xMove)
			{  
				if(yBlock <= hitBox.y + hitBox.height -yMove && yBlock >= hitBox.y - yMove)
				{
					canMoveX = false;
					
					hitBox.x -= x - (xBlock + blockW + off); 
					x = xBlock + blockW + off;
				}
			}
		}
		else if(0 >= hitBox.x + hitBox.width + xMove)
		{
			deathF = true;
		}

		if(yMove > 0) //going down
		{
			if(xBlock +3<= hitBox.x + hitBox.width && xBlock + blockW -3>= hitBox.x)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveYDown = false;
					
					hitBox.y -= (int) (y -(yBlock - hitBox.height - off));
					y = yBlock - hitBox.height - off;
				}
			}
			
			if(mHeight+height <= hitBox.y + hitBox.height + yMove && mHeight+height >= hitBox.y + yMove)
			{
				//deathF = true;
				hitBox.y = 0;
				y = 0;
			}
		}
		else if(yMove < 0) //going up
		{    
			if(xBlock +3<= hitBox.x + hitBox.width && xBlock + blockW -3>= hitBox.x)
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
		animation.render(draw, x, y, groundF);
		draw.setColor(Color.red);
		//draw.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}

	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = true;
		if(event.getKeyCode() == KeyEvent.VK_S) down = true;
		if(event.getKeyCode() == KeyEvent.VK_A) left = true;
		if(event.getKeyCode() == KeyEvent.VK_D) right = true;
		if(event.getKeyCode() == KeyEvent.VK_UP) up = true;
		if(event.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		if(event.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = false;
		if(event.getKeyCode() == KeyEvent.VK_S) down = false;
		if(event.getKeyCode() == KeyEvent.VK_A) left = false;
		if(event.getKeyCode() == KeyEvent.VK_D) right = false;
		if(event.getKeyCode() == KeyEvent.VK_UP) up = false;
		if(event.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		if(event.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
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

	public boolean isDeathF() {
		return deathF;
	}

	public void setDeathF(boolean deathF) {
		this.deathF = deathF;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
