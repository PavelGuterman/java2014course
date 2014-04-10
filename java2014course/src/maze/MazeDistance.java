package maze;

import model.algorithms.Distance;
import model.algorithms.State;

public class MazeDistance implements Distance {

	@Override
	public double getDistance(State from, State to) {
		int state_from[] = getPosition(from);
		int state_to[] = getPosition(to);
		int row = Math.abs(state_to[0] - state_from[0]);
		int col = Math.abs(state_to[1] - state_from[1]);
		return Math.sqrt((col^2 + row^2));
	}
	
	private int[] getPosition(State state) {
		int[] pos = new int[2];
		pos[0] = Integer.parseInt((state.getState().split(","))[0]);
		pos[1] = Integer.parseInt((state.getState().split(","))[1]);
		return pos;
	}

}
