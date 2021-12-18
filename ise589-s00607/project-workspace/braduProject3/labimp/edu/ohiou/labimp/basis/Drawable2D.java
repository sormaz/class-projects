package edu.ohiou.labimp.basis;


import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JPanel;

/**
 * Title:        Generic classes for manufacturing planning
 * Description:  This project implements general classes for imtelligent manufacturing planning. These are:
 * ImpObject - umbrella class fro all objects
 * MrgPartModel - general part object data
 * Viewable - interface to display objects in applet
 * GUIApplet - applet that utilizes viewable interface
 * Copyright:    Copyright (c) 2001
 * Company:      Ohio University
 * @author Dusan Sormaz
 * @version 1.0
 */

public interface Drawable2D   {
	  // public constants

	  public static final boolean MODIFY_VIEW = false;
	  public static final boolean MODIFY_TARGET = true;

  public void setCanvas (Draw2DPanel canvas);
  public JPanel getCanvas ();

  public void paintComponent (Graphics g);
  public void paintComponent (Graphics2D g);
  public void makeDrawSets();
  public LinkedList<Shape> getDrawList();
  public LinkedList<Shape> getFillList ();
  public LinkedList<DrawString> getStringList ();
  /**
   * method that generates a hashmap of images.
   * This method places them into a hashMap of Draw2DPanel. This method 
   * should call addImage from Draw2DPanel and supply image and its 
   * affine transformion target space. Image dimensions in pixels are 
   * dimensions in target space so scaling may be necessary before 
   * submitting images to Draw2DPanel
   */
  public void generateImageList ();
  public void setNeedUpdate (boolean needUpdate);
  public Collection giveSelectables();
  public Point2D getPosition();
  public void setPosition(Point2D point);
}
