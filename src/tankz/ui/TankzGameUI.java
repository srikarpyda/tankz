package tankz.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import tankz.core.TankzEngine;

public class TankzGameUI extends JFrame {

	private static final long serialVersionUID = 8373812612954042172L;
	private TankzPanel pane;

	public TankzGameUI() {
		super("Tankz");
		
		pane = new TankzPanel();
		pane.setPreferredSize(new Dimension(TankzEngine.grid.getGridSize()*16,TankzEngine.grid.getGridSize()*16));
		add(pane);
		setResizable(false);
		
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Need to make this load the game menu or something!!
		setVisible(true);
	}
	public void repaintGame() {
		pane.repaint();
	}
}
