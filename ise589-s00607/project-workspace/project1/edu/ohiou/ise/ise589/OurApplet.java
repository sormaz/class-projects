package edu.ohiou.ise.ise589;

import javax.swing.*;
import java.awt.*;

public class OurApplet extends JApplet {
	int xValue=20, yValue=200;

	
	public void init () {
//		this.getContentPane().add (new JLabel("text"));
		xValue = Integer.parseInt(this.getParameter("X"));
		yValue = Integer.parseInt(this.getParameter("Y"));
		
	}
	
	public void paint (Graphics g) {
		g.drawLine(40,30,xValue,yValue);
	}
	
	public static void main (String [] args) {
		OurApplet ourApplet = new OurApplet();
		JFrame frame= new JFrame();
		frame.setSize(300, 300);
		JButton button = new JButton("click");
		JToolBar bar = new JToolBar();
		bar.add(button);
		frame.setTitle("Line application");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(ourApplet, BorderLayout.CENTER);
		frame.getContentPane().add(bar, BorderLayout.WEST);
		frame.getContentPane().add(new JLabel("ise589"), BorderLayout.EAST);
		frame.setVisible(true);
	}
}
