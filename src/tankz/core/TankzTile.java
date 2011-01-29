package tankz.core;

public class TankzTile {

	private int x;
	private int y;
	private TankzTileState state;
	
	public TankzTile(int x, int y, TankzTileState state){
		this.x = x;
		this.y = y;
		this.state = state;
	}
	
	public TankzTileState getState(){
		return this.state;
	}
	
	public void setState(TankzTileState state){
		this.state = state;
	}
	
}
