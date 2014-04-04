package aStar;

import java.util.Comparator;

public class CopyOfState implements Comparator<CopyOfState>{
	double f;
	double g;
	String state;
	CopyOfState stateFather;

	public CopyOfState(double f, double g, String state) {
		this.f = f;
		this.g = g;
		this.state = state;
	}
	
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = g+f;
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
	public CopyOfState getStateFather() {
		return stateFather;
	}

	public void setStateFather(CopyOfState stateFather) {
		this.stateFather = stateFather;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
	@Override
	public int compare(CopyOfState state1, CopyOfState state2) {
		//return (int)state1.getF()-(int)state2.getG();
		return state1.getState().compareTo(state2.getState());
	}
	


}
