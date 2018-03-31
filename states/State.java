package states;

import java.awt.Graphics;

public abstract class State 
{
	private static State curState = null;
	
	public static void setState(State state)
	{
		curState = state;
	}
	
	public static State getState()
	{
		return curState;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics draw);
}
