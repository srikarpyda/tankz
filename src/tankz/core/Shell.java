package tankz.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shell extends ActiveObject {

	private Image shell;
	private ActiveObject parent;

	public Shell(int x, int y, ActiveObject parent) {
		super(x, y);
		this.parent = parent;
		try{
			shell = ImageIO.read(new File("images/bullet.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Image getImage() {
		return shell;
	}

	public void action(){
		int nextX,nextY;
		if(this.getVelocity() > 0){
			switch (this.getDirection()){
			case NORTH:
				nextY = this.getY()-this.getVelocity();
				nextX = this.getX();				
				break;
			case EAST:
				nextX = this.getX()+this.getVelocity();
				nextY = this.getY();
				break;
			case SOUTH:
				nextY = this.getY()+this.getVelocity();
				nextX = this.getX();
				break;
			case WEST:
				nextX = this.getX()-this.getVelocity();
				nextY = this.getY();
				break;
			default:
				nextX = this.getX();
				nextY = this.getY();
				break;
			}
			Point p = new Point(nextX+8, nextY+8);
			if(canMove(p)) {
				if(hitsTank(p)) {
					parent.removeChild(this);
				}else {
					setX(nextX);
					setY(nextY);
				}
			}else {
				parent.removeChild(this);
			}
		}
	}	

	private boolean hitsTank(Point p) {
		for (ActiveObject o : TankzEngine.playerObjects) {
			if(p.getX() > o.getX() && p.getX() < o.getX()+16){
				if(p.getY() > o.getY() && p.getY() < o.getY()+16) {
					System.out.println("Object Hit");
					System.out.println(o.getHealth());
					o.registerHit(parent);
					return true;
				}
			}
		}
		return false;
	}

	public ActiveObject getParent(){
		return this.parent;
	}
	public boolean canMove(Point p1) {
		try{
			if(p1.getGridState() != TankzTileState.BLOCKED) {
				return true;
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}
}
