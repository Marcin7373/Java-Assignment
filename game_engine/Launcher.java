package game_engine;

public class Launcher 
{
	public static void main(String[] args) 
	{
		GameLoop game = new GameLoop();//creates state objects, window and crops sprites
		game.run();    //loop that runs the whole game
	}

}
