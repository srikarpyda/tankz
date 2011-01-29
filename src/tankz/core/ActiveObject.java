package tankz.core;

public class ActiveObject {

	private int velocity;
	private Direction direction;
	
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
	
}
