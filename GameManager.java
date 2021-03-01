package battleship;

public class GameManager {
	public static void main(String[] args) {		
		GameManager game = new GameManager();
		game.start();
	}
	
	public GameManager() {}
	
	/*
	 * Start a new game
	 * 1) print grids for p1
	 * 2) start a new turn for p1 (p1 makes new guess)
	 * 3) check if p1's guess hit one of p2's ship
	 * 4) if p1 successfully hit, update that coordinate for p2
	 * 5) update the ships remaining for p2
	 * 6) print grids for p1 again
	 * 6) check if p1 won (by checking how many ships p2 has left)
	 * 7) end game if p1 has won
	 */
	public void start() {
		Player p1 = new Player(false); // real player
		Player p2 = new Player(true); //robot 
		
		for (int i = 0; i < 2; i++) {
			Coordinate guess;
			
			// player 1's turn
			System.out.println("My Turn");
			p1.printGrids(p2.getGuesses());
			guess = p1.newTurn();
			p2.checkGuess(guess);
			
			p2.updateCoordinate(guess);
			p2.updateShipsRemaining();
			p1.printGrids(p2.getGuesses());
			
			if (p2.getShipsAlive() == 0) {
				System.out.println("You Win!");
				break;
			}
			
			// player 2's turn
			System.out.println("Computer's Turn");
			p2.printGrids(p1.getGuesses());
			guess = p2.newTurn();
			p1.checkGuess(guess);
			
			p1.updateCoordinate(guess);
			
			p1.updateShipsRemaining();
			p2.printGrids(p1.getGuesses());
			
			if (p1.getShipsAlive() == 0) {
				System.out.println("You Lose.");
				break;
			}
		}
		
		System.out.println("Game Stopped.");
	}
	 
}
