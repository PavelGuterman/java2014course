package moveAction;

public class Move_DownRight implements mazeActionMove {

	@Override
	public int[] doMoveAction(int[] position) {
		int[] newPos = new int[2];
		newPos[0] = position[0] + 1;
		newPos[1] = position[1] + 1;
		return newPos;
	}

}
