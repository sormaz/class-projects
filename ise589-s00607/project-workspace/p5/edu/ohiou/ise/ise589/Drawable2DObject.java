package edu.ohiou.ise.ise589;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.*;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ohiou.labimp.basis.Draw2DPanel;
import edu.ohiou.labimp.basis.DrawString;
import edu.ohiou.labimp.basis.Drawable2D;

public class Drawable2DObject implements Drawable2D {
	Draw2DPanel canvas;

	public void generateImageList() {
		// TODO Auto-generated method stub

	}

	public JPanel getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Shape> getDrawList() {
		LinkedList shapes = new LinkedList();
		shapes.add(new Rectangle2D.Double(0,0,100,300));
		shapes.add(new java.awt.geom.Ellipse2D.Double(0,0,300,100));

		return shapes;
	}

	public LinkedList<Shape> getFillList() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<DrawString> getStringList() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	public Collection giveSelectables() {
		// TODO Auto-generated method stub

		return new LinkedList();
	}

	public void makeDrawSets() {
		Color color = Color.blue;

		Draw2DPanel drawPanel = (Draw2DPanel) canvas;
		drawPanel.addDrawShapes(color, getDrawList());
		drawPanel.addFillShapes(color, getFillList());
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	public void setCanvas(Draw2DPanel canvas) {
		this.canvas = canvas;

	}

	public void setNeedUpdate(boolean needUpdate) {
		// TODO Auto-generated method stub

	}

	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Drawable2DObject dc = new Drawable2DObject();
		JFrame frame = new JFrame();
		Draw2DPanel panel = new Draw2DPanel();
//		Object o = new Object();
//		panel.setTarget(o);
		panel.setTarget(dc);
		frame.getContentPane().add(panel);
		frame.setSize(600,800);
		frame.setVisible(true);
	}
}
