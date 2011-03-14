package tankz.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Vector;

import tankz.ui.TankzGameUI;

public class TankzEngine extends Thread implements KeyListener{

	public static TankzGameUI ui;
	public static TankzGrid grid;
	
	private static Vector<ActiveObject> active;
	public static LinkedList<ActiveObject> spawnQueue;
	public static Vector<ActiveObject> playerObjects;
	
	private boolean isRunning = true;
	private boolean isPaused = false;
	private boolean repaint = true;

	public TankzEngine() {
		super("GameEngine");
		grid = new TankzGrid(9);
		this.setupGrid();
		active = new Vector<ActiveObject>();
		spawnQueue = new LinkedList<ActiveObject>();
		playerObjects = new Vector<ActiveObject>();
		addPlayer(new Tank(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_CONTROL));
		addPlayer(new Tank(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_SPACE));
		ui = new TankzGameUI();
		ui.addKeyListener(this);
	}

	public TankzEngine(File file){
		//TODO generate code for this
	}
	public void addPlayer(ActiveObject o ) {
		spawnQueue.add(o);
		playerObjects.add(o);
	}
	public void spawn() {
		if(spawnQueue.size() != 0) {
			for (int i = 0; i < spawnQueue.size(); i++) {
				ActiveObject a = spawnQueue.pop();
				if(a.getSpawnCounter() == 0) {
					TankzTile spawnPoint = grid.getSpawnPoint();
					a.setX(spawnPoint.getX());
					a.setY(spawnPoint.getY());
					a.resetHealth();
					if(!getActive().contains(a)){
						getActive().add(a);
					}
					a.setRenderState(true);
				}else {
					System.out.println(a.getSpawnCounter());
					a.setSpawnCounter(a.getSpawnCounter()-1);
					spawnQueue.add(a);
				}				
			}
		}
	}
	public void run() {
		while(isRunning){
			if(repaint) {
				ui.repaintGame();
				repaint = false;
			}else {
				repaint = true;
			}
			if(!isPaused){
				spawn();			
				iterateLogic();				
			}
			try {
				sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
			for(ActiveObject object : getActive()){
				if(object.getHealth()== 0 && object.getRenderState() == true) {
					System.out.println("Deaded");
					object.setRenderState(false);
					object.setX(-20);
					object.setY(-20);
					object.setSpawnCounter(20);
					spawnQueue.add(object);
				}else if(object.getRenderState() == true) {
					object.action();
				}
				for (int i = 0; i < object.getChildren().size(); i++) {
					object.getChildren().get(i).action();
				}
			}		
	}
	public static synchronized Vector<ActiveObject> getActive() {
		return active;
	}
	private void setupGrid(){
		TankzTileState state = TankzTileState.BLOCKED;
		for(int x = 1; x < grid.getGridSize(); x+=2){
			for(int y = 1; y < grid.getGridSize(); y+=2){
				grid.setState(x, y, state);
			}
		}
		grid.setState(0, 0, TankzTileState.TANK_START);
		System.out.println(grid.getTile(0, 0).toString());
		grid.setState(0, 8, TankzTileState.TANK_START);
		System.out.println(grid.getTile(0, 8).toString());
		grid.setState(8, 0, TankzTileState.TANK_START);
		System.out.println(grid.getTile(8, 0).toString());
		grid.setState(8, 8, TankzTileState.TANK_START);
		System.out.println(grid.getTile(8, 8).toString());
		grid.setState(4, 4, TankzTileState.POWERUP);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE){
			togglePaused();
		}else{
			for(ActiveObject object : playerObjects){
				object.keyPressed(arg0);
			}
		}

	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		for(ActiveObject object : playerObjects){
			object.keyReleased(arg0);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
