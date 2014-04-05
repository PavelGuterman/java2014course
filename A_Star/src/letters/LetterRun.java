package letters;

import java.util.ArrayList;

import a_star.Action;
import a_star.Astar;

public class LetterRun {
	public static void main(String[] arg){
		Letters letters=new Letters("VAJA","JAVA");
		Astar as=new Astar( new LetterDomain( letters) );
		ArrayList<Action> actions = as.search(letters.getStartState() , letters.getGoalState() );
		for (Action a : actions)
		System.out.println(a.getName());
	}
}
