package states;

import java.awt.Graphics;

import game_engine.GameLoop;
import game_engine.Map;

public abstract class State 
{
	private static State curState = null;
	protected GameLoop game;
	//private Map map;
	
	public State(GameLoop game)
	{
		this.game = game;
		//map = new Map(game);
	}
	
	public static void setState(State state)
	{
		curState = state;
	}
	
	public static State getState()
	{
		return curState;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics draw);

	public Map getMap() { //needed to make one for gamestate
		return null;
	}

	public boolean isStart() {
		return false;
	}

	public void setStart(boolean b) {
	}
}
