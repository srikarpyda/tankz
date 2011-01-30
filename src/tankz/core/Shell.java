package tankz.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shell extends ActiveObject {

	private Image shell;
	
	public Shell(int x, int y) {
		super(x, y);
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
		if(this.getVelocity() > 0){
			switch (this.getDirection()){
			case NORTH:
				this.setY(this.getY()-this.getVelocity());
				break;
			case EAST:
				this.setX(this.getX()+this.getVelocity());
				break;
			case SOUTH:
				this.setY(this.getY()+this.getVelocity());
				break;
			case WEST:
				this.setX(this.getX()-this.getVelocity());
				break;
			default:
				break;
			}
		}
	}	

}
