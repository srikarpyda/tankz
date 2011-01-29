package tankz.test;

import tankz.core.TankzEngine;

public class GridTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TankzEngine main = new TankzEngine();
		main.start();
		System.out.println(TankzEngine.grid.toString());
	}
	
}
