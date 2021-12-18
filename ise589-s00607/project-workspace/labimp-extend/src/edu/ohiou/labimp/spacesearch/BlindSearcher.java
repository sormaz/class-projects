package edu.ohiou.labimp.spacesearch;

import java.util.*;

import edu.ohiou.labimp.basis.Viewable;

public class BlindSearcher extends SpaceSearcher {


  public BlindSearcher() {
    super();
    // TODO Auto-generated constructor stub
  }

  public BlindSearcher(Searchable initial, Searchable goal) {
    super(initial, goal);
    // TODO Auto-generated constructor stub
  }

  public BlindSearcher(Searchable initial, Searchable goal, int order) {
    super(initial, goal, order);
    // TODO Auto-generated constructor stub
  }

//  public BlindSearcher(Searchable initial, Searchable currentState,
//      Searchable goal, Comparator comparator) {
//    super(initial, currentState, goal, comparator);
//    // TODO Auto-generated constructor stub
//  }
//
//  public BlindSearcher(Searchable initial, Searchable goal,
//      Comparator comparator) {
//    super(initial, goal, comparator);
//    // TODO Auto-generated constructor stub
//  }
  
  //move to blind searcher
  public void initializeSearch() {
    Comparator<Searchable> comp = null;;
    if (searchOrder == DEPTH_FIRST) 
      comp = new DepthFirstComparator();
    if (searchOrder == BREADTH_FIRST) 
      comp = new BreadthFirstComparator();

    open = new TreeSet<Searchable>(comp) {
      public boolean add (Searchable state) {
        HashSet<Searchable> hs = new HashSet<Searchable> (this);
        if (!hs.contains(state)) {
          //commentout by JING HUANG'''''''''''''''''''''''''	
             //System.out.println("Contains not:\n"  + state + "\n" + hs);
          //''''''''''''''''''''''''''''''''''''''''''''''''
          return super.add(state);
        }
        //commentout by JING HUANG''''''''''''''''''''''''''''''
            //System.out.println("Contains:\n"  + state + "\n" + hs);
        //'''''''''''''''''''''''''''''''''''''''''''''''''''''
        return false;
      }
    };
    closed = new HashSet<Searchable>();
//    newStates = new LinkedList<Searchable>();
    open.add(initialState);
  }

  public Searchable runSpaceSearch(int numberOfSteps) {
    System.out.println(open.comparator());
     try {
//  	   BY JING HUANG'''''''''''''''''''''''''''''''''''''''''    	 
//       if (currentState!= goalState)
//    	   BY JING HUANG'''''''''''''''''''''''''''''''''''''''''
//         ((Viewable)currentState).setColor (CLOSED_COLOR);
// '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''    	   
    } catch (NullPointerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    if (isSearchComplete(this)) {
      if (hasReachedGoal) 
         return goalState;
      else
         return null;
    }
    closed.add(currentState);
    
    Set newStates = (Set) currentState.makeNewStates();
    //  commentout by JING HUANG''''''''''''''''''''''''''''''
    //System.out.println("new states: " + newStates);
    //System.out.println("new states size: " + newStates.size());
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''
    removeOldStates(newStates, open); // needed for jtree
    removeOldStates(newStates, closed);
    children = new LinkedList(newStates);
    open.addAll(children);
    // by JING HUANG'''''''''''''''''''''''''''''''''''''''''''''
       System.out.println("current State :" +currentState);
       System.out.println("open :" + open);
       System.out.println("close :" + closed);
     //System.out.println("open size:" + open.size());
     //System.out.println("closed size:" + closed.size());
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    if (numberOfSteps <= 1) {
      return currentState;
    } 
    else {
      numberOfSteps--;
      return runSpaceSearch(numberOfSteps);
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
