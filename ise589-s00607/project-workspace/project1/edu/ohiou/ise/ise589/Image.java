/**
 * 
 */
package edu.ohiou.ise.ise589;

import java.awt.geom.Point2D;
import java.io.*;

import javax.swing.JPanel;

/**
 * @author Dusan Sormaz
 *
 */
public class Image extends DrawObject {
	
	private File file;
	private double scale;

	/**
	 * @param point
	 */
	public Image(Point2D.Double point, double s, String fileName) 
								throws DrawObjectException {
		this (point.x, point.y, s, fileName);
	}

	/**
	 * @param x
	 * @param y
	 */
	public Image(double x, double y, File f) {
		super(x, y);
	}
	
	public Image (double x, double y, double s, String fileName) 
								throws DrawObjectException {
		super (x,y);
		File f = new File(fileName);
		if (!f.exists()) {
			throw new DrawObjectException ("File with that name does not exist.");
		}
		file = f;
		scale = s;
	}

	/* (non-Javadoc)
	 * @see p2.DrawObject#printout()
	 */
	@Override
	public void printout() {
		System.out.println("in image printout" + file);
	}
	public JPanel getPanel () {
		return new JPanel();
	}


}
