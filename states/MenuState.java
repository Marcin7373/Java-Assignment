package states;

import java.awt.Graphics;

import game_engine.GameLoop;

public class MenuState extends State
{
	public MenuState(GameLoop game)
	{
		
		super(game);
		
	}
	
	public void update()
	{
		game.setStateF(false);
		game.setRunning(false);
	}
	
	public void render(Graphics draw)
	{
		
	}
}
