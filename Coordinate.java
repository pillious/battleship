package battleship;

public class Coordinate {
	private int x;
	private int y;
	private boolean isHit;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		isHit = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getIsHit() {
		return isHit;
	}
	
	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	@Override
	public boolean equals(Object coord) {
		Coordinate c = (Coordinate)coord;
		
		if (x == c.getX() && y == c.getY()) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
