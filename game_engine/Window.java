package game_engine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Canvas canvas;
	private String title = "Jump Game-temporary title until I think of something better";//initial title      
	
	public Window(int width, int height) 
	{	
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//X = close window
		frame.setResizable(false);                //game depends on a set size so cant allow resizing                          
		frame.setLocationRelativeTo(null);        //screen  = center
		frame.setVisible(true);                   
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));//makes sure size cant be changed
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setFocusable(false);                           //prevents canvas focus instead of frame                         
		frame.add(canvas);           
		frame.pack();                                         //makes all canvas visible in frame
	}
	
	/***Getters and Setters***/
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
