package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		 cellSize = w / cellsPerRow;
		System.out.println(cellSize);
		// 3. Initialize the cell array to the appropriate size.
		cells = new Cell[cellsPerRow][cellsPerRow];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i * cellSize, j * cellSize, cellSize);
			}
		}
		
		randomizeCells();
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {

				Random r = new Random();
				int random1 = r.nextInt(50);
				int random2 = r.nextInt(50);
				if (random1 > random2) {
					cells[i][j].isAlive = false;
				} else if (random1 < random2) {
					cells[i][j].isAlive = true;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {

				cells[i][j].isAlive = false;

			}
		}

		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {

				cells[i][j].draw(g);

			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				int numOfNeighbors = getLivingNeighbors(i, j);
				cells[i][j].liveOrDie(numOfNeighbors);

				
			}
		}

		// 8. check if each cell should live or die
		
		repaint();
	}

	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y

	public ArrayList<Cell> allNeighbors(int x, int y) {
		ArrayList<Cell> list = new ArrayList<>();

		try {
			if (cells[x][y + 1] != null) {
				list.add(cells[x][y + 1]);
			}
			if (cells[x - 1][y] != null) {
				list.add(cells[x - 1][y]);
			}
			if (cells[x][y - 1] != null) {
				list.add(cells[x][y - 1]);
			}
			if (cells[x + 1][y] != null) {
				list.add(cells[x + 1][y]);
			}
			if (cells[x - 1][y - 1] != null) {
				list.add(cells[x - 1][y - 1]);
			}
			if (cells[x + 1][y + 1] != null) {
				list.add(cells[x + 1][y + 1]);
			}
			if (cells[x + 1][y - 1] != null) {
				list.add(cells[x + 1][y - 1]);
			}
			if (cells[x - 1][y + 1] != null) {
				list.add(cells[x - 1][y + 1]);
			}

		} catch (IndexOutOfBoundsException e) {
		}

		return list;
	}

	public int getLivingNeighbors(int x, int y) {
		int alive = 0;
		int width = 10;
		int height = 10;
		
		ArrayList<Cell> list = allNeighbors(x, y);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isAlive) {
				alive++;
			}
		}
		

		return alive;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		
		if(cells[e.getX()][e.getY()].isAlive) {
			cells[e.getX()][e.getY()].isAlive = false;
		}
		else {
			cells[e.getX()][e.getY()].isAlive = true;

		}

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
