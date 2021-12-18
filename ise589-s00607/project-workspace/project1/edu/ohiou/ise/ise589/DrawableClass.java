/**
 * 
 */
package edu.ohiou.ise.ise589;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.*;

import edu.ohiou.labimp.basis.*;
import edu.ohiou.labimp.basis.Drawable2D;

/**
 * @author Dusan Sormaz
 *
 */
public class DrawableClass implements Drawable2D {
	Draw2DPanel canvas;

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#generateImageList()
	 */
	public void generateImageList() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#getCanvas()
	 */
	public JPanel getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#getDrawList()
	 */
	public LinkedList getDrawList() {
		LinkedList shapes = new LinkedList();
		shapes.add(new Line2D.Double(0,0,100,300));
		// TODO Auto-generated method stub
		return shapes;
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#getFillList()
	 */
	public LinkedList getFillList() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#getPosition()
	 */
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#getStringList()
	 */
	public LinkedList getStringList() {
		LinkedList list = new LinkedList();
		// TODO Auto-generated method stub
		list.add(new DrawString("DSormaz", 0 ,0));
		return list;
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#giveSelectables()
	 */
	public Collection giveSelectables() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#makeDrawSets()
	 */
	public void makeDrawSets() {
		Color color = Color.black;
	 
	      Draw2DPanel drawPanel = (Draw2DPanel) canvas;
	      drawPanel.addDrawShapes(color, getDrawList());
	      drawPanel.addFillShapes(color, getFillList());
	    

	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#paintComponent(java.awt.Graphics2D)
	 */
	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#setCanvas(edu.ohiou.labimp.basis.Draw2DPanel)
	 */
	public void setCanvas(Draw2DPanel canvas) {
		// TODO Auto-generated method stub
		this.canvas = canvas;

	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#setNeedUpdate(boolean)
	 */
	public void setNeedUpdate(boolean needUpdate) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.ohiou.labimp.basis.Drawable2D#setPosition(java.awt.geom.Point2D)
	 */
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DrawableClass dc = new DrawableClass();
		JFrame frame = new JFrame();
		Draw2DPanel panel = new Draw2DPanel();
		panel.setTarget(dc);
		frame.getContentPane().add(panel);
		frame.setSize(600,800);
		frame.setVisible(true);
	}

}
