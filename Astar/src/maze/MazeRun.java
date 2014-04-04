package maze;

import java.util.ArrayList;
import java.util.Stack;

import aStar.Action;
import aStar.Astar;

public class MazeRun {

	public static void main(String[] args) {
		Maze maze = new Maze();
		Astar as = new Astar(new MazeDomain(maze));

		ArrayList<Action> actions;
		try {
			actions = as.search(maze.getStartState(), maze.getGoalState());
		} catch (Exception e) {
			System.err.println("Error 1");
			e.printStackTrace();
			return;
		}
		if (actions == null || actions.isEmpty()){
		  System.out.println("No Anser for good");
		}else{
			for (Action a : actions)
				System.out.println(a.getName());
		}

	}

}
