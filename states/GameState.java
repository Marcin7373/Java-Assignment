package states;

import java.awt.Graphics;

import entities.Player;
import game_engine.GameLoop;
import game_engine.SpriteCrop;

public class GameState extends State
{
	public int i = 0;
	private Player player;
	
	public GameState(GameLoop game)
	{
		super(game);
		setPlayer(new Player(game, 100, 100));
		game.getWindow().getFrame().addKeyListener(player);
	}

	public void update()
	{
		getPlayer().update();
	}
	
	public void render(Graphics draw)
	{
		getPlayer().render(draw);
		
		if(i == 0)
		{
		draw.drawImage(SpriteCrop.one, 0, 0, null);   //null = image observer not needed
		i = 1;
		}
		else {
		draw.drawImage(SpriteCrop.two, 0, 0, null);
		i = 0;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
