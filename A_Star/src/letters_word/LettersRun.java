package letters_word;

import java.util.ArrayList;

import model.algorithms.a_star.Astar;

public class LettersRun {

	public static void main(String[] args) {
		Letters letters=new Letters("VAJA","JAVA");
		LettersDistance letterG = new LettersDistance();
		LettersDistance letterH = new LettersDistance();
		Astar as=new Astar( new LetterDomain( letters), letterG, letterH );
		ArrayList<Action> actions = as.search(letters.getStartState() , letters.getGoalState() );
		for (Action a : actions)
			System.out.println(a.getName());

	}

}
