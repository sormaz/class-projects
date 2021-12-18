/**
 * 
 */
package edu.ohiou.labimp.graphmodel.gui;

import java.awt.geom.Point2D;
import java.util.*;
import edu.ohiou.labimp.graphmodel.GraphNode;

/**
 * @author Ganduri
 *
 */
public interface GraphLayouter {
	
	public Point2D getVertexLocation(GraphVertex aVertex);	
	
	public void makeLayout();

}
