package maze;

import java.io.IOException;
import java.util.ArrayList;

import a_star.Action;
import a_star.Astar;

public class MaizRun {

	public static void main(String[] args) throws IOException{
		Maze maze= new Maze();
		Astar as =new Astar( new MazeDomain( maze ) );
		ArrayList<Action> actions = as.search(maze.getStartState() , maze.getGoalState() );
		for (Action a : actions)
		System.out.println(a.getName()+" Start dennis !");
	}

}
