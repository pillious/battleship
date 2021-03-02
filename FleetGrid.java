package battleship;

import java.util.Arrays;
import java.util.ArrayList;

public class FleetGrid extends Grid {
	private final int[] lens = {5, 4, 3, 2, 1};
	// battleship, cruiser, submarine, destroyer, frigate
	private final char[] names = {'b', 'c', 'd', 's', 'f'};
	private Ship[] ships = new Ship[lens.length];  
	
	public FleetGrid() {
		super();
		
		for (int i = 0; i < lens.length; i++) {
			Ship newShip;
			boolean isValid = false;
			while (!isValid) {
				newShip = new Ship(lens[i], names[i], super.getGridSize());
				isValid = isShipValid(newShip);
				
				if (isValid) ships[i] = newShip;
			}
			
		}
	}
	
	public FleetGrid(Ship[] s) {
		ships = s;
	}
	
	/*
	 * check if the generated ship intersects with existing ships
	 * @return false if the two ships intersect, true if they don't intersect
	 */
	private boolean isShipValid(Ship newShip) {
		for (Ship ship : ships) {
			if (ship != null && 
				doIntersect(ship.getPosition()[0], ship.getPosition()[ship.getPosition().length-1], 
							newShip.getPosition()[0], newShip.getPosition()[newShip.getPosition().length-1])) {
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * Check if the guess hits one of the ships
	 * @param g a guess (x,y)
	 * @return true if g is a hit, false if not a hit
	 */
	public boolean isAHit(Coordinate g) {
		for (Ship ship: ships) {
			for (Coordinate c : ship.getPosition()) {
				if (g.equals(c)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * set the state of a coordinate to being hit.
	 */
	public void setCoordinateToHit(Coordinate c) {
		boolean isFound = false;
		
		for (Ship ship: ships) {
			for (Coordinate sc : ship.getPosition()) {
				if (sc.equals(c)) {
					sc.setIsHit(true);
					isFound = true;
					break;
				}
				
				if (isFound) break;
			}
		}
	}
	
	/*
	 * Helper function for doIntersect() 
	 */
	private boolean onSegment(Coordinate p, Coordinate q, Coordinate r) 
	{ 
	    if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) && 
	        q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) 
	    return true; 
	  
	    return false; 
	} 
	
	/*
	 * To find orientation of ordered triplet (p, q, r). 
	 * The function returns following values 
	 * 0 --> p, q and r are colinear 
	 * 1 --> Clockwise 
	 * 2 --> Counterclockwise 
	 */
	private int orientation(Coordinate p, Coordinate q, Coordinate r) 
	{ 
	    int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - 
	            (q.getX() - p.getX()) * (r.getY() - q.getY()); 
	  
	    if (val == 0) return 0; // colinear 
	  
	    return (val > 0)? 1: 2; // clock or counterclock wise 
	} 
	
	/* 
	 * The main function that returns true if line segment 'p1q1'
	 * and 'p2q2' intersect.  
	*/ 
	private boolean doIntersect(Coordinate p1, Coordinate q1, Coordinate p2, Coordinate q2) 
	{ 
	    // Find the four orientations needed for general and 
	    // special cases 
	    int o1 = orientation(p1, q1, p2); 
	    int o2 = orientation(p1, q1, q2); 
	    int o3 = orientation(p2, q2, p1); 
	    int o4 = orientation(p2, q2, q1); 
	  
	    // General case 
	    if (o1 != o2 && o3 != o4) 
	        return true; 
	  
	    // Special Cases 
	    // p1, q1 and p2 are colinear and p2 lies on segment p1q1 
	    if (o1 == 0 && onSegment(p1, p2, q1)) return true; 
	  
	    // p1, q1 and q2 are colinear and q2 lies on segment p1q1 
	    if (o2 == 0 && onSegment(p1, q2, q1)) return true; 
	  
	    // p2, q2 and p1 are colinear and p1 lies on segment p2q2 
	    if (o3 == 0 && onSegment(p2, p1, q2)) return true; 
	  
	    // p2, q2 and q1 are colinear and q1 lies on segment p2q2 
	    if (o4 == 0 && onSegment(p2, q1, q2)) return true; 
	  
	    return false; // Doesn't fall in any of the above cases 
	} 
	
	/**
	 * 
	 * @param og the opponent's guesses
	 */
	public void printGrid(ArrayList<Coordinate> og) {
		fillGrid(og);
		displayGrid();
	}
	
	/**
	 * mark where the ships & water is located on the grid
	 * @param og the opponent's guesses
	 */
	private void fillGrid(ArrayList<Coordinate> og) {
		String[][] arr = new String[super.getGridSize()][super.getGridSize()];
		for (String[] row : arr) Arrays.fill(row, "~");
		
		// override the "~" with the ship names
		for (Ship s : ships) {
			for (Coordinate c : s.getPosition()) {
				arr[c.getY()][c.getX()] = String.valueOf(s.getName());
			}
		}
		
		// override the grid with "H" & "M" to signify where the opponent has guessed.
		for (Coordinate c : og) {
			arr[c.getY()][c.getX()] = c.getIsHit() ? "H" : "M";
 		}
		
		super.setGrid(arr);
	}
	
	/**
	 * print out the game board in its current state
	 */
	private void displayGrid() {
		System.out.print("  ");
		for (int i = 0; i < super.getGridSize(); i++) {
			System.out.print(i + " ");
		}
		System.out.println();
			
		int count = 0;
		for(String[] row: super.getGrid()) {
			System.out.print(count + " ");
			for (String s : row) {
				System.out.print(s + " ");
			}
			count++;
			System.out.println();
		}
	}
	
	public Ship[] getShips() {
		return ships;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < ships.length; i++) {
			result += i + ". " + ships[i].toString() + "\n";
		}
		return result;
	}
}
