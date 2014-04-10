package model.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public abstract class AbsSearcher implements Searcher {
	
	private PriorityQueue<State> state_openList;
	private HashSet<State> state_closeList;
	private int evaluatedNodesNum = 0;
	
	public PriorityQueue<State> getState_openList() {
		return state_openList;
	}

	public void setState_openList(PriorityQueue<State> state_openList) {
		this.state_openList = state_openList;
		evaluatedNodesNum++;
	}

	public HashSet<State> getState_closeList() {
		return state_closeList;
	}

	public void setState_closeList(HashSet<State> state_closeList) {
		this.state_closeList = state_closeList;
	}

	@Override
	public abstract ArrayList<Action> search(State start, State goal);

	@Override
	public int getNumOfEvaluatedNodes() {
		return evaluatedNodesNum;
	}

}
