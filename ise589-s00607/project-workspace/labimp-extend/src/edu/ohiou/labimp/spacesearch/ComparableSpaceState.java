package edu.ohiou.labimp.spacesearch;

import java.util.Collection;

import edu.ohiou.labimp.basis.Viewable;

public abstract class ComparableSpaceState extends DefaultSpaceState implements
    Comparable {

  public int compareTo (Object other) {
    return getComparator().compare(this, other);
  }

  public boolean isSearchComplete (SpaceSearcher s) {
  if (s.open.isEmpty()) {
    s.hasReachedGoal = (s.goalState != null);
    return true;
//    return s.goalState;
  }

   s.currentState = s.open.first();
   s.open.remove(s.currentState);
   ((Viewable)s.currentState).setColor (SpaceSearcher.CURRENT_COLOR);
  if (s.currentState.canBeGoal()) { 
    if (s.goalState == null || s.currentState.isBetterThan(s.goalState)) {
      if (s.goalState != null) {
        ((Viewable)s.goalState).setColor (SpaceSearcher.CLOSED_COLOR);
      }
      s.goalState = s.currentState;      
      ((Viewable)s.currentState).setColor (SpaceSearcher.GOAL_COLOR);
    }
    if (s instanceof InformedSearcher) {
    s.hasReachedGoal = true;
    return true;
    }
  }
  return false;
  }
  
  abstract public HeuristicFunction getHeuristic() 
    throws HeuristicException;

}
