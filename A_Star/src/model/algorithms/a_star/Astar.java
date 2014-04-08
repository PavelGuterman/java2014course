package model.algorithms.a_star;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

import a_star.IllegalActionExeption;
import model.algorithms.Action;
import model.algorithms.Domain;
import model.algorithms.State;

public class Astar {
	
	private Domain domain;
	private PriorityQueue<State> state_openList;
	private HashSet<State> state_closeList;
	
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
	public HashSet<State> getState_closeList() {
		return state_closeList;
	}
	public void setState_closeList(HashSet<State> state_closeList) {
		this.state_closeList = state_closeList;
	}

	public Astar(Domain domain){
		this.domain = domain;
		state_openList = new PriorityQueue<State>();
		
		state_closeList = new HashSet<State>();
	}
	
	
	public ArrayList<Action> search(State start, State goal) throws IllegalActionExeption{
		
		start.setG(0);
		start.setF(domain.g(start, start) + domain.h(start, goal));
		state_openList.add(start);
		while(!state_openList.isEmpty()){
			State q = state_openList.poll();
			if (q.getState().equals(goal.getState())){
				return reconstruct_path(q,q.getCame_from());
			}
			state_closeList.add(q);
			for(Action act: domain.getActions(q))
			{
				State q_tag = act.doAction(q);
				double ten_g = q.getG() + domain.getRange(q,q_tag);
				if (state_closeList.contains(q_tag.getState()) 
						&& ten_g >= q_tag.getG()){
					continue;
				}
				boolean flag = false;
				for (State st : state_openList) {
					if (st.getState().equals(q_tag.getState())) {
						flag = true;
						break;
					}
				}
				if(!flag || ten_g < q_tag.getG()){
					q_tag.setCame_from(q);
					q_tag.setG(ten_g);;
				}
				if(ten_g < q_tag.getG()){
					q_tag.setG(ten_g);
				}
				q_tag.setF(q_tag.getG()+ domain.h(q_tag, goal));
				if(!flag){
					state_openList.add(q_tag);
				}
			}
		}
		return null;
		
	}
	private ArrayList<Action> reconstruct_path(State goal, State son) {
		if(son == null){
			return null;
		}
		
		
		Stack<Action> stack = new Stack<Action>();
		while(son != null){
			stack.add(domain.getAction(goal, son));
			goal = son;
			son = son.getCame_from();
		}
		
		ArrayList<Action> arryActions = new ArrayList<Action>();
		while (!stack.isEmpty()){
			arryActions.add(stack.pop());
		}

		
		return arryActions; 
		
		
	}
	
	
}
