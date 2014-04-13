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
	private HashMap<String, Domain_Creator> domainCreators;
	private HashMap<String, Searcher_Creator> sercherCreators;
	private HashMap<String, Distance_Creator> distanceCreators;

	public ModelFactory() {
		domainCreators = new HashMap<String, Domain_Creator>();
		domainCreators.put("small", new SmallMaze_Creator());
		domainCreators.put("big", new BiglMaze_Creator());
		
		sercherCreators = new HashMap<String, Searcher_Creator>();
		sercherCreators.put("astar", new AStar_Creator());
		sercherCreators.put("bfs", new BFS_Creator());
		
		distanceCreators = new HashMap<String, Distance_Creator>();
		distanceCreators.put("astar", new MazeDistance_Creator());
		distanceCreators.put("bfs", new BFSDistance());
		
	}
	
	
	/*
	 * Domain interface and creators
	 */
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

	public Domain createDomain(String type) {
		Domain_Creator dom = domainCreators.get(type);
		if (dom != null)
			return dom.create();

		return null;
	}

	/*
	 * Searcher interface and creators
	 */
	private interface Searcher_Creator {
		public Searcher create(Domain domain, Distance g, Distance h);
	}

	private class AStar_Creator implements Searcher_Creator {
		public Searcher create(Domain domain, Distance g, Distance h) {
			return new Astar(domain, g, h);
		}
	}

	private class BFS_Creator implements Searcher_Creator {
		public Searcher create(Domain domain, Distance g, Distance h) {
			return new Astar(domain, g, h);// must be BFS
		}
	}
	
	public Searcher createSearcher(String type,Domain domain, Distance g, Distance h) {
		Searcher_Creator ser = sercherCreators.get(type);
		if (ser != null)
			return ser.create(domain,g,h);

		return null;
	}
	
	
	/*
	 * Distance interface and creators
	 */
	private interface Distance_Creator {
		public Distance create();
	}

	private class MazeDistance_Creator implements Distance_Creator {
		public Distance create() {
			return new MazeDistance();
		}
	}

	private class BFSDistance implements Distance_Creator {
		public Distance create() {
			return new MazeDistance(); //must be Letters !! 
		}
	}
	
	public Distance createDistance(String type) {
		Distance_Creator dist = distanceCreators.get(type);
		if (dist != null)
			return dist.create();

		return null;
	}
}
