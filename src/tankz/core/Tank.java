package tankz.core;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Tank extends ActiveObject {

	private Image tank_north;
	private Image tank_east;
	private Image tank_south;
	private Image tank_west;


	private Vector<ActiveObject> bullets = new Vector<ActiveObject>();
	private int north, south, east, west, fire;
	private boolean upButtonPressed = false; 
	private boolean downButtonPressed = false; 
	private boolean leftButtonPressed = false; 
	private boolean rightButtonPressed = false;


	public Tank(int x, int y, int north, int south, int east, int west, int fire){
		super(x, y);
		this.setHealth(5);
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
		this.fire = fire;
		try{
			tank_north = ImageIO.read(new File("images/tank_north.png"));
			tank_east = ImageIO.read(new File("images/tank_east.png"));
			tank_south = ImageIO.read(new File("images/tank_south.png"));
			tank_west = ImageIO.read(new File("images/tank_west.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	public Tank(int north, int south, int east, int west, int fire) {
		super(0,0);
		this.setHealth(5);
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
		this.fire = fire;
		try{
			tank_north = ImageIO.read(new File("images/tank_north.png"));
			tank_east = ImageIO.read(new File("images/tank_east.png"));
			tank_south = ImageIO.read(new File("images/tank_south.png"));
			tank_west = ImageIO.read(new File("images/tank_west.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	public Image getImage() {
		if(getRenderState()){
			switch (this.getDirection()){
			case NORTH:
				return tank_north;
			case EAST:
				return tank_east;
			case SOUTH:
				return tank_south;
			case WEST:
				return tank_west;
			default:
				return null;
			}
		}
		return null;
	}	
	public void action(){
		int nextX,nextY;
		if(this.getVelocity() > 0){
			switch (this.getDirection()){
			case NORTH:
				nextY = this.getY()-this.getVelocity();
				nextX = this.getX();
				if(canMove(new Point(nextX, nextY), new Point(nextX+15, nextY))) {
					setX(nextX);
					setY(nextY);
				}	
				break;
			case EAST:
				nextX = this.getX()+this.getVelocity();
				nextY = this.getY();
				if(canMove(new Point(nextX+15, nextY), new Point(nextX+15, nextY+15))) {
					setX(nextX);
					setY(nextY);
				}
				break;
			case SOUTH:
				nextY = this.getY()+this.getVelocity();
				nextX = this.getX();
				if(canMove(new Point(nextX, nextY+15), new Point(nextX+15, nextY+15))) {
					setX(nextX);
					setY(nextY);
				}
				break;
			case WEST:
				nextX = this.getX()-this.getVelocity();
				nextY = this.getY();
				if(canMove(new Point(nextX, nextY), new Point(nextX, nextY+15))) {
					setX(nextX);
					setY(nextY);
				}
				break;
			default:
				nextX = this.getX();
				nextY = this.getY();
				break;
			}

			//TODO Create per Pixel collison

		}
	}	
	public boolean canMove(Point p1, Point p2) {

		try{
			//System.out.println("P1 : " + p1.getString() + " " + p1.getGridInfo() + " P2 : " + p2.getString() + p2.getGridInfo());
			if(p1.getGridState() != TankzTileState.BLOCKED && p2.getGridState() != TankzTileState.BLOCKED) {
				return true;
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	public void addChild(ActiveObject shell){
		this.bullets.add(shell);
	}

	public void removeChild(ActiveObject shell){
		this.bullets.remove(shell);
	}

	public Vector<ActiveObject> getChildren(){
		return (Vector<ActiveObject>)bullets;
	}
	public void registerHit(ActiveObject parent) {
		this.setHealth(this.getHealth()-1);
		if(this.getHealth() == 0) {
			System.out.println("TANK DEAD");
			parent.addScore(1);
		}
	}
	public void fire(){
		Shell shell = null;

		switch (this.getDirection()){
		case NORTH:
			shell = new Shell(this.getX(),this.getY()-8, this);
			break;
		case EAST:
			shell = new Shell(this.getX()+8,this.getY(), this);
			break;
		case SOUTH:
			shell = new Shell(this.getX(),this.getY()+8, this);
			break;
		case WEST:
			shell = new Shell(this.getX()-8,this.getY(), this);
			break;
		default:
			break;
		}
		this.addChild(shell);
		shell.setDirection(this.getDirection());
		shell.setVelocity((float) 2);

	}

	public void keyPressed(int key){
		if(key==this.north){
			upButtonPressed = true;
			this.setDirection(Direction.NORTH);
			this.setVelocity((float) 1);
		}else if(key==this.south){
			downButtonPressed = true;
			this.setDirection(Direction.SOUTH);
			this.setVelocity((float) 1);
		}else if(key==this.west){
			leftButtonPressed = true;
			this.setDirection(Direction.WEST);
			this.setVelocity((float) 1);
		}else if(key==this.east){
			rightButtonPressed = true;
			this.setDirection(Direction.EAST);
			this.setVelocity((float) 1);
		}else if(key==this.fire){
			this.fire();
		}
	}

	public void keyReleased(KeyEvent key){
		stopTank(key);
	}

	private void stopTank(KeyEvent e) {	
		if(e.getKeyCode()==this.north){					
			upButtonPressed = false;
		}else if(e.getKeyCode()==this.west){
			leftButtonPressed = false;
		}else if(e.getKeyCode()==this.east){
			rightButtonPressed = false;
		}else if(e.getKeyCode()==this.south){
			downButtonPressed = false;
		}
		if(numOfKeys() < 1) {
			this.setVelocity(0);
		}
	}

	private int numOfKeys() {
		int r = 0;
		if(upButtonPressed){
			r++;
		}
		if(downButtonPressed) {
			r++;
		}
		if(leftButtonPressed) {
			r++;
		}
		if(rightButtonPressed) {
			r++;
		}
		return r;
	}
	public void resetHealth() {
		this.setHealth(5);	
	}
}