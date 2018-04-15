package gfx;

import java.awt.Graphics;

public class Background
{
	private int bX = 0, bY = 0, width, height;
	private int scroll, loop = 0, loop2 = 0;
	
	public Background(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics draw, int scroll)
	{
		if(scroll + loop <= -width)
		{
			loop += width;
		}
		
		if(scroll*1.1 + loop2 <= -width)
		{
			loop2 += width;
		}
		
		//draw.drawImage(SpriteCrop.one, bX+scroll+loop, bY, width, height, null);
		//draw.drawImage(SpriteCrop.one, bX+scroll+loop+width, bY, width, height, null);
		//scroll = (int) (scroll * 1.1);
		//draw.drawImage(SpriteCrop.two, bX+scroll+loop2, bY, width, height, null);
		//draw.drawImage(SpriteCrop.two, bX+scroll+loop2+width, bY, width, height, null);
		//draw.drawImage(SpriteCrop.three, bX, bY, width, height, null);
		//System.out.println(bX + " " + loop);
	}
}

