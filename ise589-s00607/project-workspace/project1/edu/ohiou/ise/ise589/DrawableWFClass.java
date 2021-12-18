package edu.ohiou.ise.ise589;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ohiou.labimp.basis.Draw2DPanel;
import edu.ohiou.labimp.draw.DrawWFPanel;
import edu.ohiou.labimp.draw.DrawableWF;

public class DrawableWFClass implements DrawableWF {

	public JPanel getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList getPointSet() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	public LinkedList getShapeList(DrawWFPanel canvas) {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	public void makeShapeSets(DrawWFPanel canvas) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics2D g, DrawWFPanel canvas) {
		// TODO Auto-generated method stub

	}

	public void repaint() {
		// TODO Auto-generated method stub

	}

	public void setCanvas(DrawWFPanel canvas) {
		// TODO Auto-generated method stub

	}

	public void setColor(Color c) {
		// TODO Auto-generated method stub

	}

	public void setNeedUpdate(boolean needUpdate) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawableWFClass dwf = new DrawableWFClass();
		JFrame frame = new JFrame();
		DrawWFPanel panel = new DrawWFPanel();
		panel.setTarget(dwf);
		frame.getContentPane().add(panel);
		frame.setSize(600,800);
		frame.setVisible(true);

	}

}
