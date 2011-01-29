package tankz.core;

import tankz.ui.TankGameUI;

public class TankzEngine extends Thread {
	
	public TankGameUI ui;
	
	public TankzEngine() {
		ui = new TankGameUI();
	}
	
	public void run() {
		ui.repaint();
		iterateLogic();
		try {
			sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void iterateLogic() {
		// TODO Auto-generated method stub
		
	}
}
