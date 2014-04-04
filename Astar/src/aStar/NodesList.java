package aStar;

import java.util.HashMap;
import java.util.PriorityQueue;

public class NodesList<T>
{
	private PriorityQueue<State> nodesPriorityQ;
	private HashMap<String, State> nodesHashMap;
	
	public NodesList()
	{
		this.setNodesPriorityQ(new PriorityQueue<State>());
		this.setNodesHashMap(new HashMap<String, State>());
	}

	public PriorityQueue<State> getNodesPriorityQ() {
		return nodesPriorityQ;
	}

	public void setNodesPriorityQ(PriorityQueue<State> nodesPriorityQ) {
		this.nodesPriorityQ = nodesPriorityQ;
	}

	public HashMap<String, State> getNodesHashMap() {
		return nodesHashMap;
	}

	public void setNodesHashMap(HashMap<String, State> nodesHashMap) {
		this.nodesHashMap = nodesHashMap;
	}

	public boolean add(State state)
	{
		this.nodesHashMap.put(state.getState(), state);
		return this.nodesPriorityQ.add(state);
	}
	
	public boolean HasNodes()
	{
		return !this.nodesHashMap.isEmpty();
	}

	public State NextNode() {
		return nodesPriorityQ.poll();
	}

	public State Get(String stateString) {
		return this.nodesHashMap.get(stateString);
	}

	
}
