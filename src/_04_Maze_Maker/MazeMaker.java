package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random random1 = new Random();
		Random random2 = new Random();
		int randomCell1 = random1.nextInt(maze.cells.length - 1);
		int randomCell2 = random2.nextInt(maze.cells.length - 1);

		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[randomCell1][randomCell2]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);

		//B. check for unvisited neighbors using the cell
		ArrayList<Cell> result = getUnvisitedNeighbors(currentCell);
		
		
		//C. if has unvisited neighbors,
		if(result.size() > 0) {
			//C1. select one at random.
			Random r = new Random();
			int randomNeighbor = r.nextInt(result.size());
			Cell randomCell = result.get(randomNeighbor);
			
			
			//C2. push it to the stack
				uncheckedCells.push(randomCell);
			//C3. remove the wall between the two cells
			if(currentCell.getX() - 1 == randomCell.getX() && currentCell.getY() == randomCell.getY()) {
				currentCell.setNorthWall(false);
				randomCell.setSouthWall(false);
			}
			else if(currentCell.getX() + 1 == randomCell.getX() && currentCell.getY() == randomCell.getY()) {
			currentCell.setSouthWall(false);
			randomCell.setNorthWall(false);
			}
			else if(currentCell.getX() == randomCell.getX() && currentCell.getY() + 1 == randomCell.getY()) {
				currentCell.setEastWall(false);
				randomCell.setWestWall(false);
			}
			else if(currentCell.getX() == randomCell.getX() && currentCell.getY() - 1 == randomCell.getY()) {
				currentCell.setWestWall(false);
				randomCell.setEastWall(false);
			}
			//C4. make the new cell the current cell and mark it as visited
			currentCell.hasBeenVisited();
			randomCell.hasBeenVisited();
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
		
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
	ArrayList<Cell> Visitors = new ArrayList<Cell>();
	
		for (int i = 0; i < maze.cells.length; i++) {
			for (int j = 0; j < maze.cells.length; j++) {
				if(!maze.cells[i][j].hasBeenVisited()) {
					Visitors.add(maze.cells[i][j]);
				}
			}
		}
		return Visitors;
	}
}
