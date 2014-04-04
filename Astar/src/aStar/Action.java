package aStar;

import java.rmi.UnexpectedException;

public interface Action {
	State doAction(State state) throws UnexpectedException;
	public String getName();
}
