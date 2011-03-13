package tankz.core;

import java.awt.Image;
import java.util.Vector;

public class ActiveObject {

	private float velocity;
	private Direction direction;
	private int x;
	private int y;
	private int health;
	
	public ActiveObject(int x, int y){
		this.velocity = 0;
		this.direction = Direction.NORTH;
		this.x = x;
		this.y = y;
		health = -1;
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
	
	public void addChild(ActiveObject object){
		
	}
	
	public Vector<ActiveObject> getChildren(){
		return null;
	}
	
	public void removeChild(ActiveObject object){
		
	}
	public void registerHit() {
		
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return health;
	}
}
