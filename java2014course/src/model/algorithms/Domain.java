package model.algorithms;

import java.util.ArrayList;

public interface Domain {
	public double g(State from, State to);
	public double h(State state, State goal);
	public ArrayList<Action> getActions(State state);
	public Action getAction(State acttionFather, State actionSon);
	public double getRange(State s1, State s2);
}
