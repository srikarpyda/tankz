package tankz.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import tankz.core.ActiveObject;
import tankz.core.TankzEngine;

public class TankzPanel extends Panel {
	private static final long serialVersionUID = -3973916866602765231L;
	
	private Image block;	
	private Image empty;
	
	private Image buffer1;
	private Image buffer2;
	private boolean buffered = true;
	
	public TankzPanel() {
		block = null;
		buffer1 = new BufferedImage(TankzEngine.grid.getGridSize()*20, TankzEngine.grid.getGridSize()*20, BufferedImage.TYPE_INT_RGB);
		buffer2 = new BufferedImage(TankzEngine.grid.getGridSize()*20, TankzEngine.grid.getGridSize()*20, BufferedImage.TYPE_INT_RGB);
		try {
			block = ImageIO.read(new File("images/block.png"));
			empty = ImageIO.read(new File("images/empty.png"));
			//load rest of blocks here.
		} catch (IOException e) {
			//Even ducktape will not save you here!!!
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void paint(Graphics g) {
		if(buffered) {
			Graphics2D g2 = (Graphics2D)buffer2.getGraphics();
			g.drawImage(buffer1,0,0,null);
			paintBase(g2);
			paintActiveLayer(g2);
			paintOverlayLayer(g2);	
			
			buffered = false;
		}else {
			Graphics2D g2 = (Graphics2D)buffer1.getGraphics();
			g.drawImage(buffer2,0,0,null);
			paintBase(g2);
			paintActiveLayer(g2);
			paintOverlayLayer(g2);	
			buffered = true;
		}
		
		
	}
	private void paintOverlayLayer(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	private void paintActiveLayer(Graphics2D g2) {
		for (Iterator<ActiveObject> i = TankzEngine.active.iterator(); i.hasNext();) {
			ActiveObject a = (ActiveObject) i.next();
			if(a.getImage() != null){
				g2.drawImage(a.getImage(),a.getX(),a.getY(),null);
			}
		}		
	}

	private void paintBase(Graphics2D g2) {
		for (int x = 0; x < TankzEngine.grid.getGridSize(); x++) {
			for (int y = 0; y < TankzEngine.grid.getGridSize(); y++) {
				switch (TankzEngine.grid.getState(x, y)) {
				case BLOCKED:
					g2.drawImage(block,x*20,y*20,null);
					break;
				default:
					g2.drawImage(empty,x*20,y*20,null);
					break;
				}
			}
		}
	}
}
