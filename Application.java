package sorting;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Application extends JFrame{
	
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true); 
		setResizable(true);
		Animation animation = new Animation();
		getContentPane().add(animation, BorderLayout.CENTER);
		ButtonPanel panel = new ButtonPanel(animation);
		getContentPane().add(panel, BorderLayout.PAGE_START);
		pack();
		setMinimumSize(new Dimension(getWidth(),getHeight()));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		animation.start();
	}
		
	public static void main(String[] args) {
		new Application();
	}
}
