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
	private boolean groundF = false, peakF = false, jumpF = false, deathF = false;   //flags
	private int blockW = 98; 
	private int offset, scroll, distance;
	private int speedX = 7, speedY = 18; //x = 13 y = 8
	private float velocityY = 1, gravity = 0;
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
		
		if(canMoveYUp == false)
		{
			velocityY = 0;
			peakF = true;
		}
		
		if(velocityY > 0)
		{
			peakF = false;
		}
		else if(velocityY <= 0)
		{
			peakF = true;
		}
		
		if(peakF == true)
		{
			jumpF = false;
		}
		
		if(jumpF == true || peakF == true)
		{
			//up = false;
			groundF = false;
		}
		
		if(up == true)
		{
			if((groundF == true || jumpF == true) && peakF == false)
			{
				jumpF = true;
				yMove -= speedY * velocityY;
				velocityY += 0.004;
				
			}
		}
		
		if(jumpF == true || peakF == true)
		{
			yMove -= speedY * velocityY;
			velocityY = velocityY - 0.03f;
		}
		
		if(jumpF == false && peakF == false && groundF == true && canMoveYDown == false)
		{
			yMove += 5;
		}
		
		
		System.out.println("vel: "+velocityY+" Jump:"+jumpF+" Peak:"+peakF+" Can:"+canMoveYDown+" Ground:"+groundF);
		if(down == true) 
		{
			yMove = speedY;
		}
	
		
		
		if(jumpF == true)
		{
			canMoveYDown = true;
		}
		canMoveYUp = true;
	}
	
	public void collision(int xBlock, int yBlock)
	{
		xBlock -= 2; //makes block hitBox smaller
		int off = 1; //offset o stop triggering hitbox when pushing back
		if(xMove > 0) 
		{
			if(xBlock +3<= hitBox.x + hitBox.width + xMove && xBlock +3>= hitBox.x + xMove)
			{
				if(yBlock <= hitBox.y + hitBox.height -yMove&& yBlock >= hitBox.y -yMove)
				{
					canMoveX = false;
					System.out.println(x- (xBlock - hitBox.width - off));
					hitBox.x -= x - (xBlock - hitBox.width - off);//have to get old x before change
					x = xBlock - hitBox.width - off;
				}
			}
		}
		else if(xMove < 0) //going left
		{
			if(xBlock + blockW -3<= hitBox.x + hitBox.width + xMove && xBlock + blockW -3>= hitBox.x + xMove)
			{     //+13 -13
				if(yBlock <= hitBox.y + hitBox.height -yMove && yBlock >= hitBox.y - yMove)
				{
					canMoveX = false;
					
					hitBox.x -= x - (xBlock + blockW + off); 
					x = xBlock + blockW + off;
					
				}
			}
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
		}
		else if(yMove < 0) //going up
		{     //50 <= x  && 200 >= x   52 <= x  && 198 >= x
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
