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
	private int width, height;         
	
	public Window(int width, int height) 
	{	
		this.width = width;
		this.height = height;
		setFrame(new JFrame(title));
		getFrame().setSize(width, height);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setResizable(false);
		getFrame().setLocationRelativeTo(null);
		getFrame().setVisible(true);
		
		setCanvas(new Canvas());
		getCanvas().setPreferredSize(new Dimension(width, height));
		getCanvas().setMinimumSize(new Dimension(width, height));
		getCanvas().setMaximumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		getFrame().add(getCanvas());
		getFrame().pack();              //makes all canvas visible in frame
	}

	
	//getters and setters
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
