package entities;

import java.awt.Graphics;

import game_engine.SpriteCrop;

public class Block extends Entity
{	
	public Block(float x, float y, int width, int height) 
	{
		super(x, y, width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public void render(Graphics draw) 
	{
		draw.drawImage(SpriteCrop.block, (int)x, (int)y, width, height, null);	
	}

}
