package states;

import java.awt.Graphics;

import game_engine.GameLoop;
import game_engine.Map;

public class GameState extends State
{   //scroll = the amount by which the everything moves across the screen
	//speedUp = multiplier increasing scroll, speeding up the level
	private float scroll = 3, speedUp = 1.01f;//numbers gotten after experimenting
	private Map map;                          //so keep as is
	
	public GameState(GameLoop game)
	{
		super(game);        //game = access to center of game
		map = new Map(game);//for classes too far away
	}                       //map = where everything is rendered e.g level, player

	public void update()
	{   //if to decrease the exponential speedUp at  points
		if(scroll > 100 && scroll < 640) //needed to get speedUp close to 1
		{                                //speedUp < 1 = scroll backwards
			speedUp -= 0.0000247f; 
		}
		else if(scroll > 641 && scroll < 1200)
		{
			speedUp -= 0.000005;
		}

		if(scroll <= 11500)    //beyond 13000 map goes faster then the player can move
		{
			scroll *= speedUp;    //increases the scroll exponentially by speedUp
		}                         //so speedUp decreased and then cut off
		else 
		{
			scroll += 15.4;//at 11500 scroll the increment is 15.4 roughly
		}                  //constant scroll speed
		
		map.update(scroll);
	}
	
	public void render(Graphics draw)
	{
		map.render(draw);
	}
	
	/***Getters and Setters***/
	@Override
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
