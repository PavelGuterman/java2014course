package aStar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class CopyOfAstar {
	private Domain domain;
	private PriorityQueue<State> state_openList;
	private HashMap<String, State> state_closeList;
	private HashMap<State, State> state_from;

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public PriorityQueue<State> getState_openList() {
		return state_openList;
	}

	public void setState_openList(PriorityQueue<State> state_openList) {
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

	public CopyOfAstar(Domain domain) {
		this.domain = domain;

		state_openList = new PriorityQueue<State>(1, new Comparator<State>() {
			@Override
			public int compare(State state1, State state2) {
				return (int) ((state1.getF() - state2.getF()));
			}
		});

		state_closeList = new HashMap<String, State>();

		// state_closeList = new HashMap<String, State>();
	}

	private void CleareAllLists() {
		state_closeList.clear();
		state_openList.clear();
		state_from.clear();
	}

	public Stack<Action> search(State start, State goal) throws Exception {
		// CleareAllLists();

		// Initialize the start State
		start.setG(0);
		start.setF(domain.g(start, start) + domain.h(start, goal));

		// Initialize the open list with start
		state_openList.add(start);
		start.setF(domain.g(start, start));// + domain.h(start, goal));

		// Searching for path between Start and Goal
		while (!state_openList.isEmpty()) {
			// get the first State in line
			State q = state_openList.poll();
			// Check if I got to the goal
			if (q == goal) {
				return reconstruct_path(state_from, goal);
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

				// if openList doesn't have qNew and it's score is better then
				// put it to the openList
				if (state_openList.contains(qTag) == false
						|| (tentativeG < qTag.getG())) {

					// Update that q is qTag parent
					if (state_from != null
							&& state_from.containsKey(qTag.getState())) {
						state_from.remove(qTag.getState());
						state_from.put(qTag, q);
					} else {
						state_from.put(qTag, q);
					}

					// Update the scores of qNew
					if (tentativeG < qTag.getG()) {
						qTag.setG(tentativeG);
					}
					qTag.setF(qTag.getG() + domain.h(qTag, goal));

					// if openList doesn't contain qNew put it in openList
					if (state_openList.contains(qTag) == false) {
						state_openList.add(qTag);
					}
				}
			}
		}

		// There is no way to get from Start to Goal
		return null;
	}

	private Stack<Action> reconstruct_path(HashMap<State, State> came_from,State current) {
		if (came_from.containsKey(current)) {
			Stack<Action> temp = reconstruct_path(came_from,
					came_from.get(current));
			// return the action that lead to current
			temp.push((Action) current);
			return temp;
		} else {
			// no action that lead for that state
			Stack<Action> temp = null;
			return temp;
		}
	}

}
