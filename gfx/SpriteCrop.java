package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteCrop     
{
	private static BufferedImage backCrop, entityCrop;
	private static final int width = 100, height = 100;      //width + height of player in image
	public static BufferedImage block;
	public static BufferedImage player[] = new BufferedImage[2];      //player when not in jump or land
	public static BufferedImage playerLand[] = new BufferedImage[2];  //player land animation
	public static BufferedImage playerJump[] = new BufferedImage[2];  //player jump animation
	public static BufferedImage background[] = new BufferedImage[5];  //background crops
	
	public static void init()
	{	                     //path to img included actual path = "/img/example.png"
		backCrop = loadImage("/img/back_crop.png");
		entityCrop = loadImage("/img/entity_crop.png");
		/***Cropping all images to be used***/            
		block = entityCrop.getSubimage(0, 340, 200, 20);             //numbers for all gotten            
		player[0] = entityCrop.getSubimage(0, 0, width, height);     //while creating the images
		player[1] = entityCrop.getSubimage(100, 0, width, height);
		playerLand[0] = entityCrop.getSubimage(0, 100, width, height);
		playerLand[1] = entityCrop.getSubimage(100, 100, width, height);
		playerJump[0] = entityCrop.getSubimage(0, 203, width, height+15);
		playerJump[1] = entityCrop.getSubimage(100, 203, width, height+15);
		
		background[0] = backCrop.getSubimage(0, 2004, 2400, 102);  
		background[1] = backCrop.getSubimage(0, 1840, 2400, 153);
		background[2] = backCrop.getSubimage(0, 948, 2400, 893);
		background[3] = backCrop.getSubimage(0, 313, 2400, 636);
		background[4] = backCrop.getSubimage(0, 40, 2400, 274);
	} 
	                                 
	public static BufferedImage loadImage(String path)     //loads images in init
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
