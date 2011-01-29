package tankz.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TankzGameUI extends JFrame {

	private static final long serialVersionUID = 8373812612954042172L;
	private TankzPanel pane;

	public TankzGameUI() {
		super("Tankz");
		
		pane = new TankzPanel();
		pane.setPreferredSize(new Dimension());
	}
}
