package edu.ohiou.ise.ise589;

import java.util.ArrayList;

public class DViewer {
	
	ArrayList objects;
	
	public void addObject (DrawObject o) {
		objects.add(o);
	}
	
	public void deleteObject(DrawObject o) {
		objects.remove(o);
	}
	
	public void deleteObject (int n) {
		objects.remove(n);
	}

}
