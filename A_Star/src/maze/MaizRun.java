package maze;

import java.io.IOException;
import java.util.ArrayList;

import model.algorithms.Action;
import model.algorithms.Distance;
import model.algorithms.a_star.Astar;

public class MaizRun {

	public static void main(String[] args) throws IOException{
		Maze maze= new Maze();
		Distance mazeG = new MazeDistance();
		Distance mazeH = new MazeDistance();
		Astar as =new Astar( new MazeDomain( maze ), mazeG, mazeH );
		ArrayList<Action> actions = as.search(maze.getStartState() , maze.getGoalState() );
		for (Action a : actions){
		System.out.println(a.getName());
		}
		System.out.println("You have reach the GOAL");
	}

}
