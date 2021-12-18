/**
 * 
 */
package edu.ohiou.labimp.graphmodel.gui;

import java.awt.*;
import java.util.*;
import java.util.List;


import edu.ohiou.labimp.basis.*;
import edu.ohiou.labimp.graphmodel.*;

/**
 * Provides the default behavior for displaying the graph model
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Ohio University</p>
 * @author Chandrasekhar Ganduri
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GraphRenderer extends ViewObject {
	
	protected List<GraphVertex> vertexList = new ArrayList<GraphVertex>();
	protected Map<Arc,GraphEdge> edgeMap = new HashMap<Arc,GraphEdge>();
	protected GraphLayouter layouter;
	protected GraphModel graphModel;
	protected GraphCanvas canvas;
	protected boolean default_behavior = false;
	
	public GraphRenderer()
	{
		
	}
	public GraphRenderer(GraphModel model, boolean default_behavior)
	{
		this.graphModel = model;
		this.default_behavior = default_behavior;
		layouter = new RandomLayouter(this);
		initialize();
	}
	
	public void initialize()
	{
		Iterator<GraphNode> itr = graphModel.getNodes();
		GraphNode tempNode;
		GraphVertex tempVertex;
		while(itr.hasNext())
		{
			tempNode = itr.next();
			tempVertex = new GraphVertex(tempNode, this);
			vertexList.add(tempVertex);
		}
		layouter.makeLayout();
		Iterator<UndirectedArc> itr1 = graphModel.getUndirectedArcs();
		while(itr1.hasNext())
		{
			UndirectedArc anArc = itr1.next();
			edgeMap.put(anArc, new UndirectedGraphEdge(anArc, this));
		}
		Iterator<DirectedArc> itr2 = graphModel.getDirectedArcs();
		while(itr2.hasNext())
		{
			DirectedArc anArc= itr2.next();
			edgeMap.put(anArc, new DirectedGraphEdge(anArc, this));
		}		
		color = Color.RED;	
	}
	
	public GraphVertex getGraphVertex(GraphNode aNode)
	{
		Iterator<GraphVertex> itr = vertexList.iterator();
		GraphVertex toReturn = null;
		while(itr.hasNext())
		{
			toReturn = itr.next();
			if(toReturn.getNode().equals(aNode))
			{
				return toReturn;
			}
		}
		return toReturn;
	}
	
	
	public Iterator<GraphVertex> getVertexIterator() {
		return vertexList.iterator();
	}
	
	public Iterator<Map.Entry<Arc, GraphEdge>> getArcEdgeMapIterator() {
		return edgeMap.entrySet().iterator();
	}
	
	public void init()
	{
		canvas = new GraphCanvas(this);
	}

	public GraphModel getGraphModel() {
		return graphModel;
	}

	public void setGraphModel(GraphModel graphModel) {
		this.graphModel = graphModel;
	}	
	
	public boolean isDefault_behavior() {
		return default_behavior;
	}



	public void setDefault_behavior(boolean default_behavior) {
		this.default_behavior = default_behavior;
	}
	
	
  @SuppressWarnings("unchecked")
public Collection giveSelectables () {
    LinkedList list= new LinkedList();
    list.addAll(this.vertexList);
    return list;
    
  }

		
	public void makeDrawSets() {
		   if (super.getCanvas() instanceof Draw2DPanel) {
		      Draw2DPanel drawPanel = (Draw2DPanel) super.getCanvas();
//		      System.out.println("Draw Panel" + ((Object)drawPanel).toString());
//		      System.out.println(super.getCanvas().toString());
		      Iterator<GraphVertex> itr = vertexList.iterator();
		      GraphVertex tempVertex;
				while(itr.hasNext())
				{					
					tempVertex = itr.next();
					tempVertex.setCanvas(drawPanel);
					tempVertex.makeDrawSets();			
				}
				Iterator<Map.Entry<Arc, GraphEdge>> itr1 = edgeMap.entrySet().iterator();
				GraphEdge tempEdge;
				while(itr1.hasNext())
				{
					tempEdge = itr1.next().getValue();
					tempEdge.setCanvas(drawPanel);
					tempEdge.makeDrawSets();
				}
		    }
	}
	
	public LinkedList<Shape> getDrawList() {
		LinkedList<Shape> list = new LinkedList<Shape>();
		Iterator<GraphVertex> itr = vertexList.iterator();
		while(itr.hasNext())
		{
			
			list.addAll(itr.next().getDrawList());			
		}
		Iterator<Map.Entry<Arc, GraphEdge>> itr1 = edgeMap.entrySet().iterator();
		while(itr1.hasNext())
		{
			list.addAll(itr1.next().getValue().getDrawList());
		}		
		return list;
	}
	
	public LinkedList<Shape> getFillList() {
		LinkedList<Shape> list = new LinkedList<Shape>();
		Iterator<GraphVertex> itr = vertexList.iterator();
		while(itr.hasNext())
		{
			list.addAll(itr.next().getFillList());			
		}
		Iterator<Map.Entry<Arc, GraphEdge>> itr1 = edgeMap.entrySet().iterator();
		while(itr1.hasNext())
		{
			list.addAll(itr1.next().getValue().getFillList());
		}
		return list;
	}
	
	public LinkedList<DrawString> getStringList() {
		LinkedList<DrawString> list = new LinkedList<DrawString>();
		Iterator<GraphVertex> itr = vertexList.iterator();
		while(itr.hasNext())
		{
			list.addAll(itr.next().getStringList());			
		}
		Iterator<Map.Entry<Arc, GraphEdge>> itr1 = edgeMap.entrySet().iterator();
		while(itr1.hasNext())
		{
			list.addAll(itr1.next().getValue().getStringList());
		}
		return list;
	}	
}
