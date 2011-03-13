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

	private boolean upButtonPressed = false; 
	private boolean downButtonPressed = false; 
	private boolean leftButtonPressed = false; 
	private boolean rightButtonPressed = false;
	
	private boolean wButtonPressed = false; 
	private boolean sButtonPressed = false; 
	private boolean aButtonPressed = false; 
	private boolean dButtonPressed = false; 


	public TankzEngine() {
		super("GameEngine");
		grid = new TankzGrid(9);
		this.setupGrid();
		active = new Vector<ActiveObject>();
		spawnQueue = new LinkedList<ActiveObject>();
		playerObjects = new Vector<ActiveObject>();
		addPlayer(new Tank());
		addPlayer(new Tank());
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
				TankzTile spawnPoint = grid.getSpawnPoint();
				a.setX(spawnPoint.getX());
				a.setY(spawnPoint.getY());
				a.resetHealth();
				if(!getActive().contains(a)){
					getActive().add(a);
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
				if(object.getHealth()== 0) {
					System.out.println("Deaded");
					spawnQueue.add(object);
				}else {
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
		if(arg0.getKeyCode()==KeyEvent.VK_UP || arg0.getKeyCode()==KeyEvent.VK_LEFT || arg0.getKeyCode()==KeyEvent.VK_RIGHT || arg0.getKeyCode()==KeyEvent.VK_DOWN){
			if(!isPaused){
				moveTank(arg0);
			}
		}else if(arg0.getKeyCode()==KeyEvent.VK_W || arg0.getKeyCode()==KeyEvent.VK_A || arg0.getKeyCode()==KeyEvent.VK_D || arg0.getKeyCode()==KeyEvent.VK_S){
			if(!isPaused){
				moveTank(arg0);
			}
		}else if(arg0.getKeyCode()==KeyEvent.VK_CONTROL){
			if(!isPaused) {
				playerObjects.get(0).fire();
			}
		}else if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
			if(!isPaused) {
				playerObjects.get(1).fire();
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
			playerObjects.get(0).setDirection(Direction.NORTH);
			playerObjects.get(0).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftButtonPressed = true;
			playerObjects.get(0).setDirection(Direction.WEST);
			playerObjects.get(0).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightButtonPressed = true;
			playerObjects.get(0).setDirection(Direction.EAST);
			playerObjects.get(0).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downButtonPressed = true;
			playerObjects.get(0).setDirection(Direction.SOUTH);
			playerObjects.get(0).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_W){
			wButtonPressed = true;
			playerObjects.get(1).setDirection(Direction.NORTH);
			playerObjects.get(1).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			aButtonPressed = true;
			playerObjects.get(1).setDirection(Direction.WEST);
			playerObjects.get(1).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			sButtonPressed = true;
			playerObjects.get(1).setDirection(Direction.SOUTH);
			playerObjects.get(1).setVelocity((float) 1);
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			dButtonPressed = true;
			playerObjects.get(1).setDirection(Direction.EAST);
			playerObjects.get(1).setVelocity((float) 1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_UP || arg0.getKeyCode()==KeyEvent.VK_LEFT || arg0.getKeyCode()==KeyEvent.VK_RIGHT || arg0.getKeyCode()==KeyEvent.VK_DOWN){
			stopTank(arg0, playerObjects.get(0));
		}else if(arg0.getKeyCode()==KeyEvent.VK_W || arg0.getKeyCode()==KeyEvent.VK_A || arg0.getKeyCode()==KeyEvent.VK_D || arg0.getKeyCode()==KeyEvent.VK_S){
			stopTank(arg0, playerObjects.get(1));
		}
	}
	private int numOfKeys(ActiveObject object) {
		int r = 0;
		if(object==playerObjects.get(0)){
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
		}else if(object==playerObjects.get(1)){
			if(wButtonPressed){
				r++;
			}
			if(sButtonPressed) {
				r++;
			}
			if(aButtonPressed) {
				r++;
			}
			if(dButtonPressed) {
				r++;
			}
		}
		return r;
	}
	private void stopTank(KeyEvent e, ActiveObject object) {
		if(numOfKeys(object) == 1) {
			object.setVelocity(0);
		}	
		if(e.getKeyCode()==KeyEvent.VK_UP){					
			upButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_W){
			wButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			aButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			sButtonPressed = false;
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			dButtonPressed = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
