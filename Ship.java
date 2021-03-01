package battleship;

public class Ship {
	private int length;
	private String direction; 
	private char name;
	private Coordinate[] position;
	private boolean isSunk;
	
	/**
	 * give ship a random location on the board
	 * @param l length of ship
	 * @param n how the ship is represented when displayed
	 * @param gridSize length & width of game board
	 */
	public Ship(int l, char n, int gridSize) {
		length = l;
		name = n;
		isSunk = false;
		
		boolean isValid = false;
		
		while (!isValid) {
			// randomly select cardinal direction
			switch ((int)(Math.random()*4)) {
				case 0: 
					direction = "N";
					break;
				case 1: 
					direction = "S";
					break;
				case 2: 
					direction = "E";
					break;
				case 3: 
					direction = "W";
					break;
			}
			
			// randomly select base coordinate of ship
			int randX = (int)(Math.random() * gridSize), randY = (int)(Math.random() * gridSize);

			position = initPosition(l, direction, new Coordinate(randX, randY));
			
			isValid = isShipOnGrid(gridSize);
		}
		
	}
	
	/**
	 * 
	 * @param len length of ship
	 * @param d direction of ship (Cardinal directions NSEW)
	 * @param baseCoords the coordinate the entire ship's location is calculated from
	 * @param gridSize length & width of game board
	 */
	public Ship(int l, String d, char n, Coordinate baseCoord, int gridSize) {
		length = l;
		direction = d;
		name = n;
		isSunk = false;
		position = initPosition(l, d, baseCoord);
	}
	
	/**
	 * 
	 * @param l length of ship
	 * @param d direction of ship (Cardinal directions NSEW)
	 * @param baseCoord used to calculate the entire ship's position
	 * @return the position of the ship in an array of coordinates
	 */
	private Coordinate[] initPosition(int l, String d, Coordinate baseCoord) {
		Coordinate[] arr = new Coordinate[l];

		for (int i = 0; i < l; i++) {
			switch (d) {
				case "N":
					arr[i] = new Coordinate(baseCoord.getX(), baseCoord.getY() - i);
					break;
				case "S":
					arr[i] = new Coordinate(baseCoord.getX(), baseCoord.getY() + i);
					break;
				case "E":
					arr[i] = new Coordinate(baseCoord.getX() + i, baseCoord.getY());
					break;
				case "W":
					arr[i] = new Coordinate(baseCoord.getX() - i, baseCoord.getY());
					break;
			}
		}
		
		return arr;
	}
	
	/*
	 * check if the ship fits on the grid
	 * (check if the last coordinate is on the grid)
	 */
	private boolean isShipOnGrid(int gridSize) {
		int x = position[position.length - 1].getX();
		int y = position[position.length - 1].getY();
		
		if (x < gridSize && x >= 0 && y < gridSize && y >= 0) return true;
		
		return false;
	}
	
	public String toString() {
		String result = "";
		for (Coordinate pos : position) {
			result += pos.toString() + " ";
		}
		
		result += length + " " + direction;
		
		return result;
	}

	public Coordinate[] getPosition() {
		return position;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public char getName() {
		return name;
	}
	
	public boolean getIsSunk() {
		return isSunk;
	}
	
	public void setIsSunk(boolean isSunk) {
		this.isSunk = isSunk;
	}
}
