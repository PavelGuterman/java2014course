package model.algorithms.a_star;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import model.algorithms.AbsSearcher;
import model.algorithms.Action;
import model.algorithms.Distance;
import model.algorithms.Domain;
import model.algorithms.State;

public class Astar extends AbsSearcher{
	
	private Domain domain;
	private Distance g;
	private Distance h;
	
	
	public Astar(Domain domain,Distance g,Distance h){
		this.domain = domain;
		setState_openList(new PriorityQueue<State>());
		setState_closeList(new HashSet<State>());	
		this.g = g;
		this.h = h;
	}
	
	
	@Override
	public ArrayList<Action> search(State start, State goal) {
		start.setG(0);
		start.setF(g.getDistance(start, start)+ h.getDistance(start, goal));
//		start.setF(domain.g(start, start) + domain.h(start, goal));
		getState_openList().add(start);
		while(!getState_openList().isEmpty()){
			State q = getState_openList().poll();
			if (q.getState().equals(goal.getState())){
				return reconstruct_path(q,q.getCame_from());
			}
			getState_closeList().add(q);
			for(Action act: domain.getActions(q))
			{
				State q_tag = act.doAction(q);
				double ten_g = q.getG() + domain.getRange(q,q_tag);
				if (getState_closeList().contains(q_tag.getState()) 
						&& ten_g >= q_tag.getG()){
					continue;
				}
				boolean flag = false;
				for (State st : getState_openList()) {
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
					getState_openList().add(q_tag);
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
