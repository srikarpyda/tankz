package tankz.core;

import java.io.File;
import java.util.Vector;

import tankz.ui.TankzGameUI;

public class TankzEngine extends Thread {
	
	public static TankzGameUI ui;
	public static TankzGrid grid;
	public static Vector<ActiveObject> active;
	private boolean isRunning = true;
	private boolean isPaused = false;
	
	public TankzEngine() {
		super("GameEngine");
		grid = new TankzGrid(9);
		this.setupGrid();
		active = new Vector<ActiveObject>();
		active.add(new Tank(16*8, 16*8));
		ui = new TankzGameUI();	
	}
	
	public TankzEngine(File file){
		//TODO generate code for this
	}
	
	public void run() {
		while(isRunning){
			while(!isPaused){
				ui.repaintGame();
				iterateLogic();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void stopGame(){
		this.isRunning = false;
	}
	
	public boolean togglePaused(){
		this.isPaused = !isPaused;
		return this.isPaused;
	}
	
	private void iterateLogic() {
	}
	
	private void setupGrid(){
		TankzTileState state = TankzTileState.BLOCKED;
		for(int x = 1; x < grid.getGridSize(); x+=2){
			for(int y = 1; y < grid.getGridSize(); y+=2){
				grid.setState(x, y, state);
			}
		}
		grid.setState(0, 0, TankzTileState.TANK_START);
		grid.setState(0, 8, TankzTileState.TANK_START);
		grid.setState(8, 0, TankzTileState.TANK_START);
		grid.setState(8, 8, TankzTileState.TANK_START);
		grid.setState(4, 4, TankzTileState.POWERUP);
	}
}
