package game_engine;

import java.awt.image.BufferedImage;

public class SpriteCrop
{
	private static BufferedImage sheet = ImageLoader.loadImage("/1.jpg");
	public static BufferedImage block = ImageLoader.loadImage("/1.jpg");
	public static BufferedImage player = ImageLoader.loadImage("/player3.png");
	private static final int width = 100, height = 100;
	public static BufferedImage one, two;
	
	public static void init()
	{	
		one = sheet.getSubimage(0, 0, width, height);
		two = sheet.getSubimage(width, 0, width, height);
	}
}
