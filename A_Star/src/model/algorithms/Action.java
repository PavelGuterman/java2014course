package model.algorithms;


public interface Action {
	State doAction(State state) ;
	public String getName();
}
