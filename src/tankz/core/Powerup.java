package tankz.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Powerup extends ActiveObject {
	
	Image image = null;

	public Powerup(int x, int y) {
		super(x, y);
		try{
			image = ImageIO.read(new File("images/powerup.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}

}
