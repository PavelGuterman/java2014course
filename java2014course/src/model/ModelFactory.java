package model;

import java.util.HashMap;

import maze.Maze;
import maze.MazeDistance;
import maze.MazeDomain;
import model.algorithms.Distance;
import model.algorithms.Domain;

public class ModelFactory {
	HashMap<String,Creator> domainCreators;
	
	
	private interface Creator {
		public Domain create();
	}

	
	private class SmallMaze_Creator implements Creator {
		public Domain create() {
			
			return new MazeDomain(new Maze());
		}
	}
	
	
	
	
	public ModelFactory(){
		domainCreators = new HashMap<String, Creator>();
		
		domainCreators.put("small", new SmallMaze_Creator());

		

	}
	
}
