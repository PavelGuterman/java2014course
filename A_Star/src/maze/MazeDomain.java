package maze;

import java.util.ArrayList;

import model.algorithms.Action;
import model.algorithms.Domain;
import model.algorithms.State;
import a_star.IllegalActionExeption;
//import a_star.IllegalActionExeption;

public class MazeDomain implements Domain {

	private Maze maze;

	public MazeDomain(Maze maze) {
		this.maze = maze;
	}

	@Override
	public double g(State from, State to) {
		return to.getG() - from.getG();
	}

	@Override
	public double h(State state, State goal) {
		int state_i[] = getPosition(state);
		int goal_i[] = getPosition(goal);

		int d1 = state_i[0] - goal_i[0];
		int d2 = state_i[1] - goal_i[1];
		if (d1 < 0)
			d1 *= -1;
		if (d2 < 0)
			d2 *= -1;

		return d1 + d2;
	}

	@Override
	public ArrayList<Action> getActions(State state) {

		ArrayList<Action> actions = new ArrayList<Action>();
		int state_i[] = getPosition(state);

		boolean isUp = state_i[0] > 0;
		boolean isDown = state_i[0] + 1 <  maze.getColoms();
		boolean isleft = state_i[1] > 0;
		boolean isRight = state_i[1] + 1 < maze.getRows();

		if (isUp) {
			if (maze.getPoin(state_i[0] - 1, state_i[1]) == maze.getEmpty()) {
				actions.add(new MazeAction("Move_Up"));
			}
			if (isleft
					&& (maze.getPoin(state_i[0] - 1, state_i[1] - 1) == maze
							.getEmpty())) {
				actions.add(new MazeAction("Move_UpLeft"));
			}
			if (isRight
					&& (maze.getPoin(state_i[0] - 1, state_i[1] + 1) == maze
							.getEmpty())) {
				actions.add(new MazeAction("Move_UpRight"));
			}
		}

		if (isDown) {
			if (maze.getPoin((state_i[0] + 1), state_i[1]) == maze.getEmpty()) {
				actions.add(new MazeAction("Move_Down"));
			}
			if (isleft
					&& (maze.getPoin(state_i[0] + 1, state_i[1] - 1) == maze
							.getEmpty())) {
				actions.add(new MazeAction("Move_DownLeft"));
			}
			if (isRight
					&& (maze.getPoin(state_i[0] + 1, state_i[1] + 1) == maze
							.getEmpty())) {
				actions.add(new MazeAction("Move_DownRight"));
			}
		}
		
		if (isleft && (maze.getPoin(state_i[0], state_i[1] - 1) == maze.getEmpty()) || maze.getPoin(state_i[0], state_i[1] - 1) == this.maze.getCheese()){
			actions.add(new MazeAction("Move_Left"));
		}
		if (isRight && (maze.getPoin(state_i[0], state_i[1] + 1)== maze.getEmpty())){
			actions.add(new MazeAction("Move_Right"));
		}

		return actions;
	}

	@Override
	public Action getAction(State goal, State came_from) {
		for(Action act: getActions(came_from)){
			try {
				if (goal.equals(act.doAction(came_from))){
					return act;
				}
			} catch (IllegalActionExeption e) {
	
				e.printStackTrace();
			}
		}
		return null;
		
	}

	@Override
	public double getRange(State s1, State s2) {
		int [] pos1 = getPosition(s1);
		int [] pos2 = getPosition(s2);
		return (pos1[0] - pos2[0] != 0 && pos1[1] - pos2[1]  != 0) ? 15 : 10; 
	}

	
	private int[] getPosition(State state) {
		int[] pos = new int[2];
		pos[0] = Integer.parseInt((state.getState().split(","))[0]);
		pos[1] = Integer.parseInt((state.getState().split(","))[1]);
		return pos;
	}

}
