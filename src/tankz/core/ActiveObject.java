package tankz.core;

public class ActiveObject {

	private int velocity;
	private Direction direction;
	private int x;
	private int y;
	
	public ActiveObject(){
		this.velocity = 0;
		this.direction = Direction.NORTH;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public int getVelocity() {
		return velocity;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
}
