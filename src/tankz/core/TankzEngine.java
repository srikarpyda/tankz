package tankz.core;

import java.io.File;

import tankz.ui.TankzGameUI;

public class TankzEngine extends Thread {
	
	public static TankzGameUI ui;
	public static TankzGrid grid;
	private boolean isRunning = true;
	private boolean isPaused = false;
	
	public TankzEngine() {
		super("GameEngine");
		grid = new TankzGrid(9);
		this.setupGrid();
		ui = new TankzGameUI();	
	}
	
	public TankzEngine(File file){
		//TODO generate code for this
	}
	
	public void run() {
		while(isRunning){
			while(!isPaused){
				ui.repaint();
				iterateLogic();
				try {
					sleep(10);
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
		// TODO Auto-generated method stub
		
	}
	
	private void setupGrid(){
		TankzTileState state = TankzTileState.BLOCKED;
		for(int x = 1; x < grid.getGridSize(); x+=2){
			for(int y = 1; y < grid.getGridSize(); y+=2){
				grid.setState(x, y, state);
			}
		}		
	}
}
