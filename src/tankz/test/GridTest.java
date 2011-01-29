package tankz.test;

import tankz.core.TankzGrid;
import tankz.core.TankzTileState;

public class GridTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TankzGrid grid = new TankzGrid(8);
		System.out.println(grid.toString());
		grid.setState(0, 0, TankzTileState.BLOCKED);
		grid.setState(7, 7, TankzTileState.BLOCKED);
		System.out.println(grid.toString());
	}

}
