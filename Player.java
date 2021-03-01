package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private boolean isRobot;
	private FleetGrid fg;
	private HitGrid hg;
	private int shipsAlive;
	
	public Player(boolean isRobot) {
		this.isRobot = isRobot;
		fg = new FleetGrid();
		hg = new HitGrid();
		shipsAlive = fg.getShips().length;
	}
	
	/**
	 * execute a new turn depending on player type
	 * @param og the opponent's guesses
	 */
	public Coordinate newTurn() {
//		Coordinate guess = new Coordinate(0,0);
//		if (isRobot) guess = newGuess();
//		else {
//			fg.displayGrid();
//			System.out.println();
//			hg.displayGrid();
//			
//			guess = newGuess();
//			
//			fg.displayGrid();
//			System.out.println();
//			hg.displayGrid();
//		}
		
//		if (isRobot) {
//			System.out.println("Robot Turn");
//			fg.printGrid(og);
//			System.out.println();
//			hg.printGrid();
//			
//			guess = newGuess();
//			
//			fg.printGrid(og);
//			System.out.println();
//			hg.printGrid();
//		}
//		else {
//			System.out.println("My Turn");
//			fg.printGrid(og);
//			System.out.println();
//			hg.printGrid();
//			
//			guess = newGuess();
//			
//			fg.printGrid(og);
//			System.out.println();
//			hg.printGrid();
//		}
		
		Coordinate guess = newGuess();
		return guess;
	}
	
	
	/**
	 * Print out both the FleetGrid and HitGrid 
	 * @param og the opponent's guesses
	 */
	public void printGrids(ArrayList<Coordinate> og) {
		fg.printGrid(og);
		System.out.println();
		hg.printGrid();
	}
	
	/**
	 * execute a new guess depending on player type
	 */
	private Coordinate newGuess() {
		boolean isValid = false;
		Coordinate guess = new Coordinate(0,0);
		
		// if player is a robot
		if (isRobot) {
			while(!isValid) {
				guess = robotGuess();
				isValid = hg.isValidGuess(guess);
			}
			System.out.println("Computer's guess: " + guess.toString());
		}
		
		// if player is not a robot
		else {
			Scanner s = new Scanner(System.in);
			while(!isValid) {
				try {
					guess = playerGuess(s);
					isValid = hg.isValidGuess(guess);
				}
				catch(Exception e) {
				}
				if (!isValid) System.out.println("Invalid guess");
			}
			System.out.println("Your guess: " + guess.toString());
		}
		
		hg.addGuess(guess);
		return guess;
	}
	
	/**
	 * have player manually input their guess
	 */
	private Coordinate playerGuess(Scanner s) {
		int x = 0, y = 0;
		
		System.out.print("Guess x: ");
		if (s.hasNextInt()) x = s.nextInt();
		
		System.out.print("Guess y: ");
		if (s.hasNextInt()) y = s.nextInt();
		
		return new Coordinate(x,y);
	}
	
	/**
	 * generate a random guess
	 */
	private Coordinate robotGuess() {
		int randX = (int)(Math.random()*hg.getGridSize());
		int randY = (int)(Math.random()*hg.getGridSize());
		return new Coordinate(randX,randY);
	}

	/**
	 * check if opponent's guess hit a ship
	 * @param c your guess
	 */
	public void checkGuess(Coordinate c) {
		c.setIsHit(fg.isAHit(c));
	}
	
	/*
	 * set the state of a coordinate to being hit. (In FleetGrid)
	 */
	public void updateCoordinate(Coordinate c) {
		fg.setCoordinateToHit(c);
	}
	
	/**
	 * check how many ships the player has left
	 * runs every time a ship is struck
	 */
	public void updateShipsRemaining() {
		int shipsAlive = 0;
		Ship[] ships = fg.getShips();
		for (Ship ship : ships) {
			int partsHit = 0;
			
			for (Coordinate c : ship.getPosition()) {
				if (c.getIsHit()) partsHit++;
			}
			
			if (partsHit != ship.getPosition().length) shipsAlive++;
		}
		
		this.shipsAlive = shipsAlive;
	}

	/*
	 * retrieve the player's guesses from HitGrid
	 */
	public ArrayList<Coordinate> getGuesses() {
		return hg.getGuesses();
	}
	
	public int getShipsAlive() {
		return shipsAlive;
	}
}
