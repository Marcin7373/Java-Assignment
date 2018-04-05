package entities;

import java.awt.Graphics;

import game_engine.SpriteCrop;

public class Block extends Entity
{	
	public Block(float x, float y) 
	{
		super(x, y);
		width = 50;
		height = 50;
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public void render(Graphics draw) 
	{
		draw.drawImage(SpriteCrop.block, (int)x, (int)y, null);	
	}

}
