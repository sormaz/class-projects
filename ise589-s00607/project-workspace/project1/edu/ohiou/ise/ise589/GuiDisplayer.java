package edu.ohiou.ise.ise589;

import javax.swing.*;
import java.awt.*;

public class GuiDisplayer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame  jFrame = new JFrame();
//		jFrame.show();
			jFrame.setSize(600, 300);
			jFrame.setLocation(100,150);
			Container c = jFrame.getContentPane();
			JPanel panel = new JPanel();
			panel.add(new JLabel("Hello, World"));
			c.add(panel);
			jFrame.setVisible(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		jFrame.setTitle("ise 589 lecturee");

		// TODO Auto-generated method stub

	}

}
