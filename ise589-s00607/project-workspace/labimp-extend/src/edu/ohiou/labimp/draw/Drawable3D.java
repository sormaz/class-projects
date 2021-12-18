package edu.ohiou.labimp.draw;

/**
 * <p>Title:       Drawable3D Interface</p>
 * <p>Description: This interface provides definitions of methods for
 *                 classses whose instances need to be displayed in AnimApplet</p>
 * <p>Copyright:   Dr.D.N.Sormaz, Ohio University Copyright (c) 2002</p>
 * <p>Company:     IMSE Dept, Ohio University</p>
 * @author         Dr.Dusan N.Sormaz,Prashanth Borse,Deepak Pisipati
 * @version 1.0
 */

//Importing classes for gui components
import edu.ohiou.labimp.draw.AnimPanel;
import javax.swing.JPanel;

//Importing classes required for Java 3D methods
import javax.vecmath.Vector3d;
import javax.media.j3d.BranchGroup;

//Importing classes from Implanner
import edu.ohiou.labimp.basis.Viewable;

public interface Drawable3D {
	public BranchGroup createSceneGraph();
	public BranchGroup createAnimationGraph();
	public void setAppearance(boolean appearance);
	public void setUniverseViewPoint(Vector3d inVector);
	public void startAnimation();
	public void stopAnimation();
	public void setCanvas(AnimPanel canvas);
	public JPanel getCanvas();
}