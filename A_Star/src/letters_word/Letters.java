package letters_word;

import model.algorithms.State;

public class Letters {
	private String source;
	private String dest;	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Letters(String source, String dest){
		this.setSource(source);
		this.setDest(dest);
	}

	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	public State getGoalState() {
		// TODO Auto-generated method stub
		return null;
	}



}
