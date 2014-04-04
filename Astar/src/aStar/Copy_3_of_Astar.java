package aStar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import org.w3c.dom.NodeList;

public class Copy_3_of_Astar {
	private Domain domain;
	//private PriorityQueue<State> state_openList;
	private NodesList<State> state_openList;
	private HashMap<String, State> state_closeList;
	private HashMap<State, State> state_from;

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public NodesList<State> getState_openList() {
		return state_openList;
	}

	public void setState_openList(NodesList<State> state_openList) {
		this.state_openList = state_openList;
	}

	public HashMap<String, State> getState_closeList() {
		return state_closeList;
	}

	public void setState_closeList(HashMap<String, State> state_closeList) {
		this.state_closeList = state_closeList;
	}

	public HashMap<State, State> getState_from() {
		return state_from;
	}

	public void setState_from(HashMap<State, State> state_from) {
		this.state_from = state_from;
	}

	public Copy_3_of_Astar(Domain domain) {
		this.domain = domain;

		state_openList = new NodesList<State>(); 

		state_closeList = new HashMap<String, State>();

		// state_closeList = new HashMap<String, State>();
	}

	

	public Stack<Action> search(State start, State goal) throws Exception {
		// CleareAllLists();

		start.setG(0);
		start.setF(domain.g(start, start) + domain.h(start, goal));

		state_openList.add(start);

		// Searching for path between Start and Goal
		while (state_openList.HasNodes()) {
			// get the first State in line
			State q = state_openList.NextNode();
			System.out.println(q.getState());
			// Check if I got to the goal
			if (q.compare(q,goal)==0) {
				return reconstructPath(state_from, q); // return reconstruct_path(state_from, goal);
			}

			// Put q in the closeList
			state_closeList.put(q.getState(), q);

			// get all allowed actions
			for (Action a : domain.getActions(q)) {
				// new state after allowed action
				State qTag = a.doAction(q);

				// new score
				double tentativeG = q.getG() + domain.getRange(q, qTag);

				// if qTag appears already in the closeList and it's score worse
				// then continue to the next allowed action
				if (state_closeList.containsKey(qTag.getState())
						&& (tentativeG >= qTag.getG()))
					continue;
				
				qTag.setG(domain.g(start, q) + domain.getRange(q, qTag));
				qTag.setF(domain.g(start, qTag) + domain.h(qTag, goal));
				qTag.setState(q.getState());
				//qTag.setpAction(a);
				
				// if open list doesn't have qTag and it's score is better then
				// put it to the openList
				System.out.println("gTag= "+qTag.getState()+" tentativeG  "+tentativeG+" < qTag.getG() "+qTag.getG());
				if (state_openList.Get(qTag.getState())!=null  || (tentativeG < qTag.getG())) {

					// Update that q is qTag parent
					if (state_from != null
							&& state_from.containsKey(qTag.getState())) {
						state_from.remove(qTag.getState());
						state_from.put(qTag, q);
					} else {
						state_from =new HashMap<State, State>();
						state_from.put(qTag, q);
					}

					// Update the scores of qNew
					if (tentativeG < qTag.getG()) {
						qTag.setG(tentativeG);
					}
					qTag.setF(qTag.getG() + domain.h(qTag, goal));

					// if open list doesn't contain qTag put it in openList
					if (state_openList.Get(qTag.getState())==null) {
						state_openList.add(qTag);
					}
				}
			}
		}

		System.err.println("There is no way to get from Start to Goal !");
		return null;
	}

	private Stack<Action> reconstructPath(HashMap<State, State> came_from,State current) {
		Stack<Action> temp = null;
		if (came_from.containsKey(current)) {
			temp = reconstructPath(came_from,	came_from.get(current));
			temp.push((Action) current);
			//return temp;
		} 
		return temp;
	}

}
