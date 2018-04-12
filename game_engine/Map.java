package game_engine;

import java.awt.Graphics;
import java.util.Random;

import entities.Block;
import entities.Player;

public class Map 
{
	private GameLoop game;
	private int width, height;
	private final int tPats = 4;
	private final int bWidth = 100, bHeight = 5; //block width
	private int scroll, loop = 0;
	private Block block;
	private int[] genMap = new int[tPats];
	private int[][] pattern1 = new int[2][4];
	private int[][] pattern2 = new int[2][4];
	private int[][] pattern3 = new int[2][4];
	private int[][] pattern4 = new int[2][4];
	private Player player;
	Random rand = new Random();
	
	public Map(GameLoop game)
	{
		this.game = game;
		width = game.getWidth();
		height = game.getHeight();
		player = new Player(120, height-71, 50, 50, width, height);
		player.setOffset(-scroll);
		game.getWindow().getFrame().addKeyListener(player);
		block = new Block(0,0, bWidth, bHeight);
		
		initPatterns();
	}
	
	public void generateMap()
	{
		for(int i = 0; i < tPats-1; i++)
		{
			genMap[i] = genMap[i+1];
		}
		genMap[tPats-1] = rand.nextInt(tPats)+1;
	}
	
	public void update(float scroll)
	{
		this.scroll = (int)-scroll;
		player.setScroll(this.scroll);
		player.update();
	}
	
	public void render(Graphics draw)
	{
		int bCount;
		int xEnd = bWidth * 4;
	
		for(int patID = 0; patID < tPats; patID++)
		{
			xEnd = bWidth * 4;
			
			if(xEnd * 0 + scroll + loop <= -400)
			{
				 loop+= 400;
				 generateMap();
			}
			
			xEnd = xEnd * patID + scroll + loop;
			
			if(genMap[patID] == 1)
			{
				for(bCount = 0; bCount < 4; bCount++)
				{
					block.setX(pattern1[0][bCount] + xEnd);
					block.setY(pattern1[1][bCount]);
					player.collision(pattern1[0][bCount] + xEnd, pattern1[1][bCount]);
					block.render(draw);
				}
			}
			else if(genMap[patID] == 2)
			{
				for(bCount = 0; bCount < 4; bCount++)
				{
					block.setX(pattern2[0][bCount] + xEnd);
					block.setY(pattern2[1][bCount]);
					player.collision(pattern2[0][bCount] + xEnd, pattern2[1][bCount]);
					block.render(draw);
				}
			}
			else if(genMap[patID] == 3)
			{
				for(bCount = 0; bCount < 4; bCount++)
				{
					block.setX(pattern3[0][bCount] + xEnd);
					block.setY(pattern3[1][bCount]);
					player.collision(pattern3[0][bCount] + xEnd, pattern3[1][bCount]);
					block.render(draw);
				}
			}
			else if(genMap[patID] == 4)
			{
				for(bCount = 0; bCount < 4; bCount++)
				{
					block.setX(pattern4[0][bCount] + xEnd);
					block.setY(pattern4[1][bCount]);
					player.collision(pattern4[0][bCount] + xEnd, pattern4[1][bCount]);
					block.render(draw);
				}
			}
		}
		player.render(draw);
	}
	
	public void initPatterns()
	{
		genMap[0] = 1;
		genMap[1] = 2;
		genMap[2] = 3;
		genMap[3] = 4;
		
		//patterns 1
		pattern1[0][0] = 0;
		pattern1[1][0] = height - 20;
		pattern1[0][1] = bWidth;
		pattern1[1][1] = height - 20;
		pattern1[0][2] = bWidth * 2;
		pattern1[1][2] = height - 100;
		pattern1[0][3] = bWidth * 3;
		pattern1[1][3] = height - 160;
		
		//pattern 2
		pattern2[0][0] = 0;
		pattern2[1][0] = height - 40;
		pattern2[0][1] = bWidth;
		pattern2[1][1] = height - 80;
		pattern2[0][2] = bWidth * 2;
		pattern2[1][2] = height + 150;
		pattern2[0][3] = bWidth * 3;
		pattern2[1][3] = height - 20;
		
		//pattern 3
		pattern3[0][0] = 0;
		pattern3[1][0] = height - 40;
		pattern3[0][1] = bWidth;
		pattern3[1][1] = height - 110;
		pattern3[0][2] = bWidth * 2;
		pattern3[1][2] = height - 200;
		pattern3[0][3] = bWidth * 3;
		pattern3[1][3] = height + 50;
		
		//pattern 4
		pattern4[0][0] = 0;
		pattern4[1][0] = height - 120;
		pattern4[0][1] = bWidth;
		pattern4[1][1] = height - 50;
		pattern4[0][2] = bWidth * 2;
		pattern4[1][2] = height - 100;
		pattern4[0][3] = bWidth * 3;
		pattern4[1][3] = height + 50;
	}

	public int getbWidth() {
		return bWidth;
	}

	public int getbHeight() {
		return bHeight;
	}

	public int getScroll() {
		return scroll;
	}

	public void setScroll(int scroll) {
		this.scroll = scroll;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
