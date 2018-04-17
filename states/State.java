package states;

import java.awt.Graphics;

import game_engine.GameLoop;
import game_engine.Map;

public abstract class State 
{
	private static State curState = null;
	protected GameLoop game;             //all having game object useful
	
	public State(GameLoop game)
	{
		this.game = game;
	}
	
	public static void setState(State state)//changing state
	{
		curState = state;
	}
	
	public static State getState()
	{
		return curState;
	}
	
	public abstract void update(); //all need a render and update
	
	public abstract void render(Graphics draw);

	/***Getters and Setters***/
	public Map getMap() { //needed to make one for game state
		return null;
	}
      //needed for menu state
	public boolean isStart() {   
		return false;
	}

	public void setStart(boolean b) {
	}
}
