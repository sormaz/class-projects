package edu.ohiou.labimp.draw;
/**
 * Title:        DrawableWF Interface <p>
 * Description:  Interface for drawing wireframes. <p>
 * Copyright:    Copyright (c) 2001 <p>
 * Company:      Ohio University <p>
 * @author  Jaikumar Arumugam + Dusan N. Sormaz
 * @version 1.0
 */
import java.awt.Graphics;
import edu.ohiou.labimp.basis.Viewable;
import edu.ohiou.labimp.draw.DrawWFPanel;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.*;
import edu.ohiou.labimp.basis.*;
import java.util.LinkedList;

/**
 *
 *  Interface for displaying objects in 3d view space as wireframe
 *  object is displayed inside DrawWFPanel, with options to adjust view point
 *
 */

public interface DrawableWF {
	
	  public static final boolean MODIFY_VIEW = false;
	  public static final boolean MODIFY_TARGET = true;



	public void paintComponent(Graphics g);
	public void paintComponent(Graphics2D g);
	public void paintComponent(Graphics2D g, DrawWFPanel canvas);
	public void setCanvas(DrawWFPanel canvas);
	public JPanel getCanvas();
  public Color getColor();
  public void setColor (Color c);
	public void setNeedUpdate(boolean needUpdate);
	public void repaint();
	//  public JPanel getCanvas();
	// return a LinkedList of 2D shapes representing target.
	public LinkedList getShapeList(DrawWFPanel canvas);
  public void makeShapeSets(DrawWFPanel canvas);
	public LinkedList getPointSet();
}
