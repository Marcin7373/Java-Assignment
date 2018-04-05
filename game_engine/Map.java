package game_engine;

import java.awt.Graphics;

import entities.Block;
import entities.Forms;

public class Map 
{
	private GameLoop game;
	private static int x, y;
	private int width, height;
	private int[] genForms = new int[2];
	private Block block;
	private Forms forms;
	
	public Map(GameLoop game)
	{
		this.game = game;
		width = game.getWidth();
		height = game.getHeight();
		forms = new Forms();
	}
	
	public void generateMap()
	{
		genForms[0] = 0;
		genForms[1] = 1;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics draw)
	{
		if(x <= width)
		{
			
		}
	}
}
