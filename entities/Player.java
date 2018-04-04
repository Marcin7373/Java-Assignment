package entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game_engine.GameLoop;
import game_engine.SpriteCrop;

public class Player extends Entity implements KeyListener
{
	private boolean left, right, up, down;
	private GameLoop game;
	public Player(GameLoop game, float x, float y) 
	{
		super(x, y);
		this.game = game;
	}

	@Override
	public void update() 
	{
		if(up == true) y -= 5;
		if(down == true) y += 5;
		if(left == true) x -= 5;
		if(right == true) x += 5;
	}

	public void render(Graphics draw) 
	{
		draw.drawImage(SpriteCrop.one, (int)x, (int)y, null);
	}

	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = true;
		if(event.getKeyCode() == KeyEvent.VK_S) down = true;
		if(event.getKeyCode() == KeyEvent.VK_A) left = true;
		if(event.getKeyCode() == KeyEvent.VK_D) right = true;
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) up = false;
		if(event.getKeyCode() == KeyEvent.VK_S) down = false;
		if(event.getKeyCode() == KeyEvent.VK_A) left = false;
		if(event.getKeyCode() == KeyEvent.VK_D) right = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}
