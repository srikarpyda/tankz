package tankz.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import tankz.ui.TankzGameUI;

public class TankzEngine extends Thread implements KeyListener{

	public static TankzGameUI ui;
	public static TankzGrid grid;
	public static Vector<ActiveObject> active;
	public static LinkedList<ActiveObject> spawnQueue;
	private boolean isRunning = true;
	private boolean isPaused = false;
	private boolean repaint = true;

	private boolean upButtonPressed = false; 
	private boolean downButtonPressed = false; 
	private boolean leftButtonPressed = false; 
	private boolean rightButtonPressed = false; 


	public TankzEngine() {
		super("GameEngine");
		grid = new TankzGrid(9);
		this.setupGrid();
		active = new Vector<ActiveObject>();
		spawnQueue = new LinkedList<ActiveObject>();
		spawnQueue.add(new Tank());
		spawnQueue.add(new Tank());
		ui = new TankzGameUI();
		ui.addKeyListener(this);
	}

	public TankzEngine(File file){
		//TODO generate code for this
	}
	public void spawn() {
		if(spawnQueue.size() != 0) {
			for (int i = 0; i < spawnQueue.size(); i++) {
				ActiveObject a = spawnQueue.pop();
				TankzTile spawnPoint = grid.getSpawnPoint();
				a.setX(spawnPoint.getX());
				a.setY(spawnPoint.getY());
				active.add(a);
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
				if(repaint) {
					ui.repaintGame();
					repaint = false;
				}else {
					repaint = true;
				}				
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
		for(ActiveObject object : active){
			object.action();
		}
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
		if(arg0.getKeyCode()==KeyEvent.VK_UP || arg0.getKeyCode()==KeyEvent.VK_LEFT || arg0.getKeyCode()==KeyEvent.VK_RIGHT || arg0.getKeyCode()==KeyEvent.VK_DOWN){
			if(!isPaused){
				moveTank(arg0);
			}
		}else if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
			if(!isPaused) {
				Shell shell = null;
				switch (active.get(0).getDirection()){
				case NORTH:
					shell = new Shell(active.get(0).getX(),active.get(0).getY()-8, active.get(0));
					break;
				case EAST:
					shell = new Shell(active.get(0).getX()+8,active.get(0).getY(), active.get(0));
					break;
				case SOUTH:
					shell = new Shell(active.get(0).getX(),active.get(0).getY()+8, active.get(0));
					break;
				case WEST:
					shell = new Shell(active.get(0).getX()-8,active.get(0).getY(), active.get(0));
					break;
				default:
					break;
				}
				active.add(shell);
				shell.setDirection(active.get(0).getDirection());
				shell.setVelocity((float) 2);
			}
		}else if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE){
			togglePaused();
		}else{
			System.out.println("Undefined Key");
		}

	}
	private void moveTank(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			upButtonPressed = true;
			active.get(0).setDirection(Direction.NORTH);
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftButtonPressed = true;
			active.get(0).setDirection(Direction.WEST);
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightButtonPressed = true;
			active.get(0).setDirection(Direction.EAST);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downButtonPressed = true;
			active.get(0).setDirection(Direction.SOUTH);
		}
		active.get(0).setVelocity((float) 1);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_UP || arg0.getKeyCode()==KeyEvent.VK_LEFT || arg0.getKeyCode()==KeyEvent.VK_RIGHT || arg0.getKeyCode()==KeyEvent.VK_DOWN){
			stopTank(arg0);
		}
	}
	private int numOfKeys() {
		int r = 0;
		if(upButtonPressed){
			r++;
		}
		if(downButtonPressed) {
			r++;
		}
		if(leftButtonPressed) {
			r++;
		}
		if(rightButtonPressed) {
			r++;
		}
		return r;
	}
	private void stopTank(KeyEvent e) {
		if(numOfKeys() == 1) {
			active.get(0).setVelocity(0);
		}	
		if(e.getKeyCode()==KeyEvent.VK_UP){					
			upButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downButtonPressed = false;
		}	
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
