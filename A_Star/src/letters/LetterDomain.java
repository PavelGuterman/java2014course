package letters;

import java.util.ArrayList;

import a_star.Action;
import a_star.Domain;
import a_star.State;

public class LetterDomain implements Domain {

	@Override
	public double g(State from, State to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double h(State state, State goal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Action> getActions(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action getAction(State acttionFather, State actionSon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getRange(State s1, State s2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
