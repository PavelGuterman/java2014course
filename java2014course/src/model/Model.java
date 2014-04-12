package model;

import java.io.Serializable;

import model.algorithms.Distance;
import model.algorithms.Domain;
import model.algorithms.Searcher;

public class Model implements Serializable {
	
	private Domain domain;
	private Distance[] distances;
	private Searcher searcher;
	
	public void setDomain(String key){
		
	}
	public void setHeuristics(String[] keys){
		
	}
	public void setSearcher(String key){
		
	}
	public int solveDomain(){
		
		return 0;
	}
}
