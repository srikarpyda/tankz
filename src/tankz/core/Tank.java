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
		if(this.getVelocity() > 0){
			switch (this.getDirection()){
			case NORTH:
				this.setY(this.getY()-1);
				break;
			case EAST:
				this.setX(this.getX()+1);
				break;
			case SOUTH:
				this.setY(this.getY()+1);
				break;
			case WEST:
				this.setX(this.getX()-1);
				break;
			default:
				break;
			}
		}
	}
	
	
	
}
