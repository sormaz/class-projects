/**
 * 
 */
package edu.ohiou.labimp.graphmodel;

import java.util.EventListener;

/**
 * @author Ganduri
 *
 */
public interface NodeListener extends EventListener {
	
	public void nodeChanged();
	
	public void userObjectUpdated();

}
