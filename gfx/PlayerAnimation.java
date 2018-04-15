package gfx;

import java.awt.Graphics;

public class PlayerAnimation 
{
	private int width, height, count = 0, i = 0, j = 0;
	private boolean toggleG, lockJ, lockL;//toggle ground, lock on jump, lock on land
	
	public PlayerAnimation(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics draw, float x, float y, boolean groundF)
	{
		count++;
		
		if(groundF == true && toggleG == false) lockL = true;
		if(groundF == false && toggleG == true) lockJ = true;
		
		if(groundF == toggleG && lockL == false && lockJ == false)
		{
			if(count%5 == 0)
			{
				count = 0;
				j++;
				if(j > 1) j = 0;
				
			}
			draw.drawImage(SpriteCrop.playerA[j], (int)x, (int)y, width, height, null);
		}
		else if(lockL == true)
		{
			lockL = true;
			if(count%6 == 0)
			{
				i++;
				if(i > 1)
				{
					i = 0;
					lockL = false;
				}
			}
			draw.drawImage(SpriteCrop.playerFall[i], (int)x, (int)y, width, height, null);
		}
		else if(lockJ == true)
		{
			lockJ = true;
			if(count%6 == 0)
			{
				i++;
				if(i > 1)
				{
					i = 0;
					lockJ = false;
				}
			}
			draw.drawImage(SpriteCrop.playerJump[i], (int)x, (int)y, width, height, null);
		}
		
		toggleG = groundF;
	}
}
