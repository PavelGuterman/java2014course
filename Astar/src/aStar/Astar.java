package aStar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;


public class Astar {
	
	private Domain domain;
	private  PriorityQueue<State> state_openList;
	private  HashMap<String, State> state_closeList;
	
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

	public Astar(Domain domain){
		this.domain = domain;
		state_openList = new PriorityQueue<State>(1, new Comparator<State>() {
			public int compare(State state1, State state2) {
				return (int) (state1.getF() - state2.getF());
			}
		});
		
		state_closeList = new HashMap<String, State>();
	}
	
	
	public ArrayList<Action> search(State start, State goal) throws Exception{
		
		start.setG(0);
		start.setF(domain.g(start, start) + domain.h(start, goal));
		state_openList.add(start);
		while(!state_openList.isEmpty()){
			State q = state_openList.poll();
			System.out.println("q.state= "+q.state+" goal.state= "+goal.state);
			if (q.state == goal.state){
				return reconstruct_path(q);
			}
			state_closeList.put(q.getState(), q);
			for(Action act: domain.getActions(q)){
				State q_tag = act.doAction(q);
				System.out.println(q_tag.state+" "+act.getName());
				double ten_g = q.getG() + domain.getRange(q,q_tag);
				if (state_closeList.containsKey(q_tag.getState()) 
						&& ten_g >= q_tag.getG()){
					continue;
				}
				System.out.println("state_openList.contains(q_tag)= "+state_openList.contains(q_tag)+" ten_g  "+ten_g+" < q_tag.getG() "+q_tag.getG());
				if(!state_openList.contains(q_tag) || ten_g < q_tag.getG()){
					q_tag.setCame_from(q);
				}
				if(ten_g < q_tag.getG()){
					q_tag.setG(ten_g);
				}
				q_tag.setF(q_tag.getG()+ domain.h(q_tag, goal));
				if(!state_openList.contains(q_tag)){
					state_openList.add(q_tag);
				}
			}
		}
		System.err.println("There is no way to get from Start to Goal !");
		return null;
		
	}
	private ArrayList<Action> reconstruct_path(State q) {
		if (q.came_from == null){
			return null;
		}
		ArrayList<Action> tmp = reconstruct_path(q.came_from); 
		tmp.add((Action) q);
		return tmp;
	}
	
	
}
