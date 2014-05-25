package letters_word;

import java.util.ArrayList;

import model.algorithms.Action;
import model.algorithms.Domain;
import model.algorithms.State;

public class LetterDomain implements Domain {

	private Letters letters;
	
	public LetterDomain(Letters letters){
		this.letters = letters;
	}

	@Override
	public ArrayList<Action> getActions(State state) {
		ArrayList<Action> act = new ArrayList<Action>();
		int indx = Integer.parseInt(state.getState());
		if(indx > 0){
			
		}
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
