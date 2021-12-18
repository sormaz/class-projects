package edu.ohiou.labimp.spacesearch;

import java.util.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.ohiou.labimp.spacesearch.TravelingSalesman.TSComparator;

public interface Searchable {
	public Collection makeNewStates();
	public Searchable runSpaceSearch();
	public Searchable runSpaceSearch(Searchable s);
	public boolean memberInList(Collection l);
	public String printPath();
	public boolean equals(Searchable s);
	public Searchable getParent();
	public DefaultMutableTreeNode getNode();
	//  public DefaultMutableTreeNode createNodes (Searchable s);
	public int[] setSearchTypes();
	public boolean canBeGoal();
	public boolean isBetterThan(Searchable inState);
//	public JPanel getPanel();
//	public Searchable getClone();
	//  public Comparator getComparator();
  public Comparator getComparator();
  public int getIndex();
  
  public boolean isSearchComplete (SpaceSearcher s);
  
  public double distFromStart();
  public double distToGoal();

}
