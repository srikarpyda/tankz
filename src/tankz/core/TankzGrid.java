package tankz.core;

import java.util.Vector;

public class TankzGrid {

	private Vector<Vector<TankzTile>> grid;
	
	
	public TankzGrid(int size){
		
		Vector<TankzTile> row;
		grid = new Vector<Vector<TankzTile>>();
		for(int x = 0; x < size; x++){
			row = new Vector<TankzTile>();
			for(int y = 0; y < size; y++){
				row.add(new TankzTile(x, y, TankzTileState.EMPTY));
			}
			grid.add(row);
		}
		
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(Vector<TankzTile> row : grid){
			for(TankzTile tile : row){
				switch (tile.getState()) {
				case EMPTY:
					builder.append(0);
					break;
				case BLOCKED:
					builder.append(1);
					break;
				case TANK_START:
					builder.append(2);
					break;
				case POWERUP:
					builder.append(3);
					break;
				default:
					builder.append(tile.getState());
					break;
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public void setState(int x, int y, TankzTileState state){
		this.grid.get(x).get(y).setState(state);
	}
	
	public TankzTileState getState(int x, int y){
		return this.grid.get(x).get(y).getState();
	}
	
}
