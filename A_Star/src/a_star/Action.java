package a_star;

public interface Action {
	State doAction(State state) throws IllegalActionExeption;
	public String getName();
}
