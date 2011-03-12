package tankz.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends ActiveObject {

	private Image tank_north;
	private Image tank_east;
	private Image tank_south;
	private Image tank_west;

	public Tank(int x, int y){
		super(x, y);
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
	public void action(){
		int nextX,nextY;
		if(this.getVelocity() > 0){
			switch (this.getDirection()){
			case NORTH:
				nextY = this.getY()-this.getVelocity();
				nextX = this.getX();
				if(canMove(new Point(nextX, nextY), new Point(nextX+13, nextY))) {
					setX(nextX);
					setY(nextY);
				}	
				break;
			case EAST:
				nextX = this.getX()+this.getVelocity();
				nextY = this.getY();
				if(canMove(new Point(nextX+13, nextY), new Point(nextX+13, nextY+13))) {
					setX(nextX);
					setY(nextY);
				}
				break;
			case SOUTH:
				nextY = this.getY()+this.getVelocity();
				nextX = this.getX();
				if(canMove(new Point(nextX, nextY+13), new Point(nextX+15, nextY+13))) {
					setX(nextX);
					setY(nextY);
				}
				break;
			case WEST:
				nextX = this.getX()-this.getVelocity();
				nextY = this.getY();
				if(canMove(new Point(nextX, nextY), new Point(nextX, nextY+13))) {
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
			System.out.println("P1 : " + p1.getString() + " " + p1.getGridInfo() + " P2 : " + p2.getString() + p2.getGridInfo());
			if(p1.getGridState() != TankzTileState.BLOCKED && p2.getGridState() != TankzTileState.BLOCKED) {
				return true;
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}
}