/**
 * 
 */
package edu.ohiou.labimp.graphmodel.gui;

import java.awt.geom.Point2D;
import java.util.*;
import edu.ohiou.labimp.graphmodel.*;

/**
 * @author Ganduri
 *
 */
public class RandomLayouter implements GraphLayouter {

	protected Map<GraphVertex, Point2D> vertexLocMap;
	protected GraphRenderer renderer;
	private Random randGen = new Random();
	private int limit = 50;
	
	
	public RandomLayouter(GraphRenderer renderer)
	{
		this.renderer = renderer;
		while(renderer.getGraphModel().getTableModel().getColumnCount() > (Math.pow(limit, 2)/GraphConstants.NODE_SEPARATION))
		{
			limit += limit;
		}
		vertexLocMap = new HashMap<GraphVertex, Point2D>();
	}
	
	
	public void makeLayout()
	{
		Iterator<GraphVertex> itr = renderer.getVertexIterator();
		Iterator<GraphVertex> itr1 = vertexLocMap.keySet().iterator();
		GraphVertex temp;
		GraphVertex tempPositioned;
		Point2D.Double tempLoc;
		while(itr.hasNext())
		{
			temp = itr.next();
			tempLoc = new Point2D.Double(randGen.nextInt(limit), randGen.nextInt(limit));
			while(itr1.hasNext())
			{
				tempPositioned = itr1.next();
				while(tempLoc.distance(vertexLocMap.get(tempPositioned)) < GraphConstants.NODE_SEPARATION)
				{
					tempLoc = new Point2D.Double(randGen.nextInt(100), randGen.nextInt(100));
				}
			}
			
			temp.setNodeLocation(tempLoc);
			vertexLocMap.put(temp, tempLoc);
		}
	}
	
	public Point2D getVertexLocation(GraphVertex aVertex) {
		return vertexLocMap.get(aVertex);
	}
}
