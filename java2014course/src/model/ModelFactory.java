package model;

import java.util.ArrayList;
import java.util.HashMap;

import maze.Maze;
import maze.MazeBig;
import maze.MazeDistance;
import maze.MazeDomain;
import model.algorithms.Action;
import model.algorithms.Distance;
import model.algorithms.Domain;
import model.algorithms.Searcher;
import model.algorithms.State;
import model.algorithms.a_star.Astar;

public class ModelFactory {
	HashMap<String,Domain_Creator> domainCreators;
	HashMap<String,Searcher_Creator> sercherCreators;
	HashMap<String,Distance_Creator> distanceCreators;
	
	//Domain 
	private interface Domain_Creator {
		public Domain create();
	}
	
	private class SmallMaze_Creator implements Domain_Creator {
		public Domain create() {
			return new MazeDomain(new Maze());
		}
	}
	private class BiglMaze_Creator implements Domain_Creator {
		public Domain create() {
			return new MazeDomain(new MazeBig());
		}
	}
	
	//sercher	
	private interface Searcher_Creator {
		public Searcher create(Domain domain, Distance g, Distance h);
	}
	
	private  class AStar_Creator implements Searcher_Creator{
		public Searcher create(Domain domain, Distance g, Distance h) {
			return new Astar(domain, g, h);
		}
	}
	
	private  class BFS_Creator implements Searcher_Creator{
		public Searcher create(Domain domain,Distance g, Distance h){
			return new Astar(domain, g, h);
		}
	}
	
	public ModelFactory(){
		domainCreators = new HashMap<String, Domain_Creator>();
		domainCreators.put("small", new SmallMaze_Creator());
		domainCreators.put("big", new BiglMaze_Creator());
		
		
	}
	
	public void GetSerchers(Domain domain,Distance g, Distance h){
		sercherCreators = new HashMap<String, Searcher_Creator>() ;
		sercherCreators.put("astar", new AStar_Creator());
		sercherCreators.put("bfs", new BFS_Creator());
	}
		
	
	//distance
	private interface Distance_Creator {
		public Distance create();
	}
	
	
	private  class MazeDistance_Creator implements Distance_Creator{
		public Distance create() {
			return new MazeDistance();
		}
	}
	
	private  class BFSDistance implements Distance_Creator{
		public Distance create() {
			return new MazeDistance();
		}
	}
	
	public void GetDistances(){
		distanceCreators = new HashMap<String, Distance_Creator>() ;
		distanceCreators.put("astar", new MazeDistance_Creator());
		distanceCreators.put("bfs", new BFSDistance());
	}
}
