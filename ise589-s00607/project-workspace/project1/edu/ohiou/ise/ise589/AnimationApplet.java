package edu.ohiou.ise.ise589;

import java.awt.Graphics;

import javax.swing.JApplet;
import static java.lang.Math.*;

public class AnimationApplet extends JApplet implements Runnable {
	int xValue = 0, yValue= 0;
	double alpha = 0.0, delta = 1, radius = 50;
	Thread runner =  null;
	public AnimationApplet () {
		runner = new Thread (this);
	}
	
	public void start () {
		xValue=0;
		yValue=0;
		runner.start();
	}
	
	public void run () {
		while (true) {
		try {
			runner.sleep(100);
			xValue += 10;
			yValue += 5;
			alpha += 1;
		
				radius+= 0.1 * alpha/360;
			
			repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void paint (Graphics g) {
//		g.clearRect(0,0,this.getWidth(), this.getHeight());
//		g.drawLine(40,30,xValue,yValue); 
		g.drawLine((int) (100 + radius * cos(alpha* PI/180.)), 
				(int) (100 + radius * sin(alpha* PI/180.)),
				(int) (100 + radius * cos(alpha* PI/180.)), 
				(int) (100 + radius * sin(alpha* PI/180.)));
	}
}

