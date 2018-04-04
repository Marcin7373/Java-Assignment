package states;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import game_engine.GameLoop;

public abstract class State 
{
	private static State curState = null;
	protected GameLoop game;
	
	public State(GameLoop game)
	{
		this.game = game;
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
}
