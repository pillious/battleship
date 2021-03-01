package battleship;

public class Grid {
	private final int gridSize = 2;
	private String[][] grid;
	
	public Grid() {}
	
	public int getGridSize() {
		return gridSize;
	}
	
	public String[][] getGrid() {
		return grid;
	}
	
	public void setGrid(String[][] grid) {
		this.grid = grid;
	}
}
