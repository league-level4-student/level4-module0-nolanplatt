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
		if(!result.isEmpty()) {
			//C1. select one at random.
			Random r = new Random();
			int randomNeighbor = r.nextInt(result.size());
			Cell randomCell = result.get(randomNeighbor);
			
			
			//C2. push it to the stack
				uncheckedCells.push(randomCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, randomCell);
			//C4. make the new cell the current cell and mark it as visited
			
			currentCell = randomCell;
			currentCell.hasBeenVisited();
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
			
		//D. if all neighbors are visited
		else {
			//D1. if the stack is not empty
			if(!uncheckedCells.isEmpty()) {
				
			// D1a. pop a cell from the stack
	
			// D1b. make that the current cell
			currentCell = uncheckedCells.pop();
	
			// D1c. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		}
		
			
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {			
		 
			if(c1.getX() < c2.getX() && c1.getY() == c2.getY()) {
				System.out.println("South + North");
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			else if(c1.getX() + 1 == c2.getX() && c1.getY() == c2.getY()) {
				System.out.println("North + South");
			c1.setWestWall(false);
			c2.setEastWall(false);
			}
			if(c1.getX() == c2.getX() && c1.getY() + 1 == c2.getY()) {
				System.out.println("West + East");
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			else if(c1.getX() == c2.getX() && c1.getY() - 1 == c2.getY()) {
				System.out.println("East + West");
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		
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
