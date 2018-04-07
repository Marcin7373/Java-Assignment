package states;

import java.awt.Graphics;

import entities.Player;
import game_engine.GameLoop;
import game_engine.Map;
import game_engine.SpriteCrop;

public class GameState extends State
{
	private static float scroll = 0, speedUp = 1.01f;//scroll = 3
	private Map map;
	
	public GameState(GameLoop game)
	{
		super(game);
		map = new Map(game);
	}

	public void update()
	{
		if(scroll > 200 && scroll < 840)
		{
			speedUp -= 0.0000343f;
		}
		//System.out.println(speedUp);
		scroll *= speedUp;
		map.update(scroll);
	}
	
	public void render(Graphics draw)
	{
		map.render(draw);
	}
}
