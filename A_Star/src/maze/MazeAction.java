package maze;

import java.io.Serializable;

import model.algorithms.Action;
import model.algorithms.State;
import moveAction.mazeActionMove;
import a_star.IllegalActionExeption;

public class MazeAction implements Action ,Serializable{

	private int action;
	private final String[] actionsValid = new String[] { "Move_Up",
			"Move_Down", "Move_Left", "Move_Right", "Move_UpLeft",
			"Move_UpRight", "Move_DownLeft", "Move_DownRight" };

	public MazeAction(String action){
		this.action = getValidActionNumber(action);
	}

	public int getValidActionNumber(String act) {

		for (int i = 0; i < actionsValid.length; i++) {
			if (actionsValid[i].equals(act)) {
				return i;
			}
		}
		return -1;
	}

	public String getValidActionString(int act) throws IllegalActionExeption {
		if (act < 0 || act > actionsValid.length) {
			throw new IllegalActionExeption();
		} else {
			return actionsValid[act];
		}
	}

	@Override
	public State doAction(State state)  {
		int[] s = new int[2];
		s[0] = Integer.parseInt((state.getState().split(","))[0]);
		s[1] = Integer.parseInt((state.getState().split(","))[1]);

		int[] newState = doMoveAction(actionsValid[action],s);

		String stateString = Integer.toString(newState[0]) + ","
				+ Integer.toString(newState[1]);
		
		return new State(state.getF(), state.getG(), stateString);
	}


	private int[] doMoveAction(String actionName, int[] pos) {
		/*
		 * by relation 
		 */
		try {
			Class<mazeActionMove> classAction;
			classAction = (Class<mazeActionMove>) Class.forName("moveAction." + actionName);
			
			return  (int[]) classAction.getMethod("doMoveAction",
					pos.getClass()).invoke(
					classAction.getConstructor().newInstance(), pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getName() {
		return actionsValid[action];
	}

}
