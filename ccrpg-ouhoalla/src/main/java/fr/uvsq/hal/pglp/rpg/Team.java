package fr.uvsq.hal.pglp.rpg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * La classe <code>Team</code> représente un groupe de personnages.
 *
 * @author sarah & melissa
 * @version 2022
 */

public class Team implements MembreDeTeam, Iterable<MembreDeTeam> {
  private List<MembreDeTeam> members;

  public Team() {
    this.members = new ArrayList<>();
  }

  public void add(MembreDeTeam element) {
    if (this.equals(element)) {
    } else {
      this.members.add(element);
    }
  }

  public boolean contains(MembreDeTeam element) {
    return this.members.contains(element);
  }

  public void remove(MembreDeTeam element) {
    this.members.remove(element);
  }

  public int getSize(){
    return this.members.size();
  }

  //Patter Iterator
  @Override
  public Iterator<MembreDeTeam> iterator(){
    return new TeamIterator(members);
  }

  private static class TeamIterator implements Iterator<MembreDeTeam>{
    private Stack<Iterator<MembreDeTeam>> iteratorStack;

    public TeamIterator(List<MembreDeTeam> members){
      iteratorStack = new Stack<>();
      iteratorStack.push(members.iterator());
    }
    @Override
    public boolean hasNext(){
      boolean hasNext = iteratorStack.peek().hasNext();
      while (!hasNext) {
        iteratorStack.pop();
        if (iteratorStack.isEmpty()) {
          return false;
        }
        hasNext = iteratorStack.peek().hasNext();
      }
      return hasNext;
    }
    @Override
    public MembreDeTeam next() {
      MembreDeTeam nextElement = iteratorStack.peek().next();
      while (nextElement instanceof Team) {
        Team team = (Team) nextElement;
        Iterator<MembreDeTeam> newIterator = team.iterator();
        iteratorStack.push(newIterator);
        nextElement = iteratorStack.peek().next();
      }
      return nextElement;
    }
  }
}