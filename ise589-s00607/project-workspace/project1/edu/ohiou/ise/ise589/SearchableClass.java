package edu.ohiou.ise.ise589;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;


import edu.ohiou.labimp.spacesearch.*;

public class SearchableClass implements Searchable {
	private int stateIndex =0;
	SpaceSearcher searcher;
	SearchableClass parent = null;
	DefaultMutableTreeNode node = new DefaultMutableTreeNode(this);
	
	public SearchableClass (int index, SearchableClass parent) {
		stateIndex = index;
		this.parent = parent;
	}

	public boolean canBeGoal() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean equals(Searchable s) {
		SearchableClass sObject = (SearchableClass) s;
		// TODO Auto-generated method stub
		return stateIndex == sObject.stateIndex;
	}

	public Searchable getClone() {
		// TODO Auto-generated method stub
		return null;
	}

	public DefaultMutableTreeNode getNode() {
		// TODO Auto-generated method stub
		return node;
	}

	public Searchable getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	public boolean isBetterThan(Searchable inState) {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection makeNewStates() {
		LinkedList<SearchableClass> states = new LinkedList<SearchableClass> ();
		int newIndex = stateIndex+1;
		states.add (new SearchableClass(newIndex++, this));
		states.add (new SearchableClass(newIndex++, this));
		states.add (new SearchableClass(newIndex++, this));
		// TODO Auto-generated method stub
		return states;
	}

	public boolean memberInList(Collection l) {
		// TODO Auto-generated method stub
		return false;
	}

	public String printPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public Searchable runSpaceSearch() {
		return null;
	}

	public Searchable runSpaceSearch (Searchable s) {
		searcher = new BlindSearcher (this, s);
		// TODO Auto-generated method stub
		return searcher.runSpaceSearch();
	}

	public int[] setSearchTypes() {
		int types [] = {SpaceSearcher.DEPTH_FIRST, SpaceSearcher.BREADTH_FIRST};
		return types;
	}
	
	public String toString () {
		return "SearchbleClass:" + stateIndex;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchableClass init = new SearchableClass(0, null);
		SearchableClass goal = new SearchableClass(5, null);
		SpaceSearcher searcher = new BlindSearcher(init, goal);
		searcher.display();
//		Searchable result = init.runSpaceSearch(goal);
//		System.out.println("result: " + result);
	}

}
