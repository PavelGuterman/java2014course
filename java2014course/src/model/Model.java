package model;

import java.io.Serializable;

import model.algorithms.Distance;
import model.algorithms.Domain;
import model.algorithms.Searcher;
import model.algorithms.State;

public class Model implements Serializable {
	
	private Domain domain;
	private Distance[] distances;
	private Searcher searcher;
	
	private ModelFactory modelFactory;
	
	public Model() {
		modelFactory= new ModelFactory();
	}
	
	public void setDomain(String key){
		domain = modelFactory.createDomain(key);
		
	}
	public void setHeuristics(String[] keys){
		distances = new Distance[keys.length];
		for (int i = 0; i < keys.length; i++) {
			distances[i] = modelFactory.createDistance(keys[i]);
		}
	}
	public void setSearcher(String key){
		searcher = modelFactory.createSearcher(key, domain, distances[0], distances[1]);
	}
	public int solveDomain(State start, State goal){
		searcher.search(start, goal);
		return searcher.getNumOfEvaluatedNodes();
	}
}
