package tankz.core;

import java.awt.Image;

public class ActiveObject {

	private float velocity;
	private Direction direction;
	private int x;
	private int y;
	
	public ActiveObject(int x, int y){
		this.velocity = 0;
		this.direction = Direction.NORTH;
		this.x = x;
		this.y = y;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	public int getVelocity() {
		return Math.round(velocity);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public Image getImage(){
		return null;
	}
	
	public void action(){
	
	}
	
}
