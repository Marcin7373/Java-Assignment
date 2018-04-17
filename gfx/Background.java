package gfx;

import java.awt.Color;
import java.awt.Graphics;

public class Background
{
	private int width, height;
	private int loop1 = 0, loop2 = 0, loop3 = 0, loop4 = 0;//allows for looping of an image = total offset of screens passed
	private float rate1 = 0.1f, rate2 = 0.9f, rate3 = 0.5f, rate4 = 1.2f;//rate compared to normal scroll rate of level
	
	public Background(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void update(int scroll) 
	{      //2nd layer
		if(scroll*rate1 + loop1 <= -width)//if fully off screen = placed it back on he other side 
		{
			loop1 += width;
		}
		   //3rd layer
		if(scroll*rate2 + loop2 <= -width)
		{
			loop2 += width;
		}
		   //4th layer
		if(scroll*rate3 + loop3 <= -width)
		{
			loop3 += width;
		}
		   //5th layer
		if(scroll*rate4 + loop4 <= -width)
		{
			loop4 += width;
		}
	}
	
	public void render(Graphics draw, int scroll)
	{
		draw.setColor(new Color(130, 220, 236));//light blue back of background
		draw.fillRect(0, 0, width, height);     //draw light blue background
		draw.drawImage(SpriteCrop.background[0], 0, 221, width, 51, null);//stationary cloud
		
	    draw.drawImage(SpriteCrop.background[1], (int) (scroll * rate1)+loop1, 264, width, 80, null);  //2nd layer
	    draw.drawImage(SpriteCrop.background[1], (int) (scroll * rate1)+loop1+width, 264, width, 80, null);
	 
	    draw.drawImage(SpriteCrop.background[2], (int) (scroll * rate2)+loop2, 14, width, 446, null);  //3rd layer
	    draw.drawImage(SpriteCrop.background[2], (int) (scroll * rate2)+loop2+width, 14, width, 446, null);
	    
	    draw.drawImage(SpriteCrop.background[3], (int) (scroll * rate3)+loop3, 90, width, 318, null);  //4th layer
	    draw.drawImage(SpriteCrop.background[3], (int) (scroll * rate3)+loop3+width, 90, width, 318, null);
	    
	    draw.drawImage(SpriteCrop.background[4], (int) (scroll * rate4)+loop4, 386, width, 137, null);  //5th layer
	    draw.drawImage(SpriteCrop.background[4], (int) (scroll * rate4)+loop4+width, 386, width, 137, null);
	}
}

