package view;

import java.io.IOException;


public class Maze {
	private final int empty = 0;
	private final int wall = -1;
	private final int mouse = 1;
	private final int cheese = 2;

	private int rows = 10;
	private int coloms = 10;
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
		if (x >= 0 && y >= 0 ){
			return maze[x][y];
		}
		return -1;
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
		
		for(int i = 0; i < 9; i++){
			maze[8][i] = getWall();
			maze[6][i] = getWall();
			maze[4][i] = getWall();
			
		}
		maze[5][0] = getCheese();
		maze[7][0] = getMouse();

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

	
	
	

}
