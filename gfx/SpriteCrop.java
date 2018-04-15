package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteCrop     /**  ADD TRY CATCHES FOR NO IMAGE**/
{
	private static BufferedImage backCrop, entityCrop;
	public static BufferedImage block;
	public static BufferedImage playerA[] = new BufferedImage[2];
	public static BufferedImage playerFall[] = new BufferedImage[2];
	public static BufferedImage playerJump[] = new BufferedImage[2];
	public static BufferedImage background[] = new BufferedImage[5];
	private static final int width = 100, height = 100;
	public static BufferedImage one, two, three;
	
	public static void init()
	{	
		backCrop = loadImage("/back_crop.png");
		entityCrop = loadImage("/entity_crop.png");
		block = entityCrop.getSubimage(0, 340, 200, 20);
		playerA[0] = entityCrop.getSubimage(0, 0, width, height);
		playerA[1] = entityCrop.getSubimage(100, 0, width, height);
		playerFall[0] = entityCrop.getSubimage(0, 100, width, height);
		playerFall[1] = entityCrop.getSubimage(100, 100, width, height);
		playerJump[0] = entityCrop.getSubimage(0, 203, width, height+15);
		playerJump[1] = entityCrop.getSubimage(100, 203, width, height+15);
		/*background[0] = backCrop.getSubimage(0, 0+, 2400, );
		background[1] = backCrop.getSubimage(0, , 2400, );
		background[2] = backCrop.getSubimage(0, , 2400, );
		background[3] = backCrop.getSubimage(0, , 2400, );
		background[4] = backCrop.getSubimage(0, , 2400, );*/
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
