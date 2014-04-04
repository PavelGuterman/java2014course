package aStar;

import java.util.Comparator;

public class State implements Comparator<State>{
	private double f;
	private double g;
	String state;
	State came_from;
	
	
	
	public State(double f, double g, String state){
		this.f = f;
		this.g = g;
		this.state = state;
		this.came_from = null;
	}
	
	public double getF() {
		return f;
	}
	
	public void setF(double f) {
		this.f = f;
	}
	
	public double getG() {
		return g;
	}
	
	public void setG(double g) {
		this.g = g;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public State getCame_from() {
		return came_from;
	}
	
	public void setCame_from(State came_from) {
		this.came_from = came_from;
	}
	public int compare(State state1, State state2) {
		return state1.getState().compareTo(state2.getState());
	}


}
