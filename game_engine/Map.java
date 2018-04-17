package game_engine;

import java.awt.Graphics;
import java.util.Random;

import entities.Block;
import entities.Player;
import gfx.Background;

public class Map 
{
	private int width, height;
	private final int totalPats = 5, blockNo = 4;//no. of patterns of block, no. of blocks in each pattern
	private final int bWidth = 100, bHeight = 10;   //block width, height
	private int scroll, loop = 0;                   //loop = adds to x value to make blocks loop
	private Block block;
	private int[] genMap = new int[totalPats];          //stores order the patterns are in
	private int[][] pattern1 = new int[2][blockNo];     //stores block coordinates
	private int[][] pattern2 = new int[2][blockNo];
	private int[][] pattern3 = new int[2][blockNo];
	private int[][] pattern4 = new int[2][blockNo];
	private int[][] pattern5 = new int[2][blockNo];
	private Player player;
	private Background background;
	Random rand = new Random();                     //for generating pattern orders
	
	public Map(GameLoop game)
	{
		width = game.getWidth();
		height = game.getHeight();
		background = new Background(width, height); 
		player = new Player(120, height-71, 50, 50, width, height);//places player on platform
		player.setOffset(-scroll);                         //player needs to scroll with level
		game.getWindow().getFrame().addKeyListener(player);//player keyboard input
		block = new Block(0,0, bWidth, bHeight);
		
		initPatterns();           //sets up initial map before generation 
	}                             //then sets up all pattern coordinates
	
	public void generateMap()
	{
		for(int i = 0; i < totalPats-1; i++)       //adds a new random pattern no.
		{
			genMap[i] = genMap[i+1];
		}
		genMap[totalPats-1] = rand.nextInt(totalPats)+1;//add random pattern to the end
	}
	
	public void update(float scroll)
	{
		this.scroll = (int)-scroll;   //"-" so scroll can be just added without worry for signs
		background.update(this.scroll);
		player.setScroll(this.scroll);
		player.update();
	}
	
	public void render(Graphics draw)
	{
		background.render(draw, scroll);    //background render before everything else
		
		int bCount;                         //counts through blocks in each pattern
		int xEnd = bWidth * blockNo;        //allows for rendering blocks back to back
		
		//loops through the randomly generated patterns
		for(int patID = 0; patID < totalPats; patID++)  
		{
			if(scroll + loop <= -blockNo*bWidth)//if pattern off screen
			{
				 loop+= blockNo*bWidth;         //next pattern coordinate
				 generateMap();                 //generate next pattern randomly
			}
			
			xEnd = bWidth * blockNo;            //next block location
			xEnd = xEnd * patID + scroll + loop;//next pattern coordinate 
			
			if(genMap[patID] == 1)              //looking through patterns generated
			{
				for(bCount = 0; bCount < blockNo; bCount++)  //rendering generated patterns
				{
					block.setX(pattern1[0][bCount] + xEnd);  //one block is used
					block.setY(pattern1[1][bCount]);         //so its location is reset every time
					player.collision(pattern1[0][bCount] + xEnd, pattern1[1][bCount]);//check for collisions with player
					block.render(draw);
				}
			}
			else if(genMap[patID] == 2)         //repeat for all other possible patterns
			{
				for(bCount = 0; bCount < blockNo; bCount++)
				{
					block.setX(pattern2[0][bCount] + xEnd);
					block.setY(pattern2[1][bCount]);
					player.collision(pattern2[0][bCount] + xEnd, pattern2[1][bCount]);
					block.render(draw);
				}
			}
			else if(genMap[patID] == 3)
			{
				for(bCount = 0; bCount < blockNo; bCount++)
				{
					block.setX(pattern3[0][bCount] + xEnd);
					block.setY(pattern3[1][bCount]);
					player.collision(pattern3[0][bCount] + xEnd, pattern3[1][bCount]);
					block.render(draw);
				}
			}
			else if(genMap[patID] == 4)
			{
				for(bCount = 0; bCount < blockNo; bCount++)
				{
					block.setX(pattern4[0][bCount] + xEnd);
					block.setY(pattern4[1][bCount]);
					player.collision(pattern4[0][bCount] + xEnd, pattern4[1][bCount]);
					block.render(draw);
				}
			}
			else if(genMap[patID] == 5)
			{
				for(bCount = 0; bCount < blockNo; bCount++)
				{
					block.setX(pattern5[0][bCount] + xEnd);
					block.setY(pattern5[1][bCount]);
					player.collision(pattern5[0][bCount] + xEnd, pattern5[1][bCount]);
					block.render(draw);
				}
			}//add new pattern here
		}
		player.render(draw);
	}
	
	public void initPatterns()
	{   //initial patterns, those will always be the first 5 patterns
		genMap[0] = 1;
		genMap[1] = 2;
		genMap[2] = 3;
		genMap[3] = 4;
		genMap[4] = 5;
		
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
		pattern2[1][0] = height - 90;
		pattern2[0][1] = bWidth;
		pattern2[1][1] = height - 50;
		pattern2[0][2] = bWidth * 2;
		pattern2[1][2] = height - 550;
		pattern2[0][3] = bWidth * 3;
		pattern2[1][3] = height - 140;
		
		//pattern 3
		pattern3[0][0] = 0;
		pattern3[1][0] = height - 70;
		pattern3[0][1] = bWidth;
		pattern3[1][1] = height - 130;
		pattern3[0][2] = bWidth * 2;
		pattern3[1][2] = height - 210;
		pattern3[0][3] = bWidth * 3;
		pattern3[1][3] = height - 550;
		
		//pattern 4
		pattern4[0][0] = 0;
		pattern4[1][0] = height - 80;
		pattern4[0][1] = bWidth;
		pattern4[1][1] = height - 30;
		pattern4[0][2] = bWidth * 2;
		pattern4[1][2] = height - 550;
		pattern4[0][3] = bWidth * 3;
		pattern4[1][3] = height - 250;
		
		//pattern 5
		pattern5[0][0] = 0;
		pattern5[1][0] = height - 180;
		pattern5[0][1] = bWidth;
		pattern5[1][1] = height - 50;
		pattern5[0][2] = bWidth * 2;
		pattern5[1][2] = height - 550;
		pattern5[0][3] = bWidth * 3;
		pattern5[1][3] = height - 210;
	}

	/***Getters and Setters***/
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
