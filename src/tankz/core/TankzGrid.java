package tankz.core;

import java.util.Iterator;
import java.util.Vector;

public class TankzGrid {

	private Vector<Vector<TankzTile>> grid;
	private Vector<TankzTile> spawnPoints;
	private int spawnCounter;
	
	public TankzGrid(int size){
		Vector<TankzTile> row;
		grid = new Vector<Vector<TankzTile>>();
		spawnPoints = new Vector<TankzTile>();
		spawnCounter = -1;
		for(int x = 0; x < size; x++){
			row = new Vector<TankzTile>();
			for(int y = 0; y < size; y++){
				row.add(new TankzTile(x*20, y*20, TankzTileState.EMPTY));
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
		if(grid.get(x).get(y).getState() == TankzTileState.TANK_START) {
			for (Iterator<TankzTile> i = spawnPoints.iterator(); i.hasNext();) {
				TankzTile tile = i.next();
				if(tile.getX()==x && tile.getY()==y) {
					spawnPoints.remove(tile);
				}
			}
		}
		this.grid.get(x).get(y).setState(state);
		if(state == TankzTileState.TANK_START) {
			System.out.println("Spawn point added X: " + x + " Y: "+y);
			spawnPoints.add(this.grid.get(x).get(y));
		}
	}
	
	public TankzTileState getState(int x, int y){
		return this.grid.get(x).get(y).getState();
	}
	
	public int getGridSize(){
		return this.grid.size();
	}

	public TankzTile getSpawnPoint() {		
		if(spawnCounter >= spawnPoints.size()) {
			spawnCounter = 0;
		}else {
			spawnCounter++;
		}
		System.out.println("Spawn point num:" + spawnPoints.size() + " Spawn Counter: " + spawnCounter);
		System.out.println("Spawn Point: " + spawnPoints.get(spawnCounter).toString());
		return spawnPoints.get(spawnCounter);
	}
	public TankzTile getTile(int x,int y) {
		return grid.get(x).get(y);
	}
}
