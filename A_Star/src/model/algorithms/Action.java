package model.algorithms;

import a_star.IllegalActionExeption;

public interface Action {
	State doAction(State state) throws IllegalActionExeption;
	public String getName();
}
