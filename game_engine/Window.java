package game_engine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Canvas canvas;
	private String title = "jump game";     //initial title
	private int width = 400, height = 300;  //
	
	public Window() 
	{	
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		setCanvas(new Canvas());
		getCanvas().setPreferredSize(new Dimension(width, height));
		getCanvas().setMinimumSize(new Dimension(width, height));
		getCanvas().setMaximumSize(new Dimension(width, height));
		frame.add(getCanvas());
		frame.pack();              //makes all canvas visible in frame
		
	}

	
	//getters and setters
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}
