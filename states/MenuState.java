package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game_engine.GameLoop;

public class MenuState extends State implements KeyListener
{
	private float distance, highScore;
	private boolean start = false;
	
	public MenuState(GameLoop game)
	{
		super(game);
			
	}
	
	@Override
	public void update() 
	{
		distance = game.getDistance();
		distance = distance/100;
		if(highScore < distance)
		{
			highScore = distance;
		}
	}

	@Override
	public void render(Graphics draw) 
	{
		Graphics2D draw2 = (Graphics2D)draw;
		draw2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        draw2.setFont(new Font("Dialog", Font.BOLD, 70));
        //draw.setColor(Color.GREEN);
        draw2.drawString("Distance: "+distance+"   Highscore: "+highScore, 70, 320); 
        //dialog, helvetica
      //--- Get an array of font names (smaller than the number of fonts)
        //String[] fontNames = ge.getAvailableFontFamilyNames();
	}	

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_ENTER) start = true; 
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
}
