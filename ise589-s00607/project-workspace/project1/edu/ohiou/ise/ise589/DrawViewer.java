package edu.ohiou.ise.ise589;

import java.awt.Image;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.*;
import edu.ohiou.labimp.basis.*;

public class DrawViewer extends ViewObject {
	
	 List<DrawObject> objects;
	
	public DrawViewer () {
		objects = new ArrayList<DrawObject> ();	
	}
	
	public boolean addObject (DrawObject o) {
		
		return objects.add(o);
		
	}
	
	public void deleteObject (DrawObject o) {
		
	}
	
	public void deleteObject (int index) {
		objects.remove(index);
		
	}
	
	public DrawObject findObject (int index) {
		return objects.get(index);
	}
	
	public int indexOf (DrawObject o) {
		return objects.indexOf(o);
	}
	
	public String toString() {
	 return "DrawViewer contains " + objects.size() + " objects";
	}
	
	public void listObjects () {
		System.out.println(toString());
		int i = 1;
		for ( Iterator itr = objects.iterator(); itr.hasNext();) {
			System.out.println("" + i++ + ": " +itr.next().toString());
		}
	}
	
	public void init () {
		panel = new DrawViewPanel(this);
	}
	
	public static void main (String [] args) {
		DrawViewer v = new DrawViewer();
		v.display();
	}
}

class DrawViewPanel extends JPanel {
	DrawViewer viewer; 
	
	public DrawViewPanel (DrawViewer viewer) {
		this.viewer = viewer;
		setLayout(new BorderLayout());
		JSplitPane splitPane= new JSplitPane();
		JList jList = new JList(viewer.objects.toArray());
		splitPane.setLeftComponent(new JScrollPane (jList));
		splitPane.setRightComponent(new Draw2DPanel());
		JToolBar  toolBar = new JToolBar();
		toolBar.add(new JButton("New"));
		add (splitPane, BorderLayout.CENTER);
		add (toolBar, BorderLayout.SOUTH);
	}
	
}
