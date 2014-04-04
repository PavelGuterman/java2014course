package maze;

import java.lang.reflect.Array;

import aStar.State;

public class Maze {
	private final int empty = 0;
	private final int wall = -1;
	private final int mouse = 1;
	private final int cheese = 2;

	private int rows = 4;
	private int coloms = 3;
	private final int[][] maze = new int[coloms][rows];;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColoms() {
		return coloms;
	}

	public void setColoms(int coloms) {
		this.coloms = coloms;
	}

	public int getPoin(int x, int y) {
		return maze[x][y];
	}

	public int getEmpty() {
		return empty;
	}

	public int getWall() {
		return wall;
	}

	public int getMouse() {
		return mouse;
	}

	public int getCheese() {
		return cheese;
	}

	public Maze() {
		for (int i = 0; i < coloms; i++) {
			for (int j = 0; j < rows; j++) {
				maze[i][j] = getEmpty();
			}
			System.out.println("");
		}
		maze[1][0] = getWall();
		maze[1][1] = getWall();
		maze[0][0] = getCheese();
		maze[2][3] = getMouse();

		PrintMazeToConsole();
	}

	public void PrintMazeToConsole() {
		for (int i = 0; i < coloms; i++) {
			for (int j = 0; j < rows; j++) {
				System.out.print(maze[i][j] + ",");
			}
			System.out.println("");
		}
	}

	public State getStartState() {
		for (int i = 0; i < coloms; i++) {
			for (int j = 0; j < rows; j++) {
				if (maze[i][j] == getMouse()) {
					System.out.println("the Moise is in " + (i + "," + j));
					return new State(0, 0, (i + "," + j));
				}
			}
		}
		return null;
	}

	public State getGoalState() {
		for (int i = 0; i < coloms; i++) {
			for (int j = 0; j < rows; j++) {
				if (maze[i][j] == getCheese()) {
					System.out.println("the Cheese is in "+(i+","+j));
					return new State(0, 0, (i+","+j));
				}
			}
		}
		return null;
	}

}
