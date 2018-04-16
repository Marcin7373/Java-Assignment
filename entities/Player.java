package entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gfx.PlayerAnimation;

public class Player extends Entity implements KeyListener
{
	private boolean left, right, up, down;//input detection
	private int xMove, yMove;                     //totals for movement on each axis, added to respective coordinates
	private boolean canMoveYUp = true, canMoveYDown = false;  //canMoveYDown = false so player can jump at spawn
	private boolean groundF = false, peakF = false, jumpF = false, deathF = false;   //flags for jump algorithm
	private int blockW = 98, mWidth, mHeight;                                        //map width and height
	private int offset, scroll;                   //offset = stores the difference in scroll between cycles
	private int speedX = 18, speedY = 16;         //values gotten from play testing
	private float velocityY = 1, distance;        //velocity = multiplier for jump, 
	private PlayerAnimation pAnimation;     
	
	public Player(float x, float y, int width, int height, int mWidth, int mHeight) 
	{
		super(x, y, width, height);
		this.width = width;
		this.height = height;
		this.mWidth = mWidth;
		this.mHeight = mHeight;
		pAnimation = new PlayerAnimation(width, height);
		
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
		
		jump();  //accounts 
		
		if((x + xMove) - x > 0)            //if movement is positive 
		{
			distance += ((x + xMove) - x); //track distance
		}
		
		x += xMove;       //apply total movement on x axis
		hitBox.x += xMove;
		y += yMove;       //apply total movement on y axis
		hitBox.y += yMove;
		
		//scroll to left with level
		offset -= scroll; //difference between current and last scroll
		x -= offset;
		hitBox.x -= offset;
		offset = scroll;
	}
	
	public void jump()    
	{	
		if(left == true) xMove += -speedX; //if movement detected apply to total to be checked
		if(right == true) xMove += speedX;
		
		if(canMoveYDown == false)          //if on ground
		{
			velocityY = 1;
			peakF = false;
			groundF = true;
		}
		else                     
		{
			groundF = false;
		}
		
		if(canMoveYUp == false)   //if platform hit from below
		{
			velocityY = 0;
			peakF = true;
			jumpF = false;
		}
		
		if(velocityY > 0)        //going up
		{
			peakF = false;
		}
		else if(velocityY <= 0) //at peak and going down
		{
			peakF = true;
			jumpF = false;
		}
		
		if(up == true)  //on jump
		{
			if((groundF == true || jumpF == true) && peakF == false)//if in first half of jump
			{
				jumpF = true;
				yMove -= speedY * velocityY;
				velocityY += 0.03;          //extends jump
			}
		}
		
		if(jumpF == true || peakF == true)//if in the air
		{
			yMove -= speedY * velocityY;
			
			if(velocityY > -1.8)//cap on falling speed
			{
				velocityY = velocityY - 0.1f;//fall rate
			}
		}
		
		if(jumpF == false && peakF == false)//if not in the air
		{
			yMove += 13;//sliding of edge gravity
		}
		
		if(down == true) //speed up falling
		{
			yMove = speedY+5;
		}
		
		canMoveYDown = true;//reset for next cycle
		canMoveYUp = true;
	}
	
	public void collision(int xBlock, int yBlock)
	{
		xBlock -= 2; //makes block hitBox smaller = -2
		int off = 1; //offset to stop triggering hitbox when pushing back
		
		//if moving right
		if(xMove > 0)       
		{                  //is player not on left of platform
			if(xBlock +3 <= hitBox.x + hitBox.width + xMove && xBlock +3 >= hitBox.x + xMove)//3s = adjustments to platfrom hitbox
			{                //is player at same level as platform
				if(yBlock <= hitBox.y + hitBox.height -yMove && yBlock >= hitBox.y - yMove)
				{
					         //place player outside collision area
					hitBox.x -= x - (xBlock - hitBox.width - off);//have to get old x before change
					x = xBlock - hitBox.width - off; //hitbox and image render separate
				}
			}
			//prevent player from moving past right side of screen
			if(mWidth + 40 <= hitBox.x + hitBox.width + xMove) //+40=allow some of the player to go through
			{
				hitBox.x -= x - (mWidth - hitBox.width - off + 40);//have to get old x before change
				x = mWidth - hitBox.width - off + 40;
			}
		}
		else if(xMove < 0) //going left
		{               //if player not on right of platform
			if(xBlock + blockW -3 <= hitBox.x + hitBox.width + xMove && xBlock + blockW -3 >= hitBox.x + xMove)
			{               
				if(yBlock <= hitBox.y + hitBox.height -yMove && yBlock >= hitBox.y - yMove)
				{
					hitBox.x -= x - (xBlock + blockW + off);//change hitbox relative to x 
					x = xBlock + blockW + off;
				}
			}
		}
		else if(0 >= hitBox.x + hitBox.width + xMove) //death flag if too far left off screen
		{
			deathF = true;
		}

		if(yMove > 0) //going down
		{                    //if player not above platform
			if(xBlock +3 <= hitBox.x + hitBox.width && xBlock + blockW -3 >= hitBox.x)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveYDown = false;
					
					hitBox.y -= (int) (y - (yBlock - hitBox.height - off));
					y = yBlock - hitBox.height - off;
				}
			}
			               //fallen off = below screen   
			if(mHeight+height <= hitBox.y + hitBox.height + yMove && mHeight+height >= hitBox.y + yMove)
			{
				deathF = true;
			}
		}
		else if(yMove < 0) //going up
		{      //if player not below
			if(xBlock +3 <= hitBox.x + hitBox.width && xBlock + blockW -3 >= hitBox.x)
			{
				if(yBlock <= hitBox.y + hitBox.height + yMove && yBlock >= hitBox.y + yMove)
				{
					canMoveYUp = false;
					hitBox.y -= y - (yBlock + off);
					y = yBlock + off;
				}
			}//end if x
		}//end if up
	}//end method

	public void render(Graphics draw) 
	{
		pAnimation.render(draw, x, y, groundF);//player animations
	}

	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = true;   //input for w,a,s,d
		if(event.getKeyCode() == KeyEvent.VK_S) down = true;
		if(event.getKeyCode() == KeyEvent.VK_A) left = true;
		if(event.getKeyCode() == KeyEvent.VK_D) right = true;
		if(event.getKeyCode() == KeyEvent.VK_UP) up = true;  //input for arrows
		if(event.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		if(event.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = false;      //w,a,s,d
		if(event.getKeyCode() == KeyEvent.VK_S) down = false;
		if(event.getKeyCode() == KeyEvent.VK_A) left = false;
		if(event.getKeyCode() == KeyEvent.VK_D) right = false;
		if(event.getKeyCode() == KeyEvent.VK_UP) up = false;     //arrows
		if(event.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		if(event.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	/***Getters and Setters***/
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

	public float getDistance() {
		return distance;
	}
}
