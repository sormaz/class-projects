package package2;

import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.*;
import java.awt.*;

import edu.ohiou.labimp.basis.ViewObject;

public class DrawingViewer extends ViewObject{
	ArrayList<DrawObject> myCollection = new ArrayList<DrawObject>();
	
	public static void main (String[] args){
		DrawingViewer viewer=new DrawingViewer();
		viewer.display();
	}
	
	public void add(DrawObject drawObj) {
		myCollection.add(drawObj);
	}

	public void delete(int index) {
		myCollection.remove(index);
	}

	public void list() {
		System.out.println(toString());
		ListIterator<DrawObject> iter = myCollection.listIterator();
		while (iter.hasNext()) {
			System.out.println((String) iter.next().toString());
		}
	}

	public DrawObject get(int index) {
		return myCollection.get(index);
	}

	public String toString() {
		return "The collection contains: " + myCollection.size() + " elements";
	}
	
	public void init(){
		JList list=new JList(myCollection.toArray());
		JTextArea textArea=new JTextArea();
		
		JScrollPane listScrollPane=new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollPane textScrollPane=new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JSplitPane drawViewPanel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,listScrollPane, textScrollPane);
		drawViewPanel.setResizeWeight(0.5);
		drawViewPanel.setOneTouchExpandable(true);
		drawViewPanel.setContinuousLayout(true);
		panel=new JPanel(new BorderLayout());
		panel.add(drawViewPanel, BorderLayout.CENTER);
		panel.setVisible(true);
	}
}

