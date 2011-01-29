package tankz.core;

import tankz.ui.TankGameUI;

public class TankzEngine extends Thread {
	
	public TankGameUI ui;
	private boolean isRunning = true;
	private boolean isPaused = false;
	
	public TankzEngine() {
		ui = new TankGameUI();
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
}
