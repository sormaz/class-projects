
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package edu.ohiou.labimp.basis;
import java.awt.*;
import javax.swing.*;

/**
 * This interface specifies necessary methods that its implementor
 * need to implement in order to allow display inside GUIApplet
 */
public interface Viewable {



  public JPanel getPanel();
  public int getPanelLocation();
  public int getPanelOrientation();
//  public void setTarget (Viewable target);
//  public Viewable getTarget ();
  public void setApplet (GUIApplet applet);
  public GUIApplet getApplet ();
  public void setPanel (JPanel inPanel);
  public JPanel makePanel ();
  public void addPanel ();
  public Color getColor();
  public void setColor (Color color);
  public void init();
  public void display ();
  public void display (String inTitle);
  public void display (String inTitle, Dimension inSize);
  public void display (String inTitle, int inWidth, int inHeight);
  public void display (String inTitle, Dimension inSize, int appletClosing);
  public void toggleVisible ();
  public Dimension getAppletSize ();
  public Viewable getGuiObject ();
  public void setGuiObject (Viewable guiObject);
  
  public String toToolTipString();
}