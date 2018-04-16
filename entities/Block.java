package entities;

import java.awt.Graphics;

import gfx.SpriteCrop;

public class Block extends Entity
{	
	public Block(float x, float y, int bWidth, int bHeight) 
	{
		super(x, y, bWidth, bHeight);
		this.width = bWidth;
		this.height = bHeight;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics draw) 
	{                        //render higher to so the block looks like it connects, accounting for hitbox
		draw.drawImage(SpriteCrop.block, (int)x, (int)y-3, width, height+4, null);
	}

}
