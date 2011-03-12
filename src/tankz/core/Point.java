package tankz.core;

public class Point {

	private int x,y;
	
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public String getString() {
		return "X: " + x + " Y: " + y;
	}
	public int getGridX() {
		return x/20;
	}
	public int getGridY() {
		return y/20;
	}
	public TankzTileState getGridState() {
		return TankzEngine.grid.getState(getGridX(),getGridY());
	}
}
