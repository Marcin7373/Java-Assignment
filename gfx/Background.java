package gfx;

import java.awt.Color;
import java.awt.Graphics;

public class Background
{
	private int width, height;
	private int loop1 = 0, loop2 = 0, loop3 = 0, loop4 = 0;
	private float rate1 = 0.2f, rate2 = 0.9f, rate3 = 0.5f, rate4 = 1.2f;
	
	public Background(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics draw, int scroll)
	{
		if(scroll*rate1 + loop1 <= -width)
		{
			loop1 += width;
		}
		
		if(scroll*rate2 + loop2 <= -width)
		{
			loop2 += width;
		}
		
		if(scroll*rate3 + loop3 <= -width)
		{
			loop3 += width;
		}
		
		if(scroll*rate4 + loop4 <= -width)
		{
			loop4 += width;
		}
		
		draw.setColor(new Color(130, 220, 236));
		draw.fillRect(0, 0, width, height);
		draw.drawImage(SpriteCrop.background[0], 0, 221, width, 51, null);
		
	    draw.drawImage(SpriteCrop.background[1], (int) (scroll * rate1)+loop1, 264, width, 80, null);
	    draw.drawImage(SpriteCrop.background[1], (int) (scroll * rate1)+loop1+width, 264, width, 80, null);
	 
	    draw.drawImage(SpriteCrop.background[2], (int) (scroll * rate2)+loop2, 14, width, 446, null);
	    draw.drawImage(SpriteCrop.background[2], (int) (scroll * rate2)+loop2+width, 14, width, 446, null);
	    
	    draw.drawImage(SpriteCrop.background[3], (int) (scroll * rate3)+loop3, 90, width, 318, null);
	    draw.drawImage(SpriteCrop.background[3], (int) (scroll * rate3)+loop3+width, 90, width, 318, null);
	    
	    draw.drawImage(SpriteCrop.background[4], (int) (scroll * rate4)+loop4, 386, width, 137, null);
	    draw.drawImage(SpriteCrop.background[4], (int) (scroll * rate4)+loop4+width, 386, width, 137, null);
	    
	    //draw.drawImage(SpriteCrop.one, bX+scroll+loop, bY, width, height, null);
		//draw.drawImage(SpriteCrop.one, bX+scroll+loop+width, bY, width, height, null);
		//scroll = (int) (scroll * 1.1);
		//draw.drawImage(SpriteCrop.two, bX+scroll+loop2, bY, width, height, null);
		//draw.drawImage(SpriteCrop.two, bX+scroll+loop2+width, bY, width, height, null);
		//draw.drawImage(SpriteCrop.three, bX, bY, width, height, null);
		//System.out.println(bX + " " + loop);
	}
}

