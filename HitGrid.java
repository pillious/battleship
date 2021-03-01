package battleship;

import java.util.ArrayList;
import java.util.Arrays;

public class HitGrid extends Grid {
	private ArrayList<Coordinate> guesses;
	
	public HitGrid() {
		super();
		guesses = new ArrayList<Coordinate>();
	}
	
	public void printGrid() {
		fillGrid();
		displayGrid();
	}
	
	private void fillGrid() {
		String[][] arr = new String[super.getGridSize()][super.getGridSize()];
		for (String[] row : arr) Arrays.fill(row, "~");
		
		for (Coordinate c : guesses) {
			arr[c.getY()][c.getX()] = c.getIsHit() ? "H" : "M";
		}
		
		super.setGrid(arr);
	};
	
	private void displayGrid() {
		System.out.print("  ");
		for (int i = 0; i < super.getGridSize(); i++) {
			System.out.print(i + " ");
		}
		System.out.println();
			
		int count = 0;
		for(String[] row : super.getGrid()) {
			System.out.print(count + " ");
			for (String s : row) {
				System.out.print(s + " ");
			}
			count++;
			System.out.println();
		}
	};
	
	/**
	 * Check if player's guess is within the game board and isn't a duplicate guess.
	 * @param g the guess the player makes in coordinate form
	 * @return true if guess is valid, false if not valid
	 */
	public boolean isValidGuess(Coordinate g) {
		if (g.getX() < getGridSize() && g.getX() >= 0 && 
			g.getY() < getGridSize() && g.getY() >= 0) { 
			for (Coordinate c : guesses) {
				if (g.equals(c)) return false;
			}
			
			return true;
		};
		
		return false;
	}
	
	public ArrayList<Coordinate> getGuesses() {
		return guesses;
	}
	
	/**
	 * Saves the player's guess
	 * @param guess coordinates of a new guess
	 */
	public void addGuess(Coordinate guess) {
		guesses.add(guess);
		fillGrid();
	}
}
