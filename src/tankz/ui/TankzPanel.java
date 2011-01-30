package tankz.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
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
	
	public TankzPanel() {
		block = null;
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
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		paintBase(g2);
		paintActiveLayer(g2);
		paintOverlayLayer(g2);
		
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
					g2.drawImage(block,x*16,y*16,null);
					break;
				default:
					g2.drawImage(empty,x*16,y*16,null);
					break;
				}
			}
		}
	}
}
