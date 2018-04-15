package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteCrop
{
	private static BufferedImage background;
	public static BufferedImage block;
	public static BufferedImage playerA[] = new BufferedImage[3];
	public static BufferedImage playerFall[] = new BufferedImage[2];
	public static BufferedImage player;
	private static final int width = 100, height = 100;
	public static BufferedImage one, two, three;
	
	public static void init()
	{	
		background = loadImage("/back_crop.png");
		block = loadImage("/1.jpg");
		player = loadImage("/player3.png");
		playerA[0] = loadImage("/player1.png");
		playerA[1] = loadImage("/player2.png");
		playerA[2] = loadImage("/player3.png");
		playerFall[0] = loadImage("/player_land.png");
		playerFall[1] = loadImage("/player_land1.png");
		
		one = background.getSubimage(0, 0, 1200*2, 1000);
		two = background.getSubimage(0, 500*2, 1200*2, 1000);
		three = background.getSubimage(0, 3000, 1200*2, 1000);
	}
	
	public static BufferedImage loadImage(String path)
	{
		try 
		{
			return ImageIO.read(SpriteCrop.class.getResource(path));
		}catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
