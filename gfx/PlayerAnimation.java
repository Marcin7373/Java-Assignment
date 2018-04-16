package gfx;

import java.awt.Graphics;

public class PlayerAnimation 
{   
	private int pWidth, pHeight, cycle = 0, frames = 0;
	private boolean toggleG, lockJ, lockL;//toggle ground, lock on jump, lock on land
	
	public PlayerAnimation(int width, int height)
	{
		this.pWidth = width;    //player width and height
		this.pHeight = height;
	}
	
	public void render(Graphics draw, float x, float y, boolean groundF)
	{
		cycle++;                     //game cycles
		
		if(toggleG == true && groundF == false) lockJ = true;//if ground was true now false = jumping
		if(toggleG == false && groundF == true) lockL = true;//if ground was false now true = landing 
		
		if(groundF == toggleG && lockL == false && lockJ == false)//if not jumping or landing
		{
			if(cycle%6 == 0)               //switch frames every 6 game cycles
			{
				cycle = 0;
				frames++;                  //next frame in array
				
				if(frames > 1) frames = 0; //go back to start of array cycle = 1,0,1,0...
			}
			draw.drawImage(SpriteCrop.player[frames], (int)x, (int)y, pWidth, pHeight, null);
		}
		else if(lockL == true)
		{
			if(cycle%4 == 0)         //switch frames every 4 game cycles
			{
				frames++;
				if(frames > 1)       //array cycle
				{
					frames = 0;     
					lockL = false;   //unlock all other animations = landing over
				}
			}
			draw.drawImage(SpriteCrop.playerLand[frames], (int)x, (int)y, pWidth, pHeight, null);
		}
		else if(lockJ == true)
		{
			if(cycle%8 == 0)     //switch frames every 8 game cycles
			{
				frames++;
				if(frames > 1)
				{
					frames = 0;
					lockJ = false;//unlock all other animations = jump over
				}
			}
			draw.drawImage(SpriteCrop.playerJump[frames], (int)x, (int)y, pWidth, pHeight, null);
		}
		
		toggleG = groundF; //toggle for next cycle
	}
}
