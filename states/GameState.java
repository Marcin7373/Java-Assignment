package states;

import java.awt.Graphics;

import entities.Player;
import game_engine.GameLoop;
import game_engine.Map;
import game_engine.SpriteCrop;

public class GameState extends State
{
	private static float scroll = 3, speedUp = 1.01f;//scroll = 3
	private Player player;
	private Map map;
	
	public GameState(GameLoop game)
	{
		super(game);
		map = new Map(game);
		player = new Player(map, 100, 100, 50, 50);
		game.getWindow().getFrame().addKeyListener(player);
		
	}

	public void update()
	{
		if(scroll > 200 && scroll < 840)
		{
			speedUp -= 0.0000343f;
		}
		System.out.println(speedUp);
		scroll *= speedUp;
		player.update();
		map.update(scroll);
	}
	
	public void render(Graphics draw)
	{
		player.render(draw);
		map.render(draw);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
