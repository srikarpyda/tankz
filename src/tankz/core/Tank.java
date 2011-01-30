package tankz.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends ActiveObject {
	
	private Image tank;
	
	public Tank(int x, int y){
		super(x, y);
		try{
			tank = ImageIO.read(new File("images/tank.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Image getImage() {
		return tank;
	}
	
	
	
}
