package edu.ohiou.ise.ise589;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Vector3d;

import edu.ohiou.labimp.draw.AnimPanel;
import edu.ohiou.labimp.draw.Drawable3D;

import com.sun.j3d.utils.geometry.ColorCube;


public class Drawable3DClass implements Drawable3D {

	BranchGroup objRoot;
	AnimPanel canvas;

	public BranchGroup createAnimationGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	public BranchGroup createSceneGraph() {
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Create the transform group node and initialize it to the
		// identity. Add it to the root of the subgraph.
		TransformGroup objSpin = new TransformGroup();
		objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRoot.addChild(objSpin);

		// Create a simple shape leaf node, add it to the scene graph.
		// ColorCube is a Convenience Utility class
		objSpin.addChild(new ColorCube(1 * 0.1));

		// Create a new Behavior object that will perform the desired
		// operation on the specified transform object and add it into
		// the scene graph.
		Alpha rotationAlpha = new Alpha(-1, 4000);

		RotationInterpolator rotator = new RotationInterpolator(
				rotationAlpha,
				objSpin);

		// a bounding sphere specifies a region a behavior is active
		// create a sphere centered at the origin with radius of 100
		BoundingSphere bounds = new BoundingSphere();
		rotator.setSchedulingBounds(bounds);
		objSpin.addChild(rotator);
		return objRoot;
	}

	public JPanel getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAppearance(boolean appearance) {
		// TODO Auto-generated method stub

	}

	public void setCanvas(AnimPanel canvas) {
		this.canvas = canvas;
	}

	public void setUniverseViewPoint(Vector3d inVector) {
		// TODO Auto-generated method stub

	}

	public void startAnimation() {
		// TODO Auto-generated method stub

	}

	public void stopAnimation() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Drawable3DClass dc = new Drawable3DClass();
		JFrame frame = new JFrame();
		AnimPanel panel = new AnimPanel();
		panel.setTarget(dc);
		panel.init();
		frame.getContentPane().add(panel);
		frame.setSize(600,800);
		frame.setVisible(true);

	}

}
